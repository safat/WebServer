package main;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 5/6/14
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientHandler implements Runnable {

    private static String WEB_ROOT;

    private Socket connection;

    public ClientHandler(Socket socket, String WEB_ROOT) {
        this.connection = socket;
        this.WEB_ROOT = WEB_ROOT;
    }

    @Override
    public void run() {
        System.out.println("Request from " + connection.getInetAddress());

        BufferedReader in = null;
        OutputStream out = null;
        PrintStream pout = null;
        String request = null;

        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new BufferedOutputStream(connection.getOutputStream());
            pout = new PrintStream(out);

            request = RequestReader.readIncomingLine(in);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        int postConentLength = 0;

        while (true) {
            String requests = RequestReader.readIncomingLine(in);

            if (requests == null || requests.length() == 0)
                break;

            if (requests.startsWith("Content-Length:")) {
                postConentLength = Integer.parseInt(requests.substring(15).trim());
            }


        }

        if (request != null) {
            if (RequestValidator.isPostRequest(request)) {
                RequestHandler requestHandler = new PostRequestHandler();
                requestHandler.handleRequest(request, in, connection, WEB_ROOT, postConentLength);

            } else if (RequestValidator.isGetRequest(request)) {

                RequestHandler requestHandler = new GetRequestHandler();
                requestHandler.handleRequest(request, in, connection, WEB_ROOT, 0);

            } else {

                HttpResponseManager.responseError(pout, connection, "400", "Bad Request",
                        "Your browser sent a request that " +
                                "this server could not understand.");
            }

            try {
                if (connection != null) connection.close();
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                try {
                    if (out != null) out.close();
                    if (pout != null) pout.close();
                } catch (IOException e) {

                }
            }

        }
    }

}
