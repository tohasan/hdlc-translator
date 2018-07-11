package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.*;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.DynamicListField;

import java.util.Arrays;

/**
 * ApduGetRequest – APDU фрейм типа Get Request, применяется для запросов по логическому имени
 *  представляет собой следующую структуру:
 *	- GetRequestMode (1 байт) - тип GetRequest (GR[]),
 *	- GetRequestVas (1 байт) - тип переменной доступа (VAS - VariableAccessSpecification) для определенного типа GetRequest (GRVAS[]),
 *	- LongBlockNumber (4 байта) - номер блока данных (для GRVAS[2] – GetRequestNext),
 *  - DynamicListField (~ байт)	- массив GetRequestElement.
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class ApduGetRequest extends CommonFrame {

    public ApduGetRequest() {
        this.setItems(Arrays.asList(
                new GetRequestMode(this),
                new GetRequestVas(this),
                new LongBlockNumber(this),
                new DynamicListField<>(this, GetRequestElement.class)
        ));
    }
}