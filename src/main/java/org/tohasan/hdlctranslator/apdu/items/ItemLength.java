package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.AcseTagType;
import org.tohasan.hdlctranslator.apdu.enums.DataType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * ItemLength – (1 байт), специфицирует длину элемента данных в последовательности.
 *  используется не для всех типов данных (используется для: байтовая строка, массив, структура и т.д.)
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ItemLength extends CommonItem {

    public ItemLength(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "длина элемента данных в байтах (ItemLength) - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return !isByteString() ? 0 : 1;
    }

    @Override
    public int getValue() {
        return super.getBytes().get(0) & 255;   // & 255 исправляет отрицательное число, возвращаемое getBytes()
    }

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isByteString() {
        Optional<FrameItem> typeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof ItemType)
                .findFirst();

        return typeOptional.isPresent() && ((DataType.OCTET_STRING.getValue() == typeOptional.get().getValue()) || (DataType.VISIBLE_STRING.getValue() == typeOptional.get().getValue()) || (DataType.BIT_STRING.getValue() == typeOptional.get().getValue()) || (DataType.ARRAY.getValue() == typeOptional.get().getValue()) || (DataType.STRUCTURE.getValue() == typeOptional.get().getValue()));
    }
}