package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.ApduContent;
import org.tohasan.hdlctranslator.apdu.items.ApduTypeField;
import org.tohasan.hdlctranslator.apdu.items.Llc;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

/**
 * ApduFrame – определяет структуру APDU кадра:
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
public class ApduFrame extends CommonFrame {

    public ApduFrame() {
        this.setItems(Arrays.asList(
            new Llc(this),
            new ApduTypeField(this),
            new ApduContent(this)
        ));
    }
}
