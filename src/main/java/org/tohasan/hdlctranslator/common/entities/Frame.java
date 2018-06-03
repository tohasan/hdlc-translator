package org.tohasan.hdlctranslator.common.entities;

import java.util.List;

/**
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public interface Frame {
    List<PackageItem> getItems();

    void setItems(List<PackageItem> items);

    String getDescription();
}