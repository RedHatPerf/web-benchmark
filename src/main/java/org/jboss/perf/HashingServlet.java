package org.jboss.perf;

import org.apache.commons.codec.binary.Hex;

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
    private ThreadLocal<MessageDigest> mdg = new ThreadLocal<>();

    public HashingServlet() {
        initialiseMessageDigest();
    }

    private void initialiseMessageDigest() {
        MessageDigest mdg1;
        try {
            mdg1 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            mdg1 = null;
            e.printStackTrace();
        }
        mdg.set(mdg1);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        int hash = request.hashCode();
        int length = Integer.parseInt(request.getParameter("length"));

        StringBuilder responseBuild = new StringBuilder(32 * length + 16);

        int max = length;

        updateMdg(hash);

        for (int i = 0; i < max; i++) {
            responseBuild.append(Hex.encodeHexString(getDigest()));
            updateMdg(hash);
        }

        PrintWriter out = response.getWriter();
        out.println("<p>" + responseBuild.toString() + "</p>");

    }

    private byte[] getDigest() {
        checkMdg();
        return mdg.get().digest();
    }

    private void updateMdg(int hash) {
        checkMdg();
        mdg.get().update((byte) hash);
    }

    private void checkMdg() {
        if (mdg.get() == null) {
            initialiseMessageDigest();
        }
    }
}
