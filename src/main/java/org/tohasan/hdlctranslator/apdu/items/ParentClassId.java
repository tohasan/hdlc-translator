package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * ParentClassId – (2 байта), определяет идентификатор родительского класса.
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class ParentClassId extends CommonItem {

    public ParentClassId(Frame frame) {
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

    public int getValue() {
        return super.getBytes().get(1) << 8 | super.getBytes().get(0);
    }
}