package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.GetResponseType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * LongBlockNumber – (4 байта), определяет номер блока данных для Get Response блоков данных.
 * <p>
 * author: IgorKaSan
 * date: 04.05.2018.
 */
public class LongBlockNumber extends CommonItem {

    public LongBlockNumber(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "номер блока данных (BlockNumber) - " + Integer.toString(getValue());
    }

    @Override
    public int size() {
        return !isBlockMode() ? 0 : 4;
    }

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isBlockMode() {
        Optional<FrameItem> getResponseTypeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof GetResponseMode)
                .findFirst();

        Optional<FrameItem> getRequestTypeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof GetRequestMode)
                .findFirst();

        return (getResponseTypeOptional.isPresent() && (GetResponseType.GET_REQUEST_NEXT.getValue() == getResponseTypeOptional.get().getValue())) || (getRequestTypeOptional.isPresent() && (GetResponseType.GET_REQUEST_NEXT.getValue() == getRequestTypeOptional.get().getValue()));
    }

    @Override
    public int getValue() {
        return super.getBytes().get(1) & 255 << 8 | super.getBytes().get(0) & 255;  // & 255 исправляет отрицательное число, возвращаемое getBytes()
    }
}
