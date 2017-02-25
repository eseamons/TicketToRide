package client;

import android.os.StrictMode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import shared.Result;
import shared.Serializer;
import shared.command_classes.Command;

/**
 * Created by sirla on 2/10/2017.
 */

public class ClientCommunicator {
    private static ClientCommunicator ourInstance = new ClientCommunicator();

    public static ClientCommunicator getInstance() {
        return ourInstance;
    }

    private ClientCommunicator() {
    }

    Result send(String urlPath, Command requestCmd){
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try{
            URL url = new URL(urlPath);

            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.connect();

            String reqData = Serializer.serialize(requestCmd);

            OutputStream reqBody = urlConnection.getOutputStream();
            writeString(reqData, reqBody);
            reqBody.close();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = urlConnection.getInputStream();
                String respData = readString(respBody);
                return (Result) Serializer.deserialize(respData);
            }
            else {
                return (Result) Serializer.deserialize(urlConnection.getResponseMessage());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
