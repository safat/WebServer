package main;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 5/7/14
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetRequestHandler implements RequestHandler {


    @Override
    public void handleRequest(String request,BufferedReader in, Socket connection, String homeDir, int contentLength) {

        String requestFilePath = request.substring(4, request.length() - 9).trim();

        OutputStream out = null;
        PrintStream pout = null;


        try {
            out = new BufferedOutputStream(connection.getOutputStream());
            pout = new PrintStream(out);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        if (requestFilePath.indexOf("..") != -1 || requestFilePath.indexOf("/.ht") != -1) {

            HttpResponseManager.responseError(pout, connection, "403", "Forbidden",
                    "You don't have permission to access the requested URL.");
        } else {

            String path = homeDir + "/" + requestFilePath;

            File f = new File(path);

            if (f.isDirectory()) {
                path = path + "index.html";
                f = new File(path);
            }
            try {
                InputStream file = new FileInputStream(f);

                HttpResponseManager.sendOkToClient(pout, path);

                HttpResponseManager.sendRequestedFile(file, out);

                HttpResponseManager.log(connection, "200 OK");

            } catch (FileNotFoundException e) {

                HttpResponseManager.responseError(pout, connection, "404", "Not Found",
                        "The requested URL was not found on this server.");
            }

        }

    }


}
