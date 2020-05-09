package com.nbs.iais.ms.meta.referential.db.services;


import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.DeleteAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AgentCommandService {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessDocumentationCommandService.class);

    @Autowired
    private AgentRepository agentRepository;

    /**
     * Method to create an agent
     *
     * @param command to execute
     * @return CreateAgentCommand including the dto of agent in the event
     * @throws EntityException when the command includes a parent that can not be
     *                         found
     */
    public CreateAgentCommand createAgent(final CreateAgentCommand command)
            throws AuthorizationException, EntityException {

        final AgentEntity agentEntity = agentRepository.save(CommandTranslator.translate(command));

        if (command.getParent() != null) {
            agentRepository.findById(command.getParent()).ifPresentOrElse(parent -> {
                agentEntity.setParent(parent);
                agentRepository.save(agentEntity);
            }, () -> {
                throw new EntityException(ExceptionCodes.AGENT_PARENT_NOT_FOUND);
            });
        }

        Translator.translate(agentEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);
        return command;
    }

    /**
     * Method to update an agent usually to add name and description in other
     * languages
     *
     * @param command to execute
     * @return UpdateAgentCommand including the dto of updated agent in the event
     */
    public UpdateAgentCommand updateAgent(final UpdateAgentCommand command) throws AuthorizationException {

        if (command.getId() != null) {
            agentRepository.findById(command.getId()).ifPresentOrElse(agent -> {
                CommandTranslator.translate(command, agent);

                if (command.getParent() != null) {
                    if (command.getId().equals(command.getParent())) {
                        throw new EntityException(ExceptionCodes.AGENT_PARENT_NOT_APPLICABLE);
                    }

                    agentRepository.findById(command.getParent()).ifPresentOrElse(agent::setParent, () -> {
                        throw new EntityException(ExceptionCodes.AGENT_PARENT_NOT_FOUND);
                    });
                }

                Translator.translate(agentRepository.save(agent), command.getLanguage())
                        .ifPresent(command.getEvent()::setData);
            }, () -> {
                throw new EntityException(ExceptionCodes.AGENT_NOT_FOUND);
            });

        }
        return command;
    }

    /**
     * Method to delete an agent
     *
     * @param command to execute
     * @return DTOBoolean
     * @throws AuthorizationException AGENT_NOT_FOUND when the agent can not be found
     */
    @Transactional
    public DeleteAgentCommand deleteAgent(final DeleteAgentCommand command) throws AuthorizationException {

        try {
            final AgentEntity agentToDelete = agentRepository.findById(command.getId())
                    .orElseThrow(() -> new EntityException(ExceptionCodes.AGENT_NOT_FOUND));

            // remove Parent relationship
            agentRepository.findByParent(agentToDelete).forEach(child -> {
                child.setParent(null);
                agentRepository.save(child);
            });

            agentRepository.delete(agentToDelete);
        } catch (Exception e) {
            LOG.debug("Error deleting agent: " + e.getMessage());
            command.getEvent().setData(DTOBoolean.FAIL);
            return command;
        }

        command.getEvent().setData(DTOBoolean.TRUE);

        return command;
    }
}
