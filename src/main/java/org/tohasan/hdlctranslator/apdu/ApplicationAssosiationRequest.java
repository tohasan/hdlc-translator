package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.*;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.RawData;

import java.util.Arrays;

/**
 * Application Assosiation Request – запрос ассоциации приложения с применением самого низкого уровня безопасности
 *
 * author: IgorKaSan
 * date: 04.05.2018.
 */
public class ApplicationAssosiationRequest extends CommonFrame {

    public ApplicationAssosiationRequest() {
        // Lowest security level – запрос ассоциации приложения с применением самого низкого уровня безопасности

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
