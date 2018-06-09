package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

/**
 * ACSE Tag (Association Control Service Element) специфицирует  Элемент Сервиса Управления Ассоциацией,
 *  структура (набор полей) определяется назначением (типом - перечисление AcseTagType) элемента управления:
 *  - AcseTagName (1 байт) - наименование тега элемента управления ассоциацией (тип ACSE),
 *	- AcseLength (1 байт) - длина элемента службы управления ассоциацией в байтах,
 *	- AcseDataType (1 байт) - тип данных закодированного значения ACSE,
 *	- AcseDataLength (1 байт) - длина значения элемента службы управления ассоциацией в байтах,
 *	- AcseValue (~ байт, определяется AcseDataLength) - значения элемента службы управления ассоциацией.
 *
 * author: IgorKaSan
 * date: 01.06.2018.
 */
public class AcseTag extends CommonFrame {

    public AcseTag() {
        this.setItems(Arrays.asList(
            new AcseTagName(this),
            new AcseLength(this),
            new AcseDataType(this),
            new AcseDataLength(this),
            new AcseValue(this)
        ));
    }
}
