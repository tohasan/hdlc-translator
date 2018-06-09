package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.GetResponseType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * LongBlockNumber – (4 байта), определяет номер блока данных для Get Response блоков данных.
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class LongBlockNumber extends CommonItem {

    public LongBlockNumber(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "номер блока данных (BlockNumber) - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return !isGetRequestNext() ? 0 : 4;
    }

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isGetRequestNext() {
        Optional<FrameItem> getRequestTypeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof GetRequestType)
                .findFirst();

        return getRequestTypeOptional.isPresent() && (GetResponseType.GET_REQUEST_NEXT.getValue() == getRequestTypeOptional.get().getValue());
    }
}
