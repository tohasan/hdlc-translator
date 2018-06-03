package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * Last Block Flag – (1 байт), специфицирует наличие признака последнего блока (last-block flag)
 * – false, есть следующий блок,
 * - true, последний блок.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class LastBlock extends HdlcItem {

    public LastBlock(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "признак последнего блока (LastBlock flag)";
    }

    @Override
    public int size() {
        return 1;
    }
}
