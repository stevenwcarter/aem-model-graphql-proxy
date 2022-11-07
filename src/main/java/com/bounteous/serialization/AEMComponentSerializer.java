package com.bounteous.serialization;

import com.bounteous.model.*;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Registers each AEM component type with its deserializer
 */
public class AEMComponentSerializer implements JsonDeserializer<AEMComponent> {
    @Override
    public AEMComponent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String componentType = jsonElement.getAsJsonObject().get(":type").getAsString();

        switch(componentType) {
            case "wcm/foundation/components/responsivegrid": return jsonDeserializationContext.deserialize(jsonElement, AEMResponsiveGrid.class);
            case "wknd-spa-react/components/header": return jsonDeserializationContext.deserialize(jsonElement, AEMHeader.class);
            case "wknd-spa-react/components/text": return jsonDeserializationContext.deserialize(jsonElement, AEMText.class);
            case "wknd-spa-react/components/image": return jsonDeserializationContext.deserialize(jsonElement, AEMImage.class);
            case "wknd-spa-react/components/button": return jsonDeserializationContext.deserialize(jsonElement, AEMButton.class);
            case "wknd-spa-react/components/page": return jsonDeserializationContext.deserialize(jsonElement, AEMPageModel.class);
            case "wknd-spa-react/components/json": return jsonDeserializationContext.deserialize(jsonElement, AEMJson.class);
            default: return null;
        }
    }

}
