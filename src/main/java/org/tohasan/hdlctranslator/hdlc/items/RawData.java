package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.entities.Package;
import org.tohasan.hdlctranslator.entities.PackageItem;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

import java.util.List;
/**
 *
 *
 *
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class RawData  extends HdlcItem {

    public RawData(Frame frame) {
        super(frame);
    }

    @Override
    public void extract(Package pack) {
        List<PackageItem> items = this.frame.getItems();

        int sumFieldSizeExcludeCurrent = 0;
        for (PackageItem item : items) {
            if (item != this) {
                sumFieldSizeExcludeCurrent += item.size();
            }
        }

        int size = pack.size() - sumFieldSizeExcludeCurrent;

        for (int i = 0; i < size; i++) {
            getBytes().add(0, pack.nextByte());
        }
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