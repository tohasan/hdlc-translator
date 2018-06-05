package org.tohasan.hdlctranslator.common.entities.impl;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicListField<T extends FrameItem> extends RawData {
    private Class<T> clazz;
    private List<T> fieldItems = new ArrayList<>();

    public DynamicListField(Frame frame, Class<T> clazz) {
        super(frame);
        this.clazz = clazz;
    }

    @Override
    public List<Byte> extract(Package pack) {
        List<Byte> bytes = this.revertBytes(super.extract(pack));

        if (!this.empty()) {
            Package listFieldPackage = new DataPackage(bytes);
            while (listFieldPackage.hasNext()) {
                try {
                    T item = this.clazz.newInstance();
                    item.extract(listFieldPackage);
                    fieldItems.add(item);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return getBytes();
    }

    @Override
    public String getDescription() {
        if (this.empty() || this.fieldItems.isEmpty()) {
            return "";
        }

        // TODO: Дублирует CommonFrame
        return this.fieldItems.stream()
            .filter(item -> !item.empty())
            .map(FrameItem::getDescription)
            .collect(Collectors.joining("\n"));
    }
}
