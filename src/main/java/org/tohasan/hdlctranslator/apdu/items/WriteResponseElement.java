package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Collections;

/**
 * WriteResponseElement - структура WriteResponse запроса, определяющая запрашиваемый элемент:
 * -	ResultDiagnosticFlag (1 байт) значение диагностики источника результата (0x00 - success),
 * <p>
 * author: IgorKaSan
 * date: 09.06.2018.
 */
public class WriteResponseElement extends CommonFrame {

    public WriteResponseElement() {
        this.setItems(Collections.singletonList(
            new ResultDiagnosticFlag(this)
        ));
    }
}