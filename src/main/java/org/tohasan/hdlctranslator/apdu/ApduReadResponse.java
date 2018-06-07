package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.*;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.DynamicListField;

import java.util.Arrays;

public class ApduReadResponse extends CommonFrame {

    public ApduReadResponse() {
        this.setItems(Arrays.asList(
            new Quantity(this),  // для запроса на чтение и ответа на запрос
            // Заголовок блока данных
            new ReadResponseFormat(this),  // для ответа на запрос на чтение
            new LastBlock(this),  // для ответа на запрос на чтение
            new BlockNumber(this),  // для ответа на запрос на чтение
            new DataBlockLength(this),  // для ответа на запрос на чтение
            new Quantity(this),  // для ответа на запрос на чтение
            // конец заголовка блока (длина блока данных рассматривается в составе заголовка)
            new ResultDiagnosticFlag(this),  // для ответа на запрос на чтение
//            new ItemType(this),  // для ответа на запрос на чтение
//            new ItemLength(this),  // для ответа на запрос на чтение
//            new ItemValue(this),  // для ответа на запрос на чтение
//            new RawData(this)  // неразобранные данные
                new DynamicListField<>(this, ReadResponseElement.class)
        ));
    }
}
