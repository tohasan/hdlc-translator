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
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ReadResponseFormat extends CommonItem {

    public ReadResponseFormat(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        String typeDescription;

//        Optional<FrameItem> typeOptional = this.frame.getItems().stream()
//                .filter(item -> item instanceof ItemType)
//                .findFirst();

//        DataType type = DataType.getByValue((byte) typeOptional.get().getValue());
        ReadResponse type = ReadResponse.getByValue((byte) this.getValue());

        // TODO: Обработать type = null
        switch (type) {
            case DATA:
                typeDescription = "DATA";
                break;
            case DATA_ACCESS_ERROR:
                typeDescription = "DATA_ACCESS_ERROR";
                break;
            case DATA_BLOCK_RESULT:
                typeDescription = "DATA_BLOCK_RESULT";
                break;
            case BLOCK_NUMBER:
                typeDescription = "BLOCK_NUMBER";
                break;
            default:
                typeDescription = "Unknown data type";
                break;
        }
//        return "тип данных (ItemType) - " + typeDescription + " (DataType[" + Integer.toString(getValue()) + "])";
        return "тип переменной доступа (ReadResponseFormat) - " + typeDescription + " (ReadResponse[" +  Integer.toString(getValue()) + "])";
    }

    @Override
    public int size() {
        return 1;
    }
}