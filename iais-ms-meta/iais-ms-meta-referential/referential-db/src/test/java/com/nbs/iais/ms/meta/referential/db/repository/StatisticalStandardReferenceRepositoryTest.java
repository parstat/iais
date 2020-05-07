package com.nbs.iais.ms.meta.referential.db.repository;

import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalStandardReferenceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Arrays;

public class StatisticalStandardReferenceRepositoryTest extends RepositoryTest {

    @Autowired
    private StatisticalStandardReferenceRepository statisticalStandardRepository;


    @Test
    public void testSaveBusinessFunction(){
        final StatisticalStandardReferenceEntity toSave = saveStatisticaStandard();
        final StatisticalStandardReferenceEntity saved = statisticalStandardRepository.save(toSave);

        Assert.assertNotNull(saved);
    }

    @Test
    public void testFindByType() {
        final StatisticalStandardReferenceEntity toSave = saveStatisticaStandard();
        final StatisticalStandardReferenceEntity saved = statisticalStandardRepository.save(toSave);

        Iterable<StatisticalStandardReferenceEntity> statisticalStandard = statisticalStandardRepository.findByType(StatisticalStandardType.CONCEPTS);
        Assert.assertTrue(statisticalStandard.iterator().hasNext());
    }

    @Test
    public void testFindAllByNameInLanguageContaining(){
        final StatisticalStandardReferenceEntity toSave = saveStatisticaStandard();
        final StatisticalStandardReferenceEntity saved = statisticalStandardRepository.save(toSave);

        Iterable<StatisticalStandardReferenceEntity> statisticaStandard = statisticalStandardRepository.findAllByNameInLanguageContaining(Language.ENG.getShortName(), "Name");
        Assert.assertTrue(statisticaStandard.iterator().hasNext());
    }

    private StatisticalStandardReferenceEntity saveStatisticaStandard() {
        final StatisticalStandardReferenceEntity toSave = new StatisticalStandardReferenceEntity();
        toSave.setName("Name", Language.ENG);
        toSave.setName("Nume", Language.ROM);
        toSave.setName("имя", Language.RUS);
        toSave.setDescription("Description", Language.ENG);
        toSave.setDescription("Descriere", Language.ROM);
        toSave.setDescription("описание", Language.RUS);
        toSave.setLink("link", Language.ENG);
        toSave.setLink("link", Language.ROM);
        toSave.setLink("ссылка на сайт", Language.RUS);
        toSave.setLocalId("lfs");
        toSave.setVersion("1.0");
        toSave.setType(StatisticalStandardType.CONCEPTS);
        toSave.setVersionDate(LocalDateTime.now());


        return toSave;
    }
}
