package org.tohasan.hdlctranslator.apdu.enums;

public enum VasType {
    VARIABLE_NAME((byte) 0x02),
    PARAMETERIZED_ACCESS((byte) 0x04),
    BLOCK_NUMBER_ACCESS((byte) 0x06);

    private byte value;

    VasType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
