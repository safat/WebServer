package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 5/7/14
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RequestHandler {
    abstract void handleRequest(String requestStr,BufferedReader in,  Socket socket, String homeDir, int contentLength);

}
