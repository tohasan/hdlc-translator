package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.*;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.DynamicListField;

import java.util.Arrays;

/**
 * ApduReadResponse – APDU фрейм типа Read Response, применяется для ответов на запросы по короткому имени
 *  представляет собой следующую структуру:
 *	- Quantity (1 байт) - количество элементов в последовательности,
 *	- ReadResponseFormat (1 байт) - формат/тип ответа на запрос (например: [2] – data-block-result),
 *	- LastBlock flag (1 байт) - признак последнего блока,
 *  - BlockNumber (2 байта) - номер блока данных,
 *	- DataBlockLength (1 байт) - длина блока данных в байтах,
 *	- Quantity (1 байт) - количество элементов в блоке данных,
 *  - ResultDiagnosticFlag (1 байт) - значение диагностики источника результата (0x00 - success),
 *  - DynamicListField (~ байт)	- массив ReadResponseElement.
 *
 * author: IgorKaSan
 * date: 04.05.2018.
 */
public class ApduReadResponse extends CommonFrame {

    public ApduReadResponse() {
        this.setItems(Arrays.asList(
            new Quantity(this),
            // Заголовок блока данных
            new ReadResponseFormat(this),
            new LastBlock(this),
            new BlockNumber(this),
            new DataBlockLength(this),
            new Quantity(this),
            // конец заголовка блока (длина блока данных рассматривается в составе заголовка)
            new ResultDiagnosticFlag(this),
            new DynamicListField<>(this, ReadResponseElement.class)
        ));
    }
}
