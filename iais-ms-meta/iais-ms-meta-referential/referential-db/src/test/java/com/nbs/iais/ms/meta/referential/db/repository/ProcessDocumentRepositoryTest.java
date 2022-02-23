package com.nbs.iais.ms.meta.referential.db.repository;

import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProcessDocumentRepositoryTest extends RepositoryTest {

    @Autowired
    ProcessDocumentRepository processDocumentRepository;

    @Test
    public void testSaveBusinessFunction () {
    final  ProcessDocumentEntity toSaveDoc = saveProcessDocumentRepository();
    final  ProcessDocumentEntity saveDoc = processDocumentRepository.save(toSaveDoc);
    Assertions.assertNotNull(saveDoc);

    }

    private ProcessDocumentEntity saveProcessDocumentRepository(){

            final ProcessDocumentEntity toSave = new ProcessDocumentEntity();

           // set required fields
            toSave.setLocalId("lsf");
            toSave.setVersion("1.0");
            toSave.setVersionDate(LocalDateTime.now());
        // set optional fields

        toSave.setName("Name",Language.ENG);
        toSave.setName("Nume",Language.ROM);
        toSave.setName("имя",Language.RUS);
        toSave.setDescription("Description",Language.ENG);
        toSave.setDescription("Descriere",Language.ROM);
        toSave.setDescription("Oписание",Language.RUS);
        return toSave;

    }

}
