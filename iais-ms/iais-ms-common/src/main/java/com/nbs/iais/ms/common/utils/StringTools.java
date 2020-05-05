package com.nbs.iais.ms.common.utils;

import com.nbs.iais.ms.common.exceptions.SystemSetupException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class StringTools {

    public static String uniqueString() {

        try {
            // Initialize SecureRandom
            // This is a lengthy operation, to be done only upon
            // initialization of the application
            final SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

            // generate a random number
            final String randomNum = prng.nextInt() + "";

            // get its digest
            final MessageDigest sha = MessageDigest.getInstance("SHA-1");
            final byte[] result = sha.digest(randomNum.getBytes());

            return hexEncode(result);
        } catch (final NoSuchAlgorithmException e) {
            throw new SystemSetupException("Missing algorithm sha");
        }
    }


    public static String uniqueString(String s) {
        try {
            final MessageDigest sha = MessageDigest.getInstance("SHA-1");
            final byte[] result = sha.digest(s.getBytes());

            return hexEncode(result);
        } catch (final NoSuchAlgorithmException e) {
            throw new SystemSetupException("Missing algorithm sha");
        }
    }

    public static String base64UrlEncode(final String input) {
        final Base64.Encoder encoder = Base64.getUrlEncoder();
        final byte[] encodedBytes = encoder.encode(input.getBytes());
        return new String(encodedBytes);
    }

    public static String base64UrlDecode(final String encodedString) {
        final Base64.Decoder decoder = Base64.getUrlDecoder();
        final byte[] decodedBytes = decoder.decode(encodedString);
        return new String(decodedBytes);
    }

    public static String hash(final String str) {

        // get digest of str
        try {
            final MessageDigest sha = MessageDigest.getInstance("SHA-1");
            final byte[] result = sha.digest(str.getBytes());
            return hexEncode(result);
        } catch (final NoSuchAlgorithmException e) {
            throw new SystemSetupException("Missing algorithm sha");
        }

    }

    /**
     * The byte[] returned by MessageDigest does not have a nice textual
     * representation, so some form of encoding is usually performed.
     * <p>
     * This implementation follows the example of David Flanagan's book "Java In A
     * Nutshell", and converts a byte array into a String of hex characters.
     * <p>
     * Another popular alternative is to use a "Base64" encoding.
     */
    private static String hexEncode(final byte[] aInput) {
        final StringBuilder result = new StringBuilder();
        final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (final byte b : aInput) {
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
        }
        return result.toString();
    }

    public static double stringToDouble(final String text) {

        if (text == null) {
            return 0d;
        }
        return Double.parseDouble(text);
    }


    public static boolean isEmpty(final String text) {
        return text == null || "".equals(text.trim());
    }

    public static boolean isNotEmpty(final String text) {
        return text != null && !"".equals(text.trim());
    }


    public static String noNull(final String tempNote) {

        if (tempNote != null) {
            return tempNote;
        }

        return "";
    }

}

