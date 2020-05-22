package com.nbs.iais.ms.meta.referential.db.service.agent;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Agent;
import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.DeleteAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.services.AgentCommandService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class AgentCommandServiceTest extends ServiceTest {

    @Mock
    private AgentRepository agentRepository;

    @InjectMocks
    private AgentCommandService service;

    @Test
    public void createAgentTest() {
        //setup
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateAgentCommand command = CreateAgentCommand.create(jwt, "Name", "Description",
                AgentType.INDIVIDUAL, "email@email.com", 1L, 1L, Language.ENG);

        final AgentEntity agent = new AgentEntity();
        agent.setId(2L);
        agent.setName(command.getName(), command.getLanguage());
        agent.setDescription(command.getDescription(), command.getLanguage());
        agent.setType(command.getType());
        agent.setAccount(command.getAccount());

        final AgentEntity parent = new AgentEntity();
        parent.setId(1L);
        parent.setName("Parent", command.getLanguage());
        parent.setType(AgentType.DIVISION);

        agent.setParent(parent);

        Mockito.when(agentRepository.save(Mockito.any(AgentEntity.class))).thenReturn(agent);
        Mockito.when(agentRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(parent));

        //call
        service.createAgent(command);

        //verify
        Mockito.verify(agentRepository, Mockito.atLeast(2)).save(Mockito.any(AgentEntity.class));
        Mockito.verify(agentRepository).findById(Mockito.eq(1L));
    }

    @Test
    public void updateAgentTest() {

        //setup
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateAgentCommand command = UpdateAgentCommand.create(jwt, 2L, "Name", "Description",
                AgentType.INDIVIDUAL, "email@email.com", 1L, 1L, null, null, null, Language.ENG);

        final AgentEntity agent = new AgentEntity();
        agent.setId(2L);
        agent.setName(command.getName(), command.getLanguage());
        agent.setDescription(command.getDescription(), command.getLanguage());
        agent.setType(command.getType());
        agent.setAccount(command.getAccount());

        final AgentEntity parent = new AgentEntity();
        parent.setId(1L);
        parent.setName("Parent", command.getLanguage());
        parent.setType(AgentType.DIVISION);

        agent.setParent(parent);

        Mockito.when(agentRepository.save(Mockito.any(AgentEntity.class))).thenReturn(agent);
        Mockito.when(agentRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(parent));
        Mockito.when(agentRepository.findById(Mockito.eq(2L))).thenReturn(Optional.of(agent));

        //call
        service.updateAgent(command);

        //verify
        Mockito.verify(agentRepository).findById(Mockito.eq(1L));
        Mockito.verify(agentRepository).findById(Mockito.eq(2L));
        Mockito.verify(agentRepository).save(Mockito.any(AgentEntity.class));
    }

    @Test
    public void deleteAgentTest() {

        //setup
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteAgentCommand command = DeleteAgentCommand.create(jwt, 2L);

        final AgentEntity agent = new AgentEntity();
        agent.setId(2L);

        Mockito.when(agentRepository.findById(Mockito.eq(2L))).thenReturn(Optional.of(agent));
        Mockito.doNothing().when(agentRepository).delete(Mockito.any(AgentEntity.class));

        //call
        service.deleteAgent(command);

        //verify
        Mockito.verify(agentRepository).findById(Mockito.eq(2L));
        Mockito.verify(agentRepository).delete(Mockito.any(AgentEntity.class));

    }
}
