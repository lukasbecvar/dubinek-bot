package xyz.becvar.discord.botbase.utils;

import java.io.IOException;
import java.net.*;

/*
 * The Server status getter for java applications and webpages
*/

public class ServerStatusUtils {

    //Get true if website running
    public static boolean checkIfURLExists(String targetUrl) {
        HttpURLConnection httpUrlConn;
        try {
            httpUrlConn = (HttpURLConnection) new URL(targetUrl).openConnection();

            // A HEAD request is just like a GET request, except that it asks
            // the server to return the response headers only, and not the
            // actual resource (i.e. no message body).
            // This is useful to check characteristics of a resource without
            // actually downloading it,thus saving bandwidth. Use HEAD when
            // you don't actually need a file's contents.
            httpUrlConn.setRequestMethod("HEAD");

            // Set timeouts in milliseconds
            httpUrlConn.setConnectTimeout(30000);
            httpUrlConn.setReadTimeout(30000);

            return (httpUrlConn.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    //Return true if service running (Used for minecraft server etc)
    public static boolean hostAvailabilityCheck(String SERVER_ADDRESS, int TCP_SERVER_PORT) {
        try (Socket s = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT)) {
            return true;
        } catch (IOException ex) {
        }
        return false;
    }
}
