package org.tohasan.hdlctranslator.common.entities.impl;

import org.tohasan.hdlctranslator.common.entities.Package;

import java.util.Arrays;

public class DataPackage implements Package {
    private byte[] bytes;
    private int index = 0;

    public DataPackage(byte[] bytes) {
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

    @Override
    public Package getRest() {
        return new DataPackage(Arrays.copyOfRange(this.bytes, index, this.bytes.length));
    }
}
