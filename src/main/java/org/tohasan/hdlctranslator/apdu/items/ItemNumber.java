package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * ItemNumber – (1 байт), специфицирует номер элемента данных в последовательности.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ItemNumber extends CommonItem {

    public ItemNumber(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "номер элемента данных в последовательности (ItemNumber) - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 1;
    }

    private int getValue() {
        return super.getBytes().get(0);
    }
}