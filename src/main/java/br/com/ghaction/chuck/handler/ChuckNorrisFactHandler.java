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

package br.com.ghaction.chuck.handler;

import br.com.ghaction.chuck.pojo.Fact;
import br.com.ghaction.chuck.pojo.FirstSimple;
import br.com.ghaction.chuck.pojo.IntentFullfillment;
import br.com.ghaction.chuck.pojo.Next;
import br.com.ghaction.chuck.pojo.Prompt;
import br.com.ghaction.chuck.pojo.SpeechCustomResponse;
import br.com.ghaction.persistence.PersistToFile;
import br.com.ghaction.persistence.WriteObject;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

@Path("/fact")

public class ChuckNorrisFactHandler {

    private final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    private final String CHUCK_NORRIS_FACTS_ENDPOINT = "https://api.chucknorris.io/jokes/random";

    @Inject
    PersistToFile persist;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getFact(IntentFullfillment requestPayload) {
        Response response = null;

        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(CHUCK_NORRIS_FACTS_ENDPOINT);
            response = target.request().get();

            if (response.getStatus() != 200) {
                log.warning("Failed to connect in the endpoint " + CHUCK_NORRIS_FACTS_ENDPOINT + ", status code is: " + response.getStatus());
            }
            Fact fact = response.readEntity(Fact.class);
            persist.persistJokeAsync(new WriteObject(fact.getId(), fact.getValue()));

            // prepare response
            SpeechCustomResponse speechCustomResponse = new SpeechCustomResponse();
            speechCustomResponse.setSession(requestPayload.getSession());
           // speechCustomResponse.setScene(requestPayload.getScene());
            //speechCustomResponse.getScene().setNext(new Next("actions.scene.START_CONVERSATION"));
            Prompt prompt = new Prompt();
            FirstSimple fs = new FirstSimple(fact.getValue());
            prompt.setFirstSimple(fs);
            speechCustomResponse.setPrompt(prompt);

            log.fine(speechCustomResponse.toString());

            return Response.ok(speechCustomResponse).build();
        } catch (final Exception e) {
            return Response.ok("Failed to retrieve chuck fact: " + e.getMessage()).build();
        } finally {
            response.close();
        }
    }
}