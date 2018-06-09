package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.*;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.DynamicListField;

import java.util.Arrays;

/**
 * ApduGetResponse – APDU фрейм типа Get Response, применяется для ответов на запросы по логическому имени
 *  представляет собой следующую структуру:
 *	- GetResponseType (1 байт) - тип GetResponse (GRE[2]),
 *	- GetResponseVas (1 байт) - тип переменной доступа (VAS - VariableAccessSpecification) для определенного типа GetResponse (GREVAS),
 *	- LastBlock flag (1 байт) - признак последнего блока,
 *  - LongBlockNumber (4 байта) - номер блока данных,
 *	- ResultDiagnosticFlag (1 байт) - значение диагностики источника результата (0x00 - success),
 *	- DataBlockLength (1 байт) - длина блока данных в байтах,
 *  - DynamicListField (~ байт)	- массив GetResponseElement.
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class ApduGetResponse extends CommonFrame {

    public ApduGetResponse() {
        this.setItems(Arrays.asList(
                new GetRequestType(this),
                new GetRequestVas(this),
                new LastBlock(this),
                new LongBlockNumber(this),
                new ResultDiagnosticFlag(this),
                new DataBlockLength(this),
                new DynamicListField<>(this, ReadResponseElement.class)
        ));
    }
}