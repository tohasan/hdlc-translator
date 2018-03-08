package org.tohasan.hdlctranslator.common;

/**
 * author: IgorKaSan
 * date: 04.03.2018.
 */
public class Postprocessor {
    private byte[] byteArray;

    public Postprocessor(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public String getString () {
        String outputString = byteArraytoHexStr(this.byteArray).toString();
        return outputString;
    }

    public static StringBuffer byteArraytoHexStr(byte[] byteArray) {
        StringBuffer stringBuffer = new StringBuffer();

        for (byte curByte : byteArray) {
            stringBuffer.append(toHexChar(curByte >>> 4 & 15));
            stringBuffer.append(toHexChar(curByte & 15));
        }

        return stringBuffer;
    }

    private static char toHexChar(int hexval) {
        return hexval >= 0 && hexval <= 9 ? (char) (hexval + 48) : (char) (65 + (hexval - 10));
    }

}
