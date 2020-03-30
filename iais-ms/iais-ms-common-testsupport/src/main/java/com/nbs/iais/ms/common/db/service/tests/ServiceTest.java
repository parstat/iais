package com.nbs.iais.ms.common.db.service.tests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public abstract class ServiceTest {

    private boolean mockInitialized = false;

    @Before
    public void setup() {
        if(!mockInitialized) {
            MockitoAnnotations.initMocks(this);
            mockInitialized = true;
        }
    }
}
