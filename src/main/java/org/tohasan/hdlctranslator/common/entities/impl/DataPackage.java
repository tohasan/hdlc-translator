package org.tohasan.hdlctranslator.common.entities.impl;

import org.tohasan.hdlctranslator.common.entities.Package;

import java.util.List;

public class DataPackage implements Package {
    private byte[] bytes;
    private int index = 0;

    public DataPackage(byte[] bytes) {
        this.bytes = bytes;
    }

    DataPackage(List<Byte> bytes) {
        this.bytes = new byte[bytes.size()];

        for (int i = 0; i < bytes.size(); i++) {
            this.bytes[i] = bytes.get(i);
        }
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
