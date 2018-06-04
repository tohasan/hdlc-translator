package org.tohasan.hdlctranslator.common.entities.impl;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.Package;

import java.util.List;

/**
 * Неразобранные данные (технологическое поле).
 * <p>
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class RawData extends CommonItem {

    public RawData(Frame frame) {
        super(frame);
    }

    @Override
    public List<Byte> extract(Package pack) {
        List<FrameItem> items = this.frame.getItems();

        int sumFieldSizeExcludeCurrent = 0;
        for (FrameItem item : items) {
            if (item != this) {
                sumFieldSizeExcludeCurrent += item.size();
            }
        }

        int size = pack.size() - sumFieldSizeExcludeCurrent;

        getBytes().clear();

        for (int i = 0; i < size; i++) {
            getBytes().add(0, pack.nextByte());
        }

        return getBytes();
    }

    @Override
    protected String getDescriptionTip() {
        return "неразобранные данные (raw data)";
    }

    @Override
    public int size() {
        return getBytes().size();
    }
}