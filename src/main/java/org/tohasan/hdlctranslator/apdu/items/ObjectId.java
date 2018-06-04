package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * ObjectId – (6 байт), определяет идентификатор объекта (ОБИС код).
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class ObjectId extends CommonItem {

    public ObjectId(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "идентификатор объекта (OBIS код) - 0x" +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 6;
    }

    private int getValue() {
        return super.getBytes().get(0);
    }
}
