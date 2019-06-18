package green.tools;

import green.entity.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Tools {

    private static final int TOKEN_SIZE = 50;
    private static final String SOURCE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final long MILIS_IN_60_MINS = 3600000;

    public static String generateMD5(String s) {
        final String original = s;
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(original.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateSessionToken() {
        final SecureRandom rnd = new SecureRandom();
        final StringBuilder sb = new StringBuilder(TOKEN_SIZE);
        for (int i = 0; i < TOKEN_SIZE; i++)
            sb.append(SOURCE.charAt(rnd.nextInt(SOURCE.length())));
        return sb.toString();
    }

    public static boolean isSessionValid(Session session) {
        if (session == null) {
            return false;
        }
        final long milis = session.getAddDate().getTime();
        return Math.abs(milis - System.currentTimeMillis()) <= MILIS_IN_60_MINS;
    }
}
