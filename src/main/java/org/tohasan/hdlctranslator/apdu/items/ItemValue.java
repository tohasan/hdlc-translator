package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ItemValue – (количество байт определяется типом данных), значение элемента данных в последовательности.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class ItemValue extends HdlcItem {

    public ItemValue(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "значение элемента данных в последовательности (ItemValue)";
    }

    @Override
    public int size() {
        return 11;
    }   // длина поля должна рассчитываться в соответствии с типом данных
}
