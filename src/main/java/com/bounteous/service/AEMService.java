package com.bounteous.service;

import com.bounteous.model.AEMPageModel;
import com.bounteous.model.AEMComponent;

public interface AEMService {

    AEMPageModel getPageModel(String path);

    AEMComponent getComponent(String path);
}
