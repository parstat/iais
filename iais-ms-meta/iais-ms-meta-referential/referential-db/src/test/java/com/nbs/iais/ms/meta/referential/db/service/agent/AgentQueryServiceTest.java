package com.nbs.iais.ms.meta.referential.db.service.agent;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.services.AgentQueryService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

public class AgentQueryServiceTest extends ServiceTest {

    @Mock
    private AgentRepository repository;

    @InjectMocks
    private AgentQueryService service;

    @Test
    public void getAgentById() {
        //setup
        final GetAgentQuery query = GetAgentQuery.create(1L, Language.ENG);
        final AgentEntity agent = new AgentEntity();
        agent.setId(1L);
        agent.setName("Name", query.getLanguage());
        agent.setDescription("Description", query.getLanguage());
        agent.setType(AgentType.INDIVIDUAL);
        agent.setAccount(1L);

        Mockito.when(repository.findById(Mockito.eq(query.getId()))).thenReturn(Optional.of(agent));

        //call
        service.getAgent(query);

        //verify
        Mockito.verify(repository).findById(Mockito.eq(query.getId()));
    }

    @Test
    public void getAgentByLocalId() {
        //setup
        final GetAgentQuery query = GetAgentQuery.create("email@email.com", Language.ENG);
        final AgentEntity agent = new AgentEntity();
        agent.setId(1L);
        agent.setName("Name", query.getLanguage());
        agent.setDescription("Description", query.getLanguage());
        agent.setType(AgentType.INDIVIDUAL);
        agent.setLocalId(query.getLocalId());
        agent.setAccount(1L);

        Mockito.when(repository.findByLocalId(Mockito.eq(query.getLocalId()))).thenReturn(Optional.of(agent));

        //call
        service.getAgent(query);

        //verify
        Mockito.verify(repository).findByLocalId(Mockito.eq(query.getLocalId()));
    }

    @Test
    public void getAgentByAccount() {
        //setup
        final GetAgentQuery query = GetAgentQuery.create(Language.ENG);
        query.setAccountId(1L);
        final AgentEntity agent = new AgentEntity();
        agent.setId(1L);
        agent.setName("Name", query.getLanguage());
        agent.setDescription("Description", query.getLanguage());
        agent.setType(AgentType.INDIVIDUAL);
        agent.setLocalId(query.getLocalId());
        agent.setAccount(1L);

        Mockito.when(repository.findByAccount(Mockito.eq(query.getAccountId()))).thenReturn(Optional.of(agent));

        //call
        service.getAgent(query);

        //verify
        Mockito.verify(repository).findByAccount(Mockito.eq(query.getAccountId()));
    }


    @Test
    public void getAgentsByNameTest() {

        //setup
        final GetAgentsQuery query = GetAgentsQuery.create(null, "Name", null, Language.ENG);

        final AgentEntity agent = new AgentEntity();
        agent.setId(1L);
        agent.setName("Name", query.getLanguage());
        agent.setDescription("Description", query.getLanguage());
        agent.setType(AgentType.INDIVIDUAL);
        agent.setLocalId("email@email.com");
        agent.setAccount(1L);

        Mockito.when(repository.findAllByNameInLanguageContaining(Mockito.eq(query.getLanguage().getShortName()),
                Mockito.eq(query.getName()))).thenReturn(Collections.singletonList(agent));

        //Call
        service.getAgents(query);

        //verify
        Mockito.verify(repository).findAllByNameInLanguageContaining(Mockito.eq(query.getLanguage().getShortName()),
                Mockito.eq(query.getName()));
    }

    @Test
    public void getAgentsByParentTest() {

        //setup
        final GetAgentsQuery query = GetAgentsQuery.create(null, null, 1L, Language.ENG);

        final AgentEntity agent = new AgentEntity();
        agent.setId(2L);
        agent.setName("Name", query.getLanguage());
        agent.setDescription("Description", query.getLanguage());
        agent.setType(AgentType.INDIVIDUAL);
        agent.setLocalId("email@email.com");
        agent.setAccount(1L);

        final AgentEntity parent = new AgentEntity();
        parent.setId(1L);
        parent.setName("Parent", query.getLanguage());
        parent.setType(AgentType.DIVISION);

        agent.setParent(parent);

        Mockito.when(repository.findById(Mockito.eq(parent.getId()))).thenReturn(Optional.of(parent));
        Mockito.when(repository.findByParent(Mockito.any(AgentEntity.class))).thenReturn(Collections.singletonList(agent));

        //Call
        service.getAgents(query);

        //verify
        Mockito.verify(repository).findById(Mockito.eq(parent.getId()));
        Mockito.verify(repository).findByParent(Mockito.any(AgentEntity.class));
    }

    @Test
    public void getAgentsByTypeTest() {

        //setup
        final GetAgentsQuery query = GetAgentsQuery.create(AgentType.INDIVIDUAL,null, null, Language.ENG);

        final AgentEntity agent = new AgentEntity();
        agent.setId(1L);
        agent.setName("Name", query.getLanguage());
        agent.setDescription("Description", query.getLanguage());
        agent.setType(AgentType.INDIVIDUAL);
        agent.setLocalId("email@email.com");
        agent.setAccount(1L);

        Mockito.when(repository.findAllByType(Mockito.eq(AgentType.INDIVIDUAL))).thenReturn(Collections.singletonList(agent));

        //Call
        service.getAgents(query);

        //verify
        Mockito.verify(repository).findAllByType(Mockito.eq(AgentType.INDIVIDUAL));
    }
}
