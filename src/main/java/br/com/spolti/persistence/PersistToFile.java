package br.com.spolti.persistence;

import java.io.*;
import java.util.concurrent.CompletableFuture;

public class PersistToFile {

    private static File file = new File(System.getProperty("user.home") + "/chuck-jokes.txt");


    /**
     * Asynchronously write the given object to a file
     *
     * @param writeObject
     */
    public static void persistJokeAsync(WriteObject writeObject) {
        CompletableFuture.runAsync(() -> write(writeObject)).toCompletableFuture();
    }

    /**
     * Write the given object in text format to a file
     *
     * @param writeObject
     */
    private static void write(WriteObject writeObject) {

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
            System.out.println("Object persisted --> " + writeObject.toString());
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

}