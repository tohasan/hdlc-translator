package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ReadResponseFormat – (1 байт), специфицирует тип/формат ответа:
 * - ReadResponse [2] : Data-Block-Result - ответ на запрос осуществляется по-блочно
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */


public class ReadResponseFormat extends HdlcItem {

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

    private int getValue() {
        int code;
        code = super.getBytes().get(0);
        return code;
    }
}