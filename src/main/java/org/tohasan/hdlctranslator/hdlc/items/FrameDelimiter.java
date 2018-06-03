package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * FD (Frame Delimiter - <разделитель кадров>) – 1 байт,
 * все HDLC кадры должны начинаться и заканчиваться полем флага "01111110" (0x7E).
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class FrameDelimiter extends HdlcItem {

    public FrameDelimiter(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "разделитель кадров (frame delimiter)";
    }

    @Override
    public int size() {
        return 1;
    }
}
