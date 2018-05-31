package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * BlockNumber – (2 байта), определяет номер блока данных:
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class BlockNumber extends HdlcItem {

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
        int code;
        code = super.getBytes().get(0);
        return code;
    }
}