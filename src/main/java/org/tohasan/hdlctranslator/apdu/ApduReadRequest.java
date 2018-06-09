package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.Quantity;
import org.tohasan.hdlctranslator.apdu.items.ReadRequestElement;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.DynamicListField;

import java.util.Arrays;

/**
 * ApduReadRequest – APDU фрейм типа Read Request, применяется для запросов по короткому имени
 *  представляет собой следующую структуру:
 *	- Quantity (1 байт) -  количество элементов в запросе,
 *  - DynamicListField (~ байт)	- массив ReadRequestElement.
 *
 * author: IgorKaSan
 * date: 04.05.2018.
 */
public class ApduReadRequest extends CommonFrame {

    public ApduReadRequest() {
        this.setItems(Arrays.asList(
            new Quantity(this),
            new DynamicListField<>(this, ReadRequestElement.class)
        ));
    }
}
