package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * DataBlockLength – (1 байт), специфицирует длину блока данных в байтах.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class DataBlockLength extends CommonItem {

    public DataBlockLength(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "длина блока данных в байтах (DataBlockLength) - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 1;
    }

    private int getValue() {
        return super.getBytes().get(0);
    }
}