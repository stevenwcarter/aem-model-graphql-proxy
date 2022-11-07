package com.bounteous.service;

import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;
import com.google.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GuiceTestRunner.class)
@GuiceModules(ServiceConfigModule.class)
public class AEMServiceImplTest {

    @Inject
    private AEMService aemService;

    @Test
    public void testAddingModelJsonToPath() {
        AEMServiceImpl aemServiceImpl = (AEMServiceImpl) aemService;

        Assert.assertEquals("test.model.json", aemServiceImpl.addModelJsonToPath("test"));
        Assert.assertEquals("/some/path/test.model.json", aemServiceImpl.addModelJsonToPath("/some/path/test"));
        Assert.assertEquals("test.mOdEl.json", aemServiceImpl.addModelJsonToPath("test.mOdEl.json"));
    }
}
