package com.nbs.iais.ms.meta.referential.db.repository;

import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentationEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Arrays;

public class ProcessDocumentationRepositoryTest extends RepositoryTest {

    @Autowired
    private ProcessDocumentationRepository processDocumentationRepository;


    @Test
    public void testSaveBusinessFunction() {

    }
    private ProcessDocumentationEntity saveProcessDocumentation() {
        final ProcessDocumentationEntity toSave = new ProcessDocumentationEntity();
        toSave.setName("Name", Language.ENG);
        toSave.setName("Nume", Language.ROM);
        toSave.setName("имя", Language.RUS);
        toSave.setDescription("Description", Language.ENG);
        toSave.setDescription("Descriere", Language.ROM);
        toSave.setDescription("описание", Language.RUS);
        return toSave;
    }
}
