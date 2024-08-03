package org.jboss.perf;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Created by johara on 22/04/16.
 */
@WebServlet("/test/HashingTest/")
public class HashingServlet extends HttpServlet {

    private static final char[] table = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
    private final ThreadLocal<MessageDigest> mdg = new ThreadLocal<>();

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
            responseBuild.append(bytesToHexString(getDigest()));
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

    private static String bytesToHexString(final byte[] bytes) {
        final StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            builder.append(table[b >> 4 & 0x0f]).append(table[b & 0x0f]);
        }
        return builder.toString();
    }
}
