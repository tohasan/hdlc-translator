package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.PackageItem;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

import java.util.List;

/**
 * ACSE Value – (1 байт), специфицирует закодированное значение ACSE (Association Control Service Element - Элемент Службы Управления Ассоциацией).
 *
 * author: IgorKaSan
 * date: 01.06.2018.
 */

public class AcseValue extends HdlcItem {

    public AcseValue(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "значения элемента службы управления ассоциацией (ACSE Value)";
    }

    @Override
    public int size() {
        List<PackageItem> items = this.frame.getItems();
        int previewIndex = items.indexOf(this) - 1;
        return (int) items.get(previewIndex).getBytes().get(0);
    }
}
