package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.VasType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * ObjectShortName – (2 байта), определяет короткое имя (Short Name) запрашиваемого объекта:
 *  базовое короткое имя объекта + смещение запрашиваемого атрибута
 *  (например, 0xFD08 = 0xFD00 (объект: Logical Device Name) + 0x08 (атрибут: value).
 *
 * author: IgorKaSan
 * date: 31.05.2018.
*/
public class ShortName extends CommonItem {

    public ShortName(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "короткое имя запрашиваемого объекта (ShortName)";
    }

//    @Override
//    public int size() {
//        return 2;
//    }

    @Override
    public int size() {
        return !isBlockNumberAccess() ? 2 : 0;
    }

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isBlockNumberAccess() {
        Optional<FrameItem> blockNumberAccessOptional = this.frame.getItems().stream()
                .filter(item -> item instanceof Vas)
                .findFirst();

        return blockNumberAccessOptional.isPresent() && (VasType.BLOCK_NUMBER_ACCESS.getValue() == blockNumberAccessOptional.get().getValue());
    }
}