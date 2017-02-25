package shared;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.IOException;

/**
 * Created by erics on 2/22/2017.
 */

public class Serializer {
    public static String serialize(Object obj) throws IOException{
        String serialize = JsonWriter.objectToJson(obj);
        return serialize;
    }

    public static Object deserialize(String json) throws IOException{
        Object obj = JsonReader.jsonToJava(json);
        return obj;
    }
}
