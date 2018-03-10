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
    private StringBuffer description = new StringBuffer();

    @Override
    public void extract(Package pack, Frame frame) {
        for (int i = 0; i < size(); i++) {
            bytes.add(0, pack.nextByte());
        }
    }

    @Override
    public List<Byte> getBytes() {
        return bytes;
    }

    @Override
    public StringBuffer getDescription() {

        for (int i = bytes.size() - 1; i >= 0; i--) {
//            for (int i = 0; i < bytes.size(); i++) {
            bytes.get(i).byteValue();
            description.append(Postprocessor.toHexChar(bytes.get(i).byteValue() >>> 4 & 15));
            description.append(Postprocessor.toHexChar(bytes.get(i).byteValue() & 15));
        }

        return description;
    }
}
