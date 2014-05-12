package main;

/**
 * Created with IntelliJ IDEA.
 * User: shakhawat.hossain
 * Date: 5/7/14
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestValidator {

    private RequestValidator() {
    }

    private static boolean isValidRequest(String request) {
        if (request.endsWith("HTTP/1.0") || request.endsWith("HTTP/1.1")) {
            return true;
        }

        return false;
    }

    public static boolean isPostRequest(String request) {
        if (isValidRequest(request))
            if (request.startsWith("POST")) {
                return true;
            }

        return false;
    }

    public static boolean isGetRequest(String request) {
        if (isValidRequest(request))
            if (request.startsWith("GET"))
                return true;

        return false;
    }
}
