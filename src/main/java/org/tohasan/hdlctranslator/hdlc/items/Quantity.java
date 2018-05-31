package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * Quantity – (1 байт), специфицирует количество элементов указанных в APDU кадре (блоке).
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class Quantity extends HdlcItem {

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
        int code;
        code = super.getBytes().get(0);
        return code;
    }
}