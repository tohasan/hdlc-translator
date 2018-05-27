package org.tohasan.hdlctranslator.enums;

public enum ControlFieldType {
    SNRM((byte) 0x93),
    UA((byte) 0x75);

    private byte value;

    ControlFieldType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
