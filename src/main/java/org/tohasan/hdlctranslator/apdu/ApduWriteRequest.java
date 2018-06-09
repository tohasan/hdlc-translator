package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.Quantity;
import org.tohasan.hdlctranslator.apdu.items.ShortName;
import org.tohasan.hdlctranslator.apdu.items.Vas;
import org.tohasan.hdlctranslator.apdu.items.WriteRequestElement;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.DynamicListField;

import java.util.Arrays;

/**
 * ApduWriteRequest – APDU фрейм типа Write Request, применяется для запросов записи по короткому имени
 *  представляет собой следующую структуру:
 *	- Quantity (1 байт) -  количество элементов в запросе,
 *  - VAS (1 байт) - тип переменной доступа (VAS - VariableAccessSpecification),
 *  - ShortName (2 байт) – короткое имя записываемого объекта (атрибута объекта),
 *	- Quantity (1 байт) -  количество элементов в блоке данных,
 *  - DynamicListField (~ байт)	- массив WriteRequestElement.
 *
 * author: IgorKaSan
 * date: 09.06.2018.
 */
public class ApduWriteRequest extends CommonFrame {

    public ApduWriteRequest() {
        this.setItems(Arrays.asList(
                new Quantity(this),
                new Vas(this),
                new ShortName(this),
                new Quantity(this),
                new DynamicListField<>(this, WriteRequestElement.class)
        ));
    }
}
