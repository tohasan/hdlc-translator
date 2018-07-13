package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.DataType;
import org.tohasan.hdlctranslator.apdu.enums.ReadResponse;
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
        String typeDescription = "НЕИЗВЕСТНЫЙ_ТИП";

        DataType type = DataType.getByValue((byte) this.getValue());

        if (type != null) {
            typeDescription = type.toString();
        }

        return "тип данных (ItemType) - " + typeDescription + " (DataType[" + Integer.toString(getValue()) + "])";
    }

    @Override
    public int size() {
        return 1;
    }
}