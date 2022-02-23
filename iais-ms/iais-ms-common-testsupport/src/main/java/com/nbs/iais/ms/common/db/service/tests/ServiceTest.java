package com.nbs.iais.ms.common.db.service.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public abstract class ServiceTest {

    private boolean mockInitialized = false;

    @BeforeAll
    public void setup() {
        if(!mockInitialized) {
            MockitoAnnotations.openMocks(this);
            mockInitialized = true;
        }
    }
}
