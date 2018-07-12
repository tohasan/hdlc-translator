package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.DataType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * ItemValue – (количество байт определяется типом данных), значение элемента данных в последовательности.
 * <p>
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ItemValue extends CommonItem {
    // TODO: Подумать, может быть как-то использовать уже имеющееся перечисление DataType?
    // Item types:
//    private final static int NULL_DATA =0x00;
//    private final static int ARRAY =0x01;
//    private final static int STRUCTURE =0x02;
//    private final static int BOOLEAN =0x03;
//    private final static int DOUBLE_LONG_UNSIGNED =0x06;
//    private final static int INTEGER =0x0F;
//    private final static int LONG =0x10;
//    private final static int UNSIGNED =0x11;
//    private final static int LONG_UNSIGNED =0x12;
//    private final static int ENUM =0x16;

    ItemValue(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "значение элемента данных в последовательности (ItemValue)";
    }

    @Override
    public int size() {
        int valueSize = 0;

        if (!isByteString()) {
            Optional<FrameItem> typeOptional = this.frame.getItems().stream()
                    .filter(item -> item instanceof ItemType)
                    .findFirst();

            DataType type = DataType.getByValue((byte) typeOptional.get().getValue());
            // TODO: Обработать type = null
            switch (type) {
//            switch (typeOptional.get().getValue()) {
                case NULL_DATA:
                    valueSize = 0;
                    break;
                case ARRAY:
                    valueSize = 0;
                    break;
                case STRUCTURE:
                    valueSize = 0;
                    break;
                case BOOLEAN:
                    valueSize = 1;
                    break;
                case DOUBLE_LONG_UNSIGNED:
                    valueSize = 4;
                    break;
                case INTEGER:
                    valueSize = 1;
                    break;
                case LONG:
                    valueSize = 2;
                    break;
                case UNSIGNED:
                    valueSize = 1;
                    break;
                case LONG_UNSIGNED:
                    valueSize = 2;
                    break;
                case ENUM:
                    valueSize = 1;
                    break;
                default:
                    System.out.println("WARN Unknown data type");
                    break;
            }
        } else {
            Optional<FrameItem> itemLengthOptional = this.frame.getItems().stream()
                    .filter(item -> item instanceof ItemLength)
                    .findFirst();
            valueSize = itemLengthOptional.get().getValue();
        }
        return valueSize;
    }   // длина поля должна рассчитываться в соответствии с типом данных

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isByteString() {
        Optional<FrameItem> typeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof ItemType)
                .findFirst();

        return typeOptional.isPresent() && ((DataType.OCTET_STRING.getValue() == typeOptional.get().getValue()) || (DataType.VISIBLE_STRING.getValue() == typeOptional.get().getValue()));
    }
}
