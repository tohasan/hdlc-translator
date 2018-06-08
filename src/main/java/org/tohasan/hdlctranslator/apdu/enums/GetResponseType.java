package org.tohasan.hdlctranslator.apdu.enums;

public enum GetResponseType {
    GET_REQUEST_NORMAL((byte) 0x01),
    GET_REQUEST_NEXT((byte) 0x02),
    GET_REQUEST_WITH_LIST((byte) 0x03);

    private byte value;

    GetResponseType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
