package com.nbs.iais.ms.meta.referential.db.repository;


import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentInRoleEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentInRoleRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import org.codehaus.groovy.transform.sc.transformers.StaticCompilationTransformer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class StatisticalProgramRepositoryTest extends RepositoryTest {

    @Autowired
    private StatisticalProgramRepository statisticalProgramRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AgentInRoleRepository agentInRoleRepository;

    @Test
    public void testSaveStatisticalProgram() {
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);

        Assert.assertNotNull(saved);
    }

    @Test
    public void testFindAllByLocalId(){
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);

        Iterable<StatisticalProgramEntity> statisticalProgram = statisticalProgramRepository.findAllByLocalId(saved.getLocalId());
        Assert.assertTrue(statisticalProgram.iterator().hasNext());
    }

    @Test
    public void testExistsgByLocalId(){
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);

        Assert.assertTrue(statisticalProgramRepository.existsByLocalId(saved.getLocalId()));
    }

    @Test
    public void testFindByLocalIdAndVersion() {
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);

        Assert.assertTrue(statisticalProgramRepository.findByLocalIdAndVersion(saved.getLocalId(),saved.getVersion()).isPresent());
    }

    @Test
    public void testExitsByLocalIdAndVersion() {
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);

        Assert.assertTrue(statisticalProgramRepository.existsByLocalIdAndVersion(saved.getLocalId(), saved.getVersion()));
    }

    @Test
    public void testFindAllTopByLocalIdOrderByVersionDateDesc() {
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);

        Assert.assertTrue(statisticalProgramRepository.findAllTopByLocalIdOrderByVersionDateDesc(saved.getLocalId()).isPresent());
    }

    @Test
    public void testFindAllByNameInLanguageContaining() {
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);

        Iterable<StatisticalProgramEntity> statisticalProgram = statisticalProgramRepository.findAllByNameInLanguageContaining(Language.ENG.getShortName(), "ame");
        Assert.assertTrue(statisticalProgram.iterator().hasNext());
    }

    @Test
    public void testFindAllByAcronymInLaguageContaining() {
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);

        Iterable<StatisticalProgramEntity> statisticalProgram = statisticalProgramRepository.findAllByAcronymInLanguageContaining(Language.ENG.getShortName(), "ron");
        Assert.assertTrue(statisticalProgram.iterator().hasNext());
    }

    @Test
    public void testFindAllByProgramStatus() {
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);

        Iterable<StatisticalProgramEntity> statisticalProgram = statisticalProgramRepository.findAllByProgramStatus(saved.getProgramStatus());
        Assert.assertTrue(statisticalProgram.iterator().hasNext());
    }

    @Test
    public void testFindAllByCreator() {
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);

        Iterable<StatisticalProgramEntity> statisticalProgram = statisticalProgramRepository.findAllByCreator(saved.getCreator());
        Assert.assertTrue(statisticalProgram.iterator().hasNext());
    }

    @Test
    public void testFindAllByAgentInRole() {
        final StatisticalProgramEntity toSave = saveStatisticaProgram();
        final StatisticalProgramEntity saved = statisticalProgramRepository.save(toSave);
        final AgentEntity parent = agentRepository.save(saveAgent("Parent", AgentType.DIVISION));
        final AgentInRole agentInRole = agentInRoleRepository.save(saveAgentInRole(parent));
        saved.getAdministrators().add(agentInRole);

        Iterable<StatisticalProgramEntity> statisticalProgram = statisticalProgramRepository.findAllByAgentInRole(parent, RoleType.MAINTAINER);
        Assert.assertTrue(statisticalProgram.iterator().hasNext());

    }

    private StatisticalProgramEntity saveStatisticaProgram(){
        final StatisticalProgramEntity toSave = new StatisticalProgramEntity();
        toSave.setName("Name", Language.ENG);
        toSave.setName("Nume", Language.ROM);
        toSave.setName("имя", Language.RUS);
        toSave.setAcronym("Acronym", Language.ENG);
        toSave.setAcronym("Acronim", Language.ROM);
        toSave.setAcronym("Акроним", Language.RUS);
        toSave.setDateInitiated(LocalDateTime.of(2020,1,1,0,0));
        toSave.setDateEnded(LocalDateTime.of(2999,12,31,0,0));
        toSave.setProgramStatus(ProgramStatus.CURRENT);
        toSave.setLocalId("lfs");
        toSave.setVersion("1.0");
        toSave.setBudget(0.0);
        toSave.setDescription("description", Language.ENG);
        toSave.setDescription("descriere", Language.ROM);
        toSave.setVersionDate(LocalDateTime.now());
        toSave.setCreatedTimestamp(Instant.now());
        toSave.setCreator(1L);
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
    private AgentInRoleEntity saveAgentInRole(AgentEntity agent) {
        final AgentInRoleEntity toSave = new AgentInRoleEntity();
        toSave.setAgent(agent);
        toSave.setRole(RoleType.MAINTAINER);
        return toSave;
    }
 }
