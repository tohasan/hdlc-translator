package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.entities.Package;

public class ApduPackage implements Package {
    private byte[] bytes;
    private int index = 0;

    ApduPackage(byte[] bytes) {
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

