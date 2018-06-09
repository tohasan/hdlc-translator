package org.tohasan.hdlctranslator.apdu.enums;

/**
 * AcseTagType (ACSE - Association Control Service Element) содержит
 *  перечисление типов элементов сервиса управления ассоциацией.
 *
 * author: IgorKaSan
 * date: 01.06.2018.
 */
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
