package org.tohasan.hdlctranslator.common.entities;

import java.util.List;

/**
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public interface Frame extends FrameItem {
    List<FrameItem> getItems();

    void setItems(List<FrameItem> items);

    List<Byte> extract(Package pack);

    String parse(String hexStr);

    String getDescription();
}
