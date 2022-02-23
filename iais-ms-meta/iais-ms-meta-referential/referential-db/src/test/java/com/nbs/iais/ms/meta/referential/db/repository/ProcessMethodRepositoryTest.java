package com.nbs.iais.ms.meta.referential.db.repository;

import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessMethodEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessMethodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ProcessMethodRepositoryTest extends RepositoryTest {

    @Autowired
    private ProcessMethodRepository processMethodRepository;

    @Test
    public void testSave() {
        final ProcessMethodEntity toSaveEntity = createEntity();
        final ProcessMethodEntity savedEntity = processMethodRepository.save(toSaveEntity);

        Assertions.assertNotNull(savedEntity);
    }

    private ProcessMethodEntity createEntity() {
        final ProcessMethodEntity entity = new ProcessMethodEntity();

        // set required fields
        entity.setLocalId("lfs");
        entity.setVersion("1.0");
        entity.setVersionDate(LocalDateTime.now());

        // set optional fields
        entity.setName("Name", Language.ENG);
        entity.setName("Nume", Language.ROM);
        entity.setName("имя", Language.RUS);
        entity.setDescription("Description", Language.ENG);
        entity.setDescription("Descriere", Language.ROM);
        entity.setDescription("Oписание", Language.RUS);

        return entity;
    }


}
