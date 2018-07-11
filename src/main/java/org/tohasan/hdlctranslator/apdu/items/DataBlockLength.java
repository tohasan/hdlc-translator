package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.GetResponseType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * DataBlockLength – (1 байт), специфицирует длину блока данных в байтах.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class DataBlockLength extends CommonItem {

    public DataBlockLength(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "длина блока данных в байтах (DataBlockLength) - " +  Integer.toString(getValue());
    }

//    @Override
//    public int size() {
//        return 1;
//    }
    @Override
    public int size() {
        return !isBlockMode() ? 0 : 1;
    }

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isBlockMode() {
        Optional<FrameItem> getResponseTypeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof GetResponseMode)
                .findFirst();

//        Optional<FrameItem> getRequestTypeOptional = this.frame.getItems().stream()
//                .filter(item -> item instanceof GetRequestMode)
//                .findFirst();

//        return (getResponseTypeOptional.isPresent() && (GetResponseType.GET_REQUEST_NEXT.getValue() == getResponseTypeOptional.get().getValue())) || (getRequestTypeOptional.isPresent() && (GetResponseType.GET_REQUEST_NEXT.getValue() == getRequestTypeOptional.get().getValue()));
        return getResponseTypeOptional.isPresent() && (GetResponseType.GET_REQUEST_NEXT.getValue() == getResponseTypeOptional.get().getValue());
    }
}