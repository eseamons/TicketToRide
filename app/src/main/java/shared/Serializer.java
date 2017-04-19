package shared;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.IOException;

/**
 * Created by erics on 2/22/2017.
 */

public class Serializer {
    public static String serialize(Object obj) {
        try {
            String serializedString = JsonWriter.objectToJson(obj);
            return serializedString;
        } catch(IOException e) {
            return null;
        }
    }

    public static Object deserialize(String json) {
        try {
            Object obj = JsonReader.jsonToJava(json);
            return obj;
        } catch(IOException e) {
            return null;
        }
    }

}
