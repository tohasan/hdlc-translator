package org.tohasan.hdlctranslator.hdlc;

import org.tohasan.hdlctranslator.entities.Package;

/**
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class HdlcPackage implements Package {
    private byte[] bytes;
    private int index = 0;

    public HdlcPackage(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public byte nextByte() {
        byte currentByte = this.bytes[index];
        index++;
        return currentByte;
    }

    @Override
    public int size() {
        return this.bytes.length;
    }
}
