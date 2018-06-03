package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * ItemLength – (1 байт), специфицирует длину элемента данных в последовательности.
 *  используется не для всех типов данных (используется для: байтовая строка, массив, структура и т.д.)
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ItemLength extends CommonItem {

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
        return super.getBytes().get(0);
    }
}