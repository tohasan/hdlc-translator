package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * FCS (Frame Check Sequence - <код целостности HDLC кадра>) – 2 байта.
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class FrameCheckSequence extends CommonItem {

    public FrameCheckSequence(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "код целостности кадра (frame check sequence)";
    }

    @Override
    public int size() {
        return 2;
    }
}
