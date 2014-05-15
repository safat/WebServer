package util;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 5/7/14
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestReader  {

    public static Character readIncomingChar(BufferedReader in) {
        try {
            return (char) in.read();
        } catch (IOException e) {

        }
        return null;
    }

    public static String readIncomingLine(BufferedReader in) {
        try {
            return in.readLine();
        } catch (IOException e) {

        }
        return null;
    }

}
