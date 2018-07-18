package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * AttributeDescriptor – (1 байт), определяет конец описания атрибута.
 * если значение 0x00 - конец описания атрибута объекта;
 * если значение 0x01 - следует описание параметров для атрибута объекта.
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
}