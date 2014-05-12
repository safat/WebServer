package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 5/7/14
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class HttpResponseManager {


    public static void log(Socket connection, String msg) {
        System.out.println(new Date() + " [" + connection.getInetAddress().getHostAddress() +
                ":" + connection.getPort() + "] " + msg);
    }

    public static void responseError(PrintStream pout, Socket connection,
                                     String code, String title, String msg) {
        pout.print("HTTP/1.0 " + code + " " + title + "\r\n" +
                "\r\n" +
                "<!DOCTYPE HTML\">\r\n" +
                "<TITLE>" + code + " " + title + "</TITLE>\r\n" +
                "<HEAD></HEAD><BODY>\r\n" +
                "<H1>" + title + "</H1>\r\n" + msg + "<P>\r\n" +
                "<HR></BODY></HTML>\r\n");
        log(connection, code + " " + title);
    }


    public static void sendRequestedFile(InputStream file, OutputStream out) {

        try {
            byte[] buffer = new byte[1000];
            while (file.available() > 0) {
                out.write(buffer, 0, file.read(buffer));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        try {
            out.flush();

            if (out != null)
                out.close();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public static void sendOkToClient(PrintStream pout, String path) {
        pout.print("HTTP/1.0 200 OK\r\n" +
                "Content-Type: "+ getContentType(path) +"\r\n" +
                "Date: " + new Date() + "\r\n\r\n");
        pout.flush();
    }

    private static String getContentType(String path) {

        if (path.endsWith(".html") || path.endsWith(".htm")) {
            return "text/html";
        } else if (path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".png")) {
            return "image/jpeg";
        } else {
            return "text/html";
        }
    }


}
