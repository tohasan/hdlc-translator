package org.tohasan.hdlctranslator.hdlc;

import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;
import org.tohasan.hdlctranslator.common.entities.Frame;

/**
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public abstract class HdlcItem extends CommonItem {

    public HdlcItem(Frame frame) {
        super(frame);
    }
}
