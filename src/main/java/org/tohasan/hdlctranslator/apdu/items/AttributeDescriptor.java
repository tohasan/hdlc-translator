package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * AttributeDescriptor – (1 байт), определяет конец описания атрибута.
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class AttributeDescriptor extends CommonItem {

    public AttributeDescriptor(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "флаг завершения описания атрибута - 0x" +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 1;
    }

    private int getValue() {
        return super.getBytes().get(0);
    }
}