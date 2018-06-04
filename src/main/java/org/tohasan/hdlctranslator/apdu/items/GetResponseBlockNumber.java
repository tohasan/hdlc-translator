package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * GetResponseBlockNumber – (4 байта), определяет номер блока данных для Get Response блоков данных.
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class GetResponseBlockNumber extends CommonItem {

    public GetResponseBlockNumber(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "номер блока данных (BlockNumber) - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 4;
    }

    private int getValue() {
        return super.getBytes().get(0);
    }
}
