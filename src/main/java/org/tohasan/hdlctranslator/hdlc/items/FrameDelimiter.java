package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.entities.Package;
import org.tohasan.hdlctranslator.entities.PackageItem;
import org.tohasan.hdlctranslator.entities.State;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * FD (Frame Delimiter - <разделитель кадров>) – 1 байт,
 * все HDLC кадры должны начинаться и заканчиваться полем флага "01111110" (0x7E).
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class FrameDelimiter extends HdlcItem {

    @Override
    public StringBuffer getDescription() {
        StringBuffer description = super.getDescription();

        description.append(" - разделитель кадров (frame delimiter)");
        return description;
    }

    @Override
    public int size() {
        return 1;
    }
}
