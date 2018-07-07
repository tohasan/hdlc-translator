package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.Quantity;
import org.tohasan.hdlctranslator.apdu.items.WriteResponseElement;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.DynamicListField;

import java.util.Arrays;

/**
 * ApduWriteResponse – APDU фрейм типа Write Response, применяется для ответов на запросы записи по короткому имени
 * представляет собой следующую структуру:
 * - Quantity (1 байт) -  количество элементов в запросе,
 * - DynamicListField (~ байт)	- массив WriteResponseElement.
 * <p>
 * author: IgorKaSan
 * date: 09.06.2018.
 */
public class ApduWriteResponse extends CommonFrame {

    public ApduWriteResponse() {
        this.setItems(Arrays.asList(
            new Quantity(this),
            new DynamicListField<>(this, WriteResponseElement.class)
        ));
    }
}
