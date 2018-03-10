package org.tohasan.hdlctranslator.entities;

import java.util.List;

/**
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public interface PackageItem {
    void extract(Package pack, Frame frame);

    List<Byte> getBytes();

    StringBuffer getDescription();

    int size();
}
