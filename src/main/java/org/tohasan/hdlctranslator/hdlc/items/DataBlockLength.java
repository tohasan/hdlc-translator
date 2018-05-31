package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * DataBlockLength – (1 байт), специфицирует длину блока данных в байтах.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class DataBlockLength extends HdlcItem {

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
        int code;
        code = super.getBytes().get(0);
        return code;
    }
}