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
            switch (typeOptional.get().getValue()) {
                case 3:
                    valueSize = 1;
                    break;
                case 6:
                    valueSize = 4;
                    break;
                case 16:
                    valueSize = 2;
                    break;
                case 17:
                    valueSize = 1;
                    break;
                case 18:
                    valueSize = 2;
                    break;
                case 15:
                    valueSize = 1;
                    break;
                case 22:
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
