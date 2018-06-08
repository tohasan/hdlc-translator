package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * AttributeId – (1 байт), определяет идентификатор атрибута объекта.
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class AttributeId extends CommonItem {

    public AttributeId(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "идентификатор атрибута - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 1;
    }
}