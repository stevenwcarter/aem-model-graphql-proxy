package com.bounteous.api.inject;

import com.bounteous.serialization.SerializerModule;
import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.bounteous.model.AEMComponent;
import com.bounteous.model.AEMPageModel;
import com.bounteous.model.AEMText;
import com.bounteous.service.AEMService;
import com.bounteous.service.ServiceConfigModule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

@RunWith(GuiceTestRunner.class)
@GuiceModules({SerializerModule.class, ServiceConfigModule.class})
public class TestGsonParsePageModel {

    @Inject
    private Gson gson;

    @Inject
    private AEMService aemService;


    @Test
    public void testParsePageModel() throws UnsupportedEncodingException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("pagemodel.json");
        Reader reader = new InputStreamReader(is, "UTF-8");
        AEMPageModel pageModel = gson.fromJson(reader, AEMPageModel.class);

        System.out.println(gson.toJson(pageModel.getItems()));
        Assert.assertEquals("wcm/foundation/components/responsivegrid", pageModel.getItems().get(0).getComponent().getType());

    }

    @Test
    public void testParseComponent() throws Exception {
        // /content/wknd-spa-react/us/en/home/jcr:content/root/responsivegrid/text.model.json
        AEMComponent component = aemService.getComponent("/content/wknd-spa-react/us/en/home/jcr:content/root/responsivegrid/text.model.json");

        Assert.assertTrue(((AEMText)component).isRichText());
    }
}
