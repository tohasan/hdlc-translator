package org.tohasan.hdlctranslator.apdu.enums;

public enum ReadResponse {
    DATA((byte) 0x00),
    DATA_ACCESS_ERROR((byte) 0x01),
    DATA_BLOCK_RESULT((byte) 0x02),
    BLOCK_NUMBER((byte) 0x0D);

    private byte value;

    ReadResponse(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public static ReadResponse getByValue(byte value) {
        for (ReadResponse type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
