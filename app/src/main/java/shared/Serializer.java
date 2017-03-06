package shared;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static String serializeList(Object obj) {
        List<Object> objList = (List<Object>) obj;
        try {
            String serializedString = JsonWriter.objectToJson(objList.toArray());
            return serializedString;
        } catch(IOException e) {
            return null;
        }

    }

    public static Object deserializeList(String str) {
        try {
            Object[] objArray = (Object[]) JsonReader.jsonToJava(str);
            return new ArrayList<Object>(Arrays.asList(objArray));
        } catch (IOException e){
            return null;
        }
    }

}
