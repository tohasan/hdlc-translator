package org.tohasan.hdlctranslator.apdu.enums;

public enum DataType {
    ARRAY((byte) 0x01),
    STRUCTURE((byte) 0x02),
    OCTET_STRING((byte) 0x09),
    VISIBLE_STRING((byte) 0x0A),
    LONG((byte) 0x10),
    UNSIGNED((byte) 0x11),
    LONG_UNSIGNED((byte) 0x12),
    INTEGER((byte) 0x15);

    private byte value;

    DataType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
