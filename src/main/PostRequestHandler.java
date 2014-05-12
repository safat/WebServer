package main;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/*


/**
* Created with IntelliJ IDEA.
* User: shakhawat.hossain
* Date: 5/7/14
* Time: 12:44 PM
* To change this template use File | Settings | File Templates.
*/
public class PostRequestHandler implements RequestHandler {

    @Override
    public void handleRequest(String request, BufferedReader in , Socket connection, String homeDir, int postContentLength) {

        PrintStream pout = null;
        try {
            pout = new PrintStream(new BufferedOutputStream(connection.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        HttpResponseManager.sendOkToClient(pout, " ");

        StringBuilder postReqStr = new StringBuilder("");

        for (int i = 0; i < postContentLength; i++) {
            postReqStr.append(RequestReader.readIncomingChar(in));
        }

        HttpResponseManager.sendOkToClient(pout, " ");

        Map<String, String> postMap = getPostDataMap(postReqStr.toString());
        System.out.println("Post  " + postMap);

        System.out.println("\n\n");

    }


    public static Map<String, String> getPostDataMap(String postString) {
        Map<String, String> postDataMap = new HashMap<String, String>();

        for (String postReq : postString.split("&")) {
            String[] nameValue = postReq.split("=");

            if (nameValue.length > 1) {
                postDataMap.put(nameValue[0], nameValue[1]);
            } else {
                postDataMap.put(nameValue[0], null);
            }
        }

        return postDataMap;
    }
}