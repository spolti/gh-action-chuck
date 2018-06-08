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

package br.com.spolti.chuck.fact;

import br.com.spolti.chuck.fact.pojo.Fact;
import br.com.spolti.chuck.fact.pojo.SpeechCustomResponse;
import br.com.spolti.persistence.PersistToFile;
import br.com.spolti.persistence.WriteObject;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by fspolti on 6/8/17.
 */
public class ChuckNorrisFact {

    private static final String CHUCK_NORRIS_FACTS_ENDPOINT = "https://api.chucknorris.io/jokes/random";

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

        Fact fact = response.readEntity(Fact.class);
        PersistToFile.persistJokeAsync(new WriteObject(fact.getId(), fact.getValue()));

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new SpeechCustomResponse(fact.getValue()));
    }
}