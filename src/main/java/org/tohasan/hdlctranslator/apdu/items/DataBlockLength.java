package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.GetResponseType;
import org.tohasan.hdlctranslator.apdu.enums.ReadResponse;
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

    @Override
    public int size() {
        return !isBlockMode() ? 0 : 1;
    }

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isBlockMode() {
        Optional<FrameItem> getResponseTypeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof GetResponseMode)
                .findFirst();

        Optional<FrameItem> readResponseTypeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof ReadResponseFormat)
                .findFirst();

        return (getResponseTypeOptional.isPresent() && (GetResponseType.GET_REQUEST_NEXT.getValue() == getResponseTypeOptional.get().getValue())) || (readResponseTypeOptional.isPresent() && (ReadResponse.DATA_BLOCK_RESULT.getValue() == readResponseTypeOptional.get().getValue()));
    }
}