package main; /**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 5/6/14
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */

import util.PropertyReader;

import java.net.*;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static int PORT;
    private static int NUMBER_OF_THREAD;
    private static String WEB_ROOT_DIRECTORY;

    public static void main(String[] args) {
        setupServer();

        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

        ServerSocket socket = null;

        try {
            socket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not start server: " + e);
            System.exit(-1);
        }

        System.out.println("Main waiting on  " + PORT);


        while (true) {
            Socket connection = null;

            try {
                connection = socket.accept();

                ClientHandler handler = new ClientHandler(connection, WEB_ROOT_DIRECTORY);
                executor.execute(handler);
            } catch (IOException e) {
                System.err.println(e);
            }

        }
    }

    public static void setupServer() {
        Properties prop = PropertyReader.getProperty("prop/server.properties");

        PORT = Integer.parseInt(prop.getProperty("port"));
        NUMBER_OF_THREAD = Integer.parseInt(prop.getProperty("nthread"));
        WEB_ROOT_DIRECTORY = prop.getProperty("homeDirectory");
    }
}
