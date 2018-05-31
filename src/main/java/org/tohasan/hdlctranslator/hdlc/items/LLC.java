package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * LLC (Logical link control) – (3 байта), все APDU кадры начинаюстя LLC полем:
 * для нисходящего (Client → Server) – 0xE6E600,
 * для восходящего (Server → Client) – 0xE6E700.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class LLC extends HdlcItem {

    public LLC(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "логическое управление каналом (logical link control)";
    }

    @Override
    public int size() {
        return 3;
    }

}
