package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * FCS (Frame Check Sequence - <код целостности HDLC кадра>) – 2 байта.
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class FrameCheckSequence extends HdlcItem {

    @Override
    public StringBuffer getDescription() {
        StringBuffer description = super.getDescription();

        description.append(" - код целостности кадра (frame check sequence)");
        return description;
    }

    @Override
    public int size() {
        return 2;
    }
}
