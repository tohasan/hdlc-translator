package org.tohasan.hdlctranslator.hdlc;

import org.tohasan.hdlctranslator.common.Postprocessor;
import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.entities.Package;
import org.tohasan.hdlctranslator.entities.PackageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public abstract class HdlcItem implements PackageItem {
    private List<Byte> bytes = new ArrayList<>();
    protected Frame frame;

    public HdlcItem(Frame frame) {
        this.frame = frame;
    }

    protected abstract String getDescriptionTip();

    @Override
    public void extract(Package pack) {
        for (int i = 0; i < size(); i++) {
            bytes.add(0, pack.nextByte());
        }
    }

    @Override
    public List<Byte> getBytes() {
        return bytes;
    }


    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder();

        for (int i = bytes.size() - 1; i >= 0; i--) {
            description.append(Postprocessor.toHexChar(bytes.get(i) >>> 4 & 15));
            description.append(Postprocessor.toHexChar(bytes.get(i) & 15));
        }

        return !this.empty() ? String.format("%s - %s", description.toString(), this.getDescriptionTip()) : "";
    }

    @Override
    public boolean empty() {
        return bytes.size() == 0;
    }
}
