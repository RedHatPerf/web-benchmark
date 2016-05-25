package org.jboss.perf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by johara on 22/04/16.
 */
public class HashingServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        String hash = String.valueOf(request.hashCode());
        StringBuilder responseBuild = new StringBuilder();

        try {
            MessageDigest mdg = MessageDigest.getInstance("MD5");

//            Random randomGenerator = new Random();

//            int max = randomGenerator.nextInt(1000);
            int max = 10; //randomGenerator.nextInt(10);

            mdg.update(hash.getBytes());

            for(int i = 0; i < max; i++){
                String hex = getHex(mdg.digest());
                responseBuild.append( hex );
                mdg.update(hex.getBytes());
            }

            hash = responseBuild.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<p>" + hash + "</p>");

    }

    private String getHex(byte[] byteData) {

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}
