package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * ReadResponseFormat – (1 байт), специфицирует тип/формат ответа:
 * - ReadResponse [2] : Data-Block-Result - ответ на запрос осуществляется поблочно
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ReadResponseFormat extends CommonItem {

    public ReadResponseFormat(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тип переменной доступа (ReadResponseFormat) ReadResponse[" +  Integer.toString(getValue()) + "]";
    }

    @Override
    public int size() {
        return 1;
    }
}