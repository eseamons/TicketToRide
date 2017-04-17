package server;

import android.os.Build;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import server.plugin.Plugin;

/**
 * Created by sirla on 2/10/2017.
 */

public class ServerCommunicator {

    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    public void run(String portNumber) {
        System.out.println("Initializing HTTP Server");
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null); // use the default executor

        System.out.println("Creating contexts");
        server.createContext("/command", new ExecCommandHandler());

        server.createContext("/test", new TestHandler());

        System.out.println("Starting server");
        try {
            System.out.println("Address2: " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        server.start();
    }

    public static void main(String[] args) {


        String portNumber = "8080";
        //String provider = args[0];
        //String checkpoint = args[1];
        boolean wipe = false;
        if (args.length >= 3)
            if (args[2].equals("-wipe") || args[2].equals("wipe") || args[2].equals("w") || args[2].equals("-w"))
                wipe = true;

        String provider = "SQL";
        String checkpoint = "1";

        ServerModel model = ServerModel.getInstance();
        model.setPlugin(new Plugin(provider));
        model.setCheckpoint(Integer.parseInt(checkpoint));
        model.setWipe(wipe);


        new ServerCommunicator().run(portNumber);
    }
}
