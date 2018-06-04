package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * BlockNumber – (2 байта), определяет номер блока данных:
 *
 * author: IgorKaSan
 * date: 31.05.2018.
*/
public class BlockNumber extends CommonItem {

    public BlockNumber(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "номер блока данных (BlockNumber) - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 2;
    }

    private int getValue() {
        return super.getBytes().get(0);
    }
}