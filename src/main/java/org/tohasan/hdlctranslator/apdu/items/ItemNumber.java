package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ItemNumber – (1 байт), специфицирует номер элемента данных в последовательности.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class ItemNumber extends HdlcItem {

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
        int code;
        code = super.getBytes().get(0);
        return code;
    }
}