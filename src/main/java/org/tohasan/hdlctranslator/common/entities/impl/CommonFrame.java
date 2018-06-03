package org.tohasan.hdlctranslator.common.entities.impl;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.Package;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.utils.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CommonFrame implements Frame {
    private List<FrameItem> items;

    protected CommonFrame() {
        this.items = new ArrayList<>();
    }

    @Override
    public List<FrameItem> getItems() {
        return items;
    }

    public void setItems(List<FrameItem> items) {
        this.items = items;
    }

    @Override
    public List<Byte> extract(Package pack) {
        for (FrameItem item : this.getItems()) {
            item.extract(pack);
        }

        return this.getBytes();
    }

    public String parse(String hexStr) {
        StringHelper stringHelper = new StringHelper(hexStr);
        Package pack = new DataPackage(stringHelper.getBytes());

        this.extract(pack);

        return this.getDescription();
    }

    @Override
    public String getDescription() {
        return this.items.stream()
            .filter(item -> !item.empty())
            .map(FrameItem::getDescription)
            .collect(Collectors.joining("\n"));
    }

    @Override
    public List<Byte> getBytes() {
        return this.items.stream()
            .flatMap(item -> item.getBytes().stream())
            .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return this.items.stream()
            .map(FrameItem::size)
            .reduce(0, (left, right) -> left + right);
    }

    @Override
    public boolean empty() {
        return this.items.isEmpty();
    }
}
