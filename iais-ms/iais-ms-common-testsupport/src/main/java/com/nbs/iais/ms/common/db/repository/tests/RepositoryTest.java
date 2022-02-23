package com.nbs.iais.ms.common.db.repository.tests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
public abstract class RepositoryTest {

    @Autowired
    public TestEntityManager entityManager;

}
