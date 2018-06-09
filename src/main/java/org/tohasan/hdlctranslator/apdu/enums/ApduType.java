package org.tohasan.hdlctranslator.apdu.enums;

/**
 * ApduType содержит перечисление типов APDU пакетов.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public enum ApduType {
    READ_REQUEST((byte) 0x05),
    READ_RESPONSE((byte) 0x0C),
    WRITE_REQUEST((byte) 0x06),
    WRITE_RESPONSE((byte) 0x0D),
    APPLICATION_ASSOCIATION_REQUEST((byte) 0x60),
    APPLICATION_ASSOCIATION_RESPONSE((byte) 0x61),
    GET_REQUEST((byte) 0xC0),
    GET_RESPONSE((byte) 0xC4);

    private byte value;

    ApduType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
