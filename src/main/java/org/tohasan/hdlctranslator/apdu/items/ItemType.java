package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.DataType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * ItemType – (1 байт), специфицирует тип данных (Enumeration - перечисление) элемента данных в последовательности.
 * <p>
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ItemType extends CommonItem {

    public ItemType(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        String typeDescription;

        Optional<FrameItem> typeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof ItemType)
                .findFirst();

        DataType type = DataType.getByValue((byte) typeOptional.get().getValue());
        // TODO: Обработать type = null
        switch (type) {
            case NULL_DATA:
                typeDescription = "NULL_DATA";
                break;
            case ARRAY:
                typeDescription = "ARRAY";
                break;
            case STRUCTURE:
                typeDescription = "STRUCTURE";
                break;
            case BOOLEAN:
                typeDescription = "BOOLEAN";
                break;
            case DOUBLE_LONG_UNSIGNED:
                typeDescription = "DOUBLE_LONG_UNSIGNED";
                break;
            case OCTET_STRING:
                typeDescription = "OCTET_STRING";
                break;
            case VISIBLE_STRING:
                typeDescription = "VISIBLE_STRING";
                break;
            case INTEGER:
                typeDescription = "INTEGER";
                break;
            case LONG:
                typeDescription = "LONG";
                break;
            case UNSIGNED:
                typeDescription = "UNSIGNED";
                break;
            case LONG_UNSIGNED:
                typeDescription = "LONG_UNSIGNED";
                break;
            case ENUM:
                typeDescription = "ENUM";
                break;
            default:
                typeDescription = "Unknown data type";
                break;
        }
        return "тип данных (ItemType) - " + typeDescription + " (DataType[" + Integer.toString(getValue()) + "])";
    }

    @Override
    public int size() {
        return 1;
    }
}