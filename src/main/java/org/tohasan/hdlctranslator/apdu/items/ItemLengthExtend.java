package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.DataType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

public class ItemLengthExtend extends CommonItem {

    public ItemLengthExtend(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "длина элемента данных в байтах (ItemLength) - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        int fieldSize = 0;
        if (isItemLength()){
            if (isItemLengthExtend()) {fieldSize = 2;}
        }

        return fieldSize;
    }

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isItemLength() {
        Optional<FrameItem> typeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof ItemType)
                .findFirst();

        Optional<FrameItem> itemLengthOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof ItemLength)
                .findFirst();

//        return typeOptional.isPresent() && DataType.VISIBLE_STRING.getValue() == typeOptional.get().getValue();
//        return typeOptional.isPresent() && (128 > typeOptional.get().getValue());
//        return typeOptional.isPresent() && (128 > itemLengthOptional.get().getValue()) && ((DataType.OCTET_STRING.getValue() == typeOptional.get().getValue()) || (DataType.VISIBLE_STRING.getValue() == typeOptional.get().getValue()) || (DataType.ARRAY.getValue() == typeOptional.get().getValue()) || (DataType.STRUCTURE.getValue() == typeOptional.get().getValue()));
        return typeOptional.isPresent() && ((DataType.OCTET_STRING.getValue() == typeOptional.get().getValue()) || (DataType.VISIBLE_STRING.getValue() == typeOptional.get().getValue()) || (DataType.ARRAY.getValue() == typeOptional.get().getValue()) || (DataType.STRUCTURE.getValue() == typeOptional.get().getValue()));
//    public int size() {
//        return 1;
    }

        private boolean isItemLengthExtend() {
            Optional<FrameItem> itemLengthOptional = this.frame.getItems().stream()
                    .filter(item -> item instanceof ItemLength)
                    .findFirst();

        return itemLengthOptional.isPresent() && (128 <= itemLengthOptional.get().getValue());
    }
}