package com.bounteous.serialization;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by steve on 11/19/16.
 */
public class JsonUtilities {

    private static final Logger log = LoggerFactory.getLogger(JsonUtilities.class);

    /**
     * Merge "source" into "target". If fields have equal name, merge them recursively.
     *
     * @return the merged object (target).
     */
    public static JSONObject deepMerge(JSONObject source, JSONObject target) throws JSONException {
        for (String key : JSONObject.getNames(source)) {
            Object value = source.get(key);
            if (!target.has(key)) {
                // new value for "key":
                target.put(key, value);
            } else {
                // existing value for "key" - recursively deep merge:
                if (value instanceof JSONObject) {
                    JSONObject valueJson = (JSONObject) value;
                    deepMerge(valueJson, target.getJSONObject(key));
                } else {
                    target.put(key, value);
                }
            }
        }
        return target;
    }

    public static String fixWeirdCharacters(String s) {
        s = s.replace((char) 145, '\'');

        s = s.replace((char) 8216, '\''); // left single quote

        s = s.replace((char) 146, '\'');

        s = s.replace((char) 8217, '\''); // right single quote

        s = s.replace((char) 147, '\"');

        s = s.replace((char) 148, '\"');

        s = s.replace((char) 8220, '\"'); // left double

        s = s.replace((char) 8221, '\"'); // right double

        s = s.replace((char) 8211, '-'); // em dash??

        s = s.replace((char) 150, '-');

        return s;
    }
}
