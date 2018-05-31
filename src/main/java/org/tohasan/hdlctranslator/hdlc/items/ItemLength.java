package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ItemLength – (1 байт), специфицирует длину элемента данных в последовательности.
 *  используется не для всех типов данных (используется для: байтовая строка, массив, структура и т.д.)
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class ItemLength extends HdlcItem {

    public ItemLength(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "длина элемента данных в байтах (ItemLength) - " +  Integer.toString(getValue());
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