package com.nbs.iais.ms.meta.referential.api.controllers;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.DeleteAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/close/referential/agents", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiAgentClosed extends AbstractController {

    /**
     * Method to create an agent
     *
     * @param jwt         authorization token
     * @param name        of the agent in selected language
     * @param type        of agent: DIVISION, ORGANIZATION, INDIVIDUAL
     * @param description of the agent in the selected language
     * @param localId     of the agent i.e the email of the INDIVIDUAL
     * @param parent      of the agent, ORGANIZATIONS Can not have parents
     * @param account     INDIVIDUAL only are related to an account
     * @param language    selected
     * @return AgentDTO
     */
    @JsonView(Views.Extended.class)
    @PostMapping
    public AgentDTO createAgent(@RequestHeader(name = "jwt-auth") final String jwt,
                                @RequestParam(name = "name", required = false) final String name,
                                @RequestParam(name = "type", required = false) final AgentType type,
                                @RequestParam(name = "description", required = false) final String description,
                                @RequestParam(name = "local_id") final String localId,
                                @RequestParam(name = "parent", required = false) final Long parent,
                                @RequestParam(name = "account", required = false) final Long account,
                                @RequestParam(name = "language", required = false) final String language) {

        final CreateAgentCommand command = CreateAgentCommand.create(jwt, name, description, type, localId, parent,
                account, Language.getLanguage(language));
        return sendCommand(command, "agent").getEvent().getData();

    }

    /**
     * Method to update an agent
     *
     * @param jwt              authorization token
     * @param id               of the agent
     * @param name             of the agent in selected language
     * @param type             of agent: DIVISION, ORGANIZATION, INDIVIDUAL
     * @param description      of the agent in the selected language
     * @param localId          of the agent i.e the email of the INDIVIDUAL
     * @param version          first version of agent (default 1.0)
     * @param versionDate      of the agent (default now())
     * @param versionRationale reason of the first version of agent (default 'First
     *                         Version')
     * @param parent           of the agent, ORGANIZATIONS Can not have parents
     * @param account          INDIVIDUAL only are related to an account
     * @param language         selected
     * @return AgentDTO
     */
    @JsonView(Views.Extended.class)
    @PatchMapping("/{id}")
    public AgentDTO updateAgent(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id, @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "type", required = false) final AgentType type,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "local_id", required = false) final String localId,
            @RequestParam(name = "parent", required = false) final Long parent,
            @RequestParam(name = "account", required = false) final Long account,
            @RequestParam(name = "version", required = false) final String version,
            @RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
            @RequestParam(name = "versionRationale", required = false) final String versionRationale,
            @RequestParam(name = "language", required = false) final String language) {

        final UpdateAgentCommand command = UpdateAgentCommand.create(jwt, id, name, description, type, localId, parent,
                account, version, versionDate, versionRationale, Language.getLanguage(language));
        return sendCommand(command, "agent").getEvent().getData();

    }

    /**
     * Method to delete an agent
     *
     * @param jwt authorization token, only ADMIN and ROOT can delete currently
     * @param id  of agent to delete
     * @return DTOBoolean true if the agent has been deleted
     */
    @JsonView(Views.Extended.class)
    @DeleteMapping("/{id}")
    public DTOBoolean deleteAgent(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id) {

        final DeleteAgentCommand command = DeleteAgentCommand.create(jwt, id);

        return sendCommand(command, "agent").getEvent().getData();
    }

    /**
     * be ADMIN or self) Method to get the agent by account
     *
     * @param account  id of registered account that is mapped with an agent
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return AgentDTO in the selected language
     * @throws AuthorizationException if requested account is not self or has not the role ADMIN
     */
    @JsonView(Views.Extended.class)
    @GetMapping("/account/{account}")
    public AgentDTO getAgentQueryByAccount(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "account") final Long account,
            @RequestParam(name = "language") final String language) throws AuthorizationException {

        final Long user = JWT.decode(jwt).getClaim("user").asLong();
        final AccountRole role = AccountRole.valueOf(JWT.decode(jwt).getClaim("role").asString());
        if(account.equals(user) || role != AccountRole.USER) {
            final GetAgentQuery getAgentQuery = GetAgentQuery.create();
            getAgentQuery.setAccountId(account);
            getAgentQuery.setLanguage(Language.getLanguage(language));
            return sendQuery(getAgentQuery, "agent").getRead().getData();
        }
        throw new AuthorizationException(ExceptionCodes.NOT_AUTHORIZED);
    }

}
