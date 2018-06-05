package org.tohasan.hdlctranslator.apdu.enums;

public enum  AcseTagType {
    MECHANISM_NAME((byte) 0x8B);

    private byte value;

    AcseTagType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
