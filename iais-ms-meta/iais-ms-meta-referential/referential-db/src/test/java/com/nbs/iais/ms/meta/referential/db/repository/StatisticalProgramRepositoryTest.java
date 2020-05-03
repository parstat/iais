package com.nbs.iais.ms.meta.referential.db.repository;


import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDateTime;

public class StatisticalProgramRepositoryTest extends RepositoryTest {

    @Autowired
    private StatisticalProgramRepository statisticalProgramRepository;

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
 }
