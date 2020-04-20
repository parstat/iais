package com.nbs.iais.ms.meta.referential.db.repository;

import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class BusinessFunctionRepositoryTest extends RepositoryTest {

    @Autowired
    private BusinessFunctionRepository businessFunctionRepository;


    @Test
    public void testSaveBusinessFunction() {

        final BusinessFunctionEntity toSave = getBusinessFunctionEntity();

        final BusinessFunctionEntity saved = businessFunctionRepository.save(toSave);

        Assert.assertNotNull(saved);

    }

    private BusinessFunctionEntity getBusinessFunctionEntity() {
        final BusinessFunctionEntity toSave = new BusinessFunctionEntity();
        toSave.setName("name", Language.ENG);
        toSave.setName("nume", Language.ROM);
        toSave.setName("имя", Language.RUS);
        toSave.setDescription("description", Language.ENG);
        toSave.setDescription("descriere", Language.ROM);
        toSave.setDescription("описание", Language.RUS);
        toSave.setLocalId("1.1");
        toSave.setVersion("5.1");
        toSave.setVersionDate(LocalDateTime.now());
        toSave.setVersionRationale("rationale");
        return toSave;
    }
}
