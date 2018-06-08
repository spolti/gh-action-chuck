package br.com.spolti.chuck.handler;

import io.undertow.io.IoCallback;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ChuckNorrisDumpFileHandler implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange exchange) throws IOException {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain; charset=utf-8");
        exchange.getResponseSender().transferFrom(
                new RandomAccessFile(new File(System.getProperty("user.home") + "/chuck-jokes.txt"), "rw").getChannel(),
                IoCallback.END_EXCHANGE);
    }
}