package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * APDU type – (1 байт), специфицирует Блок данных COSEM протокола прикладного уровня (COSEM APDU – COSEM Application Layer Protocol Data Unit):
 * - Запрос на чтение (Read Request– APDU [5]) – 0x05
 * - Ответ на запрос чтения (Read Response– APDU [12]) – 0x0C
 * - Запрос на запись (Write Request– APDU [6]) – 0x06
 * - Ответ на запрос записи (Write Response– APDU [13]) – 0x0D
 * - Запрос ассоциации приложения (Application Association Request – AARQ – APDU [96]) – 0x60
 * - Ответ на запрос ассоциации приложения (Application Association Response – AARE – APDU [97]) – 0x61
 * - Запрос получения данных (Get-Request – APDU [192]) – 0xC0
 * - Ответ на запрос получения данных (Get-Response – APDU [196] – 0xC4)
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ApduTypeField extends CommonItem {
    final static int READ_REQUEST = 0x05;
    final static int READ_RESPONSE = 0x0C;
    final static int WRITE_REQUEST = 0x06;
    final static int WRITE_RESPONSE = 0x0D;
    final static int APPLICATION_ASSOCIATION_REQUEST = 0x60;
    final static int APPLICATION_ASSOCIATION_RESPONSE = 0x61;
    final static int GET_REQUEST = 0xC0;
    final static int GET_RESPONSE = 0xC4;

    public ApduTypeField(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тип APDU пакета (APDU type) APDU[" +  Integer.toString(getValue()) + "]";
    }

    @Override
    public int size() {
        return 1;
    }

    private int getValue() {
        return super.getBytes().get(0) & 255;   // & 255 исправляет отрицательное число, возвращаемое getBytes()
    }
}
