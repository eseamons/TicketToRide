package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by sirla on 2/10/2017.
 */

public class HandlerBase implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

    }
}
