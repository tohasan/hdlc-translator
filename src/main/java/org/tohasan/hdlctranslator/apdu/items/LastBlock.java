package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.GetResponseType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * Last Block Flag – (1 байт), специфицирует наличие признака последнего блока (last-block flag)
 * – false, есть следующий блок,
 * - true, последний блок.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class LastBlock extends CommonItem {

    public LastBlock(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "признак последнего блока (LastBlock flag)";
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
