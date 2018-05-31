package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ObjectShortName – (2 байта), определяет короткое имя (Short Name) запрашиваемого объекта:
 *  базовое короткое имя объекта + смещение запрашиваемого атрибута
 *  (например, 0xFD08 = 0xFD00 (объект: Logical Device Name) + 0x08 (атрибут: value).
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class ShortName extends HdlcItem {

    public ShortName(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "короткое имя запрашиваемого объекта (ShortName)";
    }

    @Override
    public int size() {
        return 2;
    }
}