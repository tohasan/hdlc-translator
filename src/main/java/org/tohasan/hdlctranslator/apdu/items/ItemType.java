package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ItemType – (1 байт), специфицирует тип данных (Enumeration - перечисление) элемента данных в последовательности.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class ItemType extends HdlcItem {

    public ItemType(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тип данных элемента данных в последовательности (ItemType) - DataType[" +  Integer.toString(getValue()) + "]";
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