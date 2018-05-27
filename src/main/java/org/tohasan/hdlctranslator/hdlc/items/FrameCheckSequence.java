package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * FCS (Frame Check Sequence - <код целостности HDLC кадра>) – 2 байта.
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class FrameCheckSequence extends HdlcItem {

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
