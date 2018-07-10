package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.GetResponseType;
import org.tohasan.hdlctranslator.apdu.enums.VasType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * BlockNumber – (2 байта), определяет номер блока данных:
 *
 * author: IgorKaSan
 * date: 31.05.2018.
*/
public class BlockNumber extends CommonItem {

    public BlockNumber(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "номер блока данных (BlockNumber) - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return !isBlockNumberAccess() ? 0 : 2;
    }

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isBlockNumberAccess() {
        Optional<FrameItem> blockNumberAccessOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof Vas)
                .findFirst();

        Optional<FrameItem> dataBlockResultOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof ReadResponseFormat)
                .findFirst();

        return (blockNumberAccessOptional.isPresent() && (VasType.BLOCK_NUMBER_ACCESS.getValue() == blockNumberAccessOptional.get().getValue())) || dataBlockResultOptional.isPresent();
    }

    @Override
    public int getValue() {
        return super.getBytes().get(0) & 255;   // & 255 исправляет отрицательное число, возвращаемое getBytes()
    }
}