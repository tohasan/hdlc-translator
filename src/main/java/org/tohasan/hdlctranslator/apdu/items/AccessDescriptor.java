package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * AccessDescriptor – (1 байт), определяет начало описания параметра атрибута объекта.
 * значение 0x01 - возможно номер параметра.
 *
 * author: IgorKaSan
 * date: 18.07.2018.
 */
public class AccessDescriptor extends CommonItem {
    final static int ATTRIBUTES_EXIST = 0x01;

    public AccessDescriptor(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "флаг начала описания параметров - 0x" +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return !isAccessDescriptor() ? 0 : 1;
    }

    private boolean isAccessDescriptor() {
        Optional<FrameItem> itemAttributeDescriptorOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof AttributeDescriptor)
                .findFirst();

        return itemAttributeDescriptorOptional.isPresent() && (ATTRIBUTES_EXIST == itemAttributeDescriptorOptional.get().getValue());
    }
}