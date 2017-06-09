/*
 MIT License

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package br.com.spolti.handler;

import br.com.spolti.exception.UnsupportedHttpMethod;
import br.com.spolti.fact.ChuckNorrisFact;
import io.undertow.attribute.ExchangeAttributes;
import io.undertow.predicate.Predicate;
import io.undertow.predicate.Predicates;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * Created by fspolti on 6/8/17.
 */
public class ChuckNorrisFactHandler implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        // set the response header to JSON
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");

        Predicate predicate = Predicates.and(Predicates.contains(ExchangeAttributes.requestMethod(), "POST"));

        if (predicate.resolve(exchange)) {
            exchange.getResponseSender().send(ChuckNorrisFact.get());
        } else {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send(String.format("The HTTP method %s is not valid, only POST method is supported.", exchange.getRequestMethod()));
            throw new UnsupportedHttpMethod(String.format("The HTTP method %s is not valid, only POST method is supported.", exchange.getRequestMethod()));
        }
    }
}