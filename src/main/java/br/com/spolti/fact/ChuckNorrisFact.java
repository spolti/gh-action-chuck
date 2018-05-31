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

package br.com.spolti.fact;

import br.com.spolti.response.FollowUpEvent;
import br.com.spolti.response.SpeechCustomResponse;
import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by fspolti on 6/8/17.
 */
public class ChuckNorrisFact {

    private static final String CHUCK_NORRIS_FACTS_ENDPOINT = "https://api.chucknorris.io/jokes/random";
    private static SpeechCustomResponse speechCustomResponse;

    /**
     * Execute the request
     *
     * @return Return a JSON pretty response based on {@link SpeechCustomResponse}
     */
    public static String get() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(CHUCK_NORRIS_FACTS_ENDPOINT);
        Response response = target.request().get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed to connect in the endpoint " + CHUCK_NORRIS_FACTS_ENDPOINT + ", status code is: " + response.getStatus());
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new SpeechCustomResponse(response.readEntity(Fact.class).getValue()));
    }
}