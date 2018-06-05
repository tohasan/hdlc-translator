package org.tohasan.hdlctranslator.common.entities.impl;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.Package;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class FrameField extends RawData {
    private Frame nestedFrame;

    public FrameField(Frame frame) {
        super(frame);
    }

    @Override
    public List<Byte> extract(Package pack) {
        List<Byte> bytes = super.extract(pack);
        List<Byte> bytesReverted = new ArrayList<>(bytes);
        Collections.reverse(bytesReverted);

        this.nestedFrame = initNestedFrame();

        if (!this.empty() && this.nestedFrame != null) {
            this.nestedFrame.extract(new DataPackage(bytesReverted));
        }

        return bytes;
    }

    protected abstract Frame initNestedFrame();

    @Override
    public String getDescription() {
        return !this.empty() && nestedFrame != null ? nestedFrame.getDescription() : "";
    }
}
