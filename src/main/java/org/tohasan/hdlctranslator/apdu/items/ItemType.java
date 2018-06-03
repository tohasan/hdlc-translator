package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * ItemType – (1 байт), специфицирует тип данных (Enumeration - перечисление) элемента данных в последовательности.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ItemType extends CommonItem {

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
        return super.getBytes().get(0);
    }
}