package com.nbs.iais.ms.meta.referential.db.repository;

import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentationEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessInputSpecificationEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessDocumentationRepositoryTest extends RepositoryTest {

    @Autowired
    private ProcessDocumentationRepository processDocumentationRepository;


    @Test
    public void testSaveProcessDocumentation() {
        final ProcessDocumentationEntity saved = processDocumentationRepository.save(saveProcessDocumentation());
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void testAddInputsToProcess() {
        final ProcessDocumentationEntity saved = processDocumentationRepository.save(saveProcessDocumentation());
        saved.addProcessInput(getInput());
        //final ProcessDocumentationEntity saved2 = processDocumentationRepository.save(saved);
        //assertNotNull(saved2.getProcessInputs());
        assertEquals(processDocumentationRepository.save(saved).getProcessInputs().size(), 1);

    }
    private ProcessDocumentationEntity saveProcessDocumentation() {
        final ProcessDocumentationEntity toSave = new ProcessDocumentationEntity();
        toSave.setName("Name", Language.ENG);
        toSave.setName("Nume", Language.ROM);
        toSave.setName("имя", Language.RUS);
        toSave.setDescription("Description", Language.ENG);
        toSave.setDescription("Descriere", Language.ROM);
        toSave.setDescription("описание", Language.RUS);
        toSave.setLocalId(UUID.randomUUID().toString());
        toSave.setVersion("1.0");
        toSave.setVersionDate(LocalDateTime.now());
        toSave.setVersionRationale("rationale");
        return toSave;
    }

    private ProcessInputSpecificationEntity getInput() {
        final ProcessInputSpecificationEntity input = new ProcessInputSpecificationEntity();
        input.setName("input name", Language.ENG);
        input.setDescription("description", Language.ENG);
        input.setLocalId(UUID.randomUUID().toString());
        input.setVersion("1.0");
        input.setVersionDate(LocalDateTime.now());
        return input;
    }


}
