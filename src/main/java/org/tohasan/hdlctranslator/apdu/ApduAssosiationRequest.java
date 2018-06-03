package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.*;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

public class ApduAssosiationRequest extends CommonFrame {

    public ApduAssosiationRequest() {
        // Lowest security level

        this.setItems(Arrays.asList(
            new AarqLength(this),  // для запроса ассоциации приложения
            // Элемент Службы Управления Ассоциацией tag [1]
            new AcseTag(this),  // для ассоциации приложения
            new AcseLength(this),  // для ассоциации приложения
            new AcseDataType(this),  // для ассоциации приложения
            new AcseDataLength(this),  // для ассоциации приложения
            new AcseValue(this),  // для ассоциации приложения
            // Конец элемента Службы Управления Ассоциацией
            // Элемент Службы Управления Ассоциацией tag [30]
            new AcseTag(this),  // для ассоциации приложения
            new AcseLength(this),  // для ассоциации приложения
            new AcseDataType(this),  // для ассоциации приложения
            new AcseDataLength(this),  // для ассоциации приложения
            new AcseValue(this),  // комплексное значение, надо разбирать дальше
            // Конец элемента Службы Управления Ассоциацией
            new RawData(this)  // не разобранные данные
        ));

        // Low level auth

//        this.setItems(Arrays.asList(
//            new Llc(this),
//            new ApduTypeField(this),
//            new AarqLength(this),  // для запроса ассоциации приложения
//            // Элемент Службы Управления Ассоциацией tag [1]
//            new AcseTag(this),  // для ассоциации приложения
//            new AcseLength(this),  // для ассоциации приложения
//            new AcseDataType(this),  // для ассоциации приложения
//            new AcseDataLength(this),  // для ассоциации приложения
//            new AcseValue(this),  // для ассоциации приложения
//            // Конец элемента Службы Управления Ассоциацией
//            // Элемент Службы Управления Ассоциацией tag [6]
//            new AcseTag(this),  // для ассоциации приложения
//            new AcseLength(this),  // для ассоциации приложения
//            new AcseDataType(this),  // для ассоциации приложения
//            new AcseDataLength(this),  // для ассоциации приложения
//            new AcseValue(this),  // для ассоциации приложения
//            // Конец элемента Службы Управления Ассоциацией
//            // Элемент Службы Управления Ассоциацией tag [10]
//            new AcseTag(this),  // для ассоциации приложения
//            new AcseLength(this),  // для ассоциации приложения
//            new AcseDataType(this),  //
//            new AcseDataLength(this),  //
//            // Конец элемента Службы Управления Ассоциацией
//            // Элемент Службы Управления Ассоциацией tag [11]
//            new AcseTag(this),  // для ассоциации приложения
//            new AcseLength(this),  // для ассоциации приложения
//            new AcseValue(this),  // для ассоциации приложения
//            // Конец элемента Службы Управления Ассоциацией
//            // Элемент Службы Управления Ассоциацией tag [12]
//            new AcseTag(this),  // для ассоциации приложения
//            new AcseLength(this),  // для ассоциации приложения
//            new AcseDataType(this),  //
//            new AcseDataLength(this),  //
//            // Конец элемента Службы Управления Ассоциацией
//            // Элемент Службы Управления Ассоциацией tag [30]
//            new AcseTag(this),  // для ассоциации приложения
//            new AcseLength(this),  // для ассоциации приложения
//            new AcseDataType(this),  // для ассоциации приложения
//            new AcseDataLength(this),  // для ассоциации приложения
//            new AcseValue(this),  // комплексное значение, надо разбирать дальше
//            // Конец элемента Службы Управления Ассоциацией
//            new RawData(this)  // не разобранные данные
//        ));
    }
}
