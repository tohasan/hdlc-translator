package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.List;

/**
 * ItemValue – (количество байт определяется типом данных), значение элемента данных в последовательности.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ItemValue extends CommonItem {

    public ItemValue(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "значение элемента данных в последовательности (ItemValue)";
    }

    @Override
    public int size() {
        List<FrameItem> items = this.frame.getItems();
        int previewIndex = items.indexOf(this) - 1;
        return items.get(previewIndex).getValue();
//        return 11;
    }   // длина поля должна рассчитываться в соответствии с типом данных
}
