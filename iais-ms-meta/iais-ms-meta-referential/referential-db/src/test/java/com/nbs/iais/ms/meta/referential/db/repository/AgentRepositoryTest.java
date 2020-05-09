package com.nbs.iais.ms.meta.referential.db.repository;

import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDateTime;
import java.util.Arrays;

public class AgentRepositoryTest extends RepositoryTest {

    @Autowired
    private AgentRepository agentRepository;


    @Test
    public void testSaveBusinessFunction() {
        final AgentEntity toSave = saveAgent();
        final AgentEntity saved = agentRepository.save(toSave);

        Assert.assertNotNull(saved);

    }

    @Test
    public void testFindByAccount() {
        final AgentEntity toSave = saveAgent();
        final AgentEntity saved = agentRepository.save(toSave);

        Assert.assertTrue(agentRepository.findByAccount(saved.getAccount()).isPresent());
    }

    @Test
    public void testFindByLocalId() {
        final AgentEntity toSave = saveAgent();
        final AgentEntity saved = agentRepository.save(toSave);

        Assert.assertTrue(agentRepository.findByLocalId(saved.getLocalId()).isPresent());
    }

    @Test
    public void testFindByIdIn() {
        final AgentEntity toSave = saveAgent();
        final AgentEntity saved = agentRepository.save(toSave);

        Iterable<AgentEntity> agent = agentRepository.findByIdIn(Arrays.asList(saved.getId(),2L,3L));
        Assert.assertTrue(agent.iterator().hasNext());
    }

    @Test
    public void testFindByParent() {
        final AgentEntity parent = agentRepository.save(saveAgent("Parent", AgentType.ORGANIZATION));
        final AgentEntity child1 = agentRepository.save(saveAgent("Child1", AgentType.DIVISION));
        child1.setParent(parent);
        agentRepository.save(child1);
        final AgentEntity child2 = agentRepository.save(saveAgent("Child2", AgentType.DIVISION));
        child2.setParent(parent);
        agentRepository.save(child2);

        Iterable<AgentEntity> children = agentRepository.findByParent(parent);
        Assert.assertTrue(children.iterator().hasNext());
    }

    @Test
    public void testFindByType() {
        final AgentEntity toSave = saveAgent();
        final AgentEntity saved = agentRepository.save(toSave);

        Iterable<AgentEntity> agent = agentRepository.findByType(saved.getType());
    }

    @Test
    public void testFindByName() {
        final AgentEntity parent = agentRepository.save(saveAgent("Parent", AgentType.ORGANIZATION));

        Iterable<AgentEntity> agent = agentRepository.findAllByNameInLanguageContaining(Language.ENG.getShortName(), "Parent");
        Assert.assertTrue(agent.iterator().hasNext());
    }

    private AgentEntity saveAgent() {
        final AgentEntity toSave = new AgentEntity();
        toSave.setName("Name", Language.ENG);
        toSave.setName("Nume", Language.ROM);
        toSave.setName("имя", Language.RUS);
        toSave.setDescription("Description", Language.ENG);
        toSave.setDescription("Descriere", Language.ROM);
        toSave.setDescription("описание", Language.RUS);
        toSave.setType(AgentType.DIVISION);
        toSave.setLocalId("lfs");
        toSave.setVersion("1.0");
        toSave.setVersionDate(LocalDateTime.now());

        return toSave;
    }
    private AgentEntity saveAgent(String englishName, AgentType type) {
        final AgentEntity toSave = new AgentEntity();
        toSave.setName(englishName, Language.ENG);
        toSave.setDescription("Description", Language.ENG);
        toSave.setType(type);
        toSave.setLocalId("001");
        toSave.setVersion("1.0");
        toSave.setVersionDate(LocalDateTime.now());
        return toSave;
    }
}
