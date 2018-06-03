package org.tohasan.hdlctranslator.common.entities.impl;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.Package;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.utils.StringHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonItem implements FrameItem {
    private List<Byte> bytes = new ArrayList<>();
    protected Frame frame;

    public CommonItem(Frame frame) {
        this.frame = frame;
    }

    protected abstract String getDescriptionTip();

    @Override
    public List<Byte> extract(Package pack) {
        bytes = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            bytes.add(0, pack.nextByte());
        }
        return bytes;
    }

    @Override
    public List<Byte> getBytes() {
        return bytes;
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder();

        for (int i = bytes.size() - 1; i >= 0; i--) {
            description.append(StringHelper.toHexChar(bytes.get(i) >>> 4 & 15));
            description.append(StringHelper.toHexChar(bytes.get(i) & 15));
        }

        return !this.empty() ? String.format("%s - %s", description.toString(), this.getDescriptionTip()) : "";
    }

    @Override
    public boolean empty() {
        return bytes.size() == 0;
    }
}
