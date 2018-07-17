package org.tohasan.hdlctranslator.apdu.enums;

public enum DataType {
    NULL_DATA((byte) 0x00),
    ARRAY((byte) 0x01),
    STRUCTURE((byte) 0x02),
    BOOLEAN((byte) 0x03),
    BIT_STRING((byte) 0x04),
    DOUBLE_LONG_UNSIGNED((byte) 0x06),
    OCTET_STRING((byte) 0x09),
    VISIBLE_STRING((byte) 0x0A),
    LONG((byte) 0x10),
    UNSIGNED((byte) 0x11),
    LONG_UNSIGNED((byte) 0x12),
    INTEGER((byte) 0x0F),
    ENUM((byte) 0x16),
    UNKNOWN_DATA_TYPE((byte) 0xFF);

    private byte value;

    DataType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public static DataType getByValue(byte value) {
        for (DataType type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
