package org.tohasan.hdlctranslator.common;

/**
 * author: IgorKaSan
 * date: 04.03.2018.
 */
public class Preprocessor {
    private String message;

    public Preprocessor(String message) {
        this.message = message;
    }

    public byte[] getBytes() {
        String message = removeSpaces(this.message);
        return hexStringToByteArray(message);
    }

    public static String removeSpaces(String string) {
        return string.replaceAll(" ", "");
    }

    public static byte[] hexStringToByteArray(String string) {
        int len = string.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(string.charAt(i), 16) << 4)
                    + Character.digit(string.charAt(i + 1), 16));
        }
        return data;
    }

}
