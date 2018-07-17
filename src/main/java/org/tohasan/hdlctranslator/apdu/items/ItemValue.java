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
    final static int UNKNOWN_DATA_TYPE = 0xFF;
    final static int BIT_IN_BYTE = 8;

    ItemValue(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        String valueDescription = "значение элемента данных (ItemValue)";

        if (isByteString()) {
            int size = this.size();
            int arrayMaxIndex = size - 1;
            StringBuilder obis = new StringBuilder();

            byte[] bytes = new byte[size];

            for (int i = 0; i < size; i++) {
                bytes[arrayMaxIndex - i] = super.getBytes().get(i);
                if (0 < obis.length()) {
                    obis.insert(0, Integer.toString(bytes[arrayMaxIndex - i] & 255) + ".");
                } else {
                    obis.insert(0, Integer.toString(bytes[arrayMaxIndex - i] & 255));
                }
            }
            // TODO: сделать, что бы только значения ОБИСов преобразовывались в строку 0.0.0.0.0.0
            valueDescription = valueDescription + " - идентификатор объекта (OBIS код) - " + obis.toString();
        }
        return valueDescription;
    }

    @Override
    public int size() {
        int valueSize = 0;

        if (!isByteString()) {
            Optional<FrameItem> typeOptional = this.frame.getItems().stream()
                    .filter(item -> item instanceof ItemType)
                    .findFirst();

            DataType type = DataType.getByValue((byte) typeOptional.get().getValue());

            if (type == null) {
                type = DataType.getByValue((byte) UNKNOWN_DATA_TYPE);
            }

            switch (type) {
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
                    System.out.println("WARNING: Unknown data type");
                    break;
            }
        } else {
            Optional<FrameItem> typeOptional = this.frame.getItems().stream()
                    .filter(item -> item instanceof ItemType)
                    .findFirst();

            Optional<FrameItem> itemLengthOptional = this.frame.getItems().stream()
                    .filter(item -> item instanceof ItemLength)
                    .findFirst();

            if (DataType.BIT_STRING.getValue() == typeOptional.get().getValue()) {
                valueSize = itemLengthOptional.get().getValue() / BIT_IN_BYTE;
            } else {
                valueSize = itemLengthOptional.get().getValue();
            }
        }
        return valueSize;
    }   // длина поля должна рассчитываться в соответствии с типом данных

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isByteString() {
        Optional<FrameItem> typeOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof ItemType)
                .findFirst();

        return typeOptional.isPresent() && ((DataType.OCTET_STRING.getValue() == typeOptional.get().getValue()) || (DataType.VISIBLE_STRING.getValue() == typeOptional.get().getValue()) || (DataType.BIT_STRING.getValue() == typeOptional.get().getValue()));
    }
}
