package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.DataType;
import org.tohasan.hdlctranslator.apdu.enums.ReadResponse;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * ReadResponseFormat – (1 байт), специфицирует тип/формат ответа:
 * - ReadResponse [2] : Data-Block-Result - ответ на запрос осуществляется поблочно
 * <p>
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ReadResponseFormat extends CommonItem {

    public ReadResponseFormat(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        String typeDescription = "НЕИЗВЕСТНЫЙ_ТИП";

        ReadResponse type = ReadResponse.getByValue((byte) this.getValue());

        if (type != null) {
            typeDescription = type.toString();
        }

        return "тип переменной доступа (ReadResponseFormat) - " + typeDescription + " (ReadResponse[" + Integer.toString(getValue()) + "])";
    }

    @Override
    public int size() {
        return 1;
    }
}