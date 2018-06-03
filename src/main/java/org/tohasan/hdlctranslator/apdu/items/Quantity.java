package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * Quantity – (1 байт), специфицирует количество элементов указанных в APDU кадре (блоке).
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class Quantity extends CommonItem {

    public Quantity(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "количество элементов в последовательности (Quantity) - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 1;
    }

    private int getValue() {
        return super.getBytes().get(0);
    }
}