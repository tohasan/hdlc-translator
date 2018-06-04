package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * ParentClassID – (2 байта), определяет идентификатор родительского класса.
 *
 * author: IgorKaSan
 * date: 04.05.2018.
 */

public class ParentClassID extends CommonItem {

    public ParentClassID(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "идентификатор родительского класса - 0x" +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 2;
    }

    private int getValue() {
        return super.getBytes().get(0);
    }
}