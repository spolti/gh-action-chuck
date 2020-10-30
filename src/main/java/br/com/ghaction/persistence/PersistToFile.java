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

package br.com.ghaction.persistence;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@ApplicationScoped
public class PersistToFile {

    private final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    private File file = new File(System.getProperty("user.home") + "/chuck-jokes.txt");


    /**
     * Asynchronously write the given object to a file
     *
     * @param writeObject
     */
    public void persistJokeAsync(WriteObject writeObject) {
        CompletableFuture.runAsync(() -> write(writeObject)).toCompletableFuture();
    }

    /**
     * Write the given object in text format to a file
     *
     * @param writeObject
     */
    private void write(WriteObject writeObject) {

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(writeObject.toString());
            log.info("Object persisted at [" + file +  "] --> " + writeObject.toString());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}