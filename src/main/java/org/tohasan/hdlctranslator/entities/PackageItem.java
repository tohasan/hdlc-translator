package org.tohasan.hdlctranslator.entities;

import java.util.List;

/**
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public interface PackageItem {
    void extract(Package pack);

    List<Byte> getBytes();

    String getDescription();

    int size();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean empty();
}
