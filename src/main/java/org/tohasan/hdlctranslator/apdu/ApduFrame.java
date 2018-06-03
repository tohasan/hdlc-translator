package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.PackageItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApduFrame implements Frame {
    private List<PackageItem> items;

    ApduFrame() {
        this.items = new ArrayList<>();
    }

    @Override
    public List<PackageItem> getItems() {
        return items;
    }

    public void setItems(List<PackageItem> items) {
        this.items = items;
    }

    @Override
    public String getDescription() {
        return this.items.stream()
                .filter(item -> !item.empty())
                .map(PackageItem::getDescription)
                .collect(Collectors.joining("\n"));
    }
}
