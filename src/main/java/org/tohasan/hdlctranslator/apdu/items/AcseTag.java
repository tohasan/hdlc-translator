package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * ACSE Tag – (1 байт), специфицирует ACSE (Association Control Service Element - Элемент Службы Управления Ассоциацией).
 *  AARQ/AARE (Application Association Request/Response) перечисление
 *
 * author: IgorKaSan
 * date: 01.06.2018.
 */
public class AcseTag extends CommonItem {

    public AcseTag(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тег элемента управления ассоциацией (ACSE Tag) - [" +  Integer.toString(getValue()) + "]";
    }

    @Override
    public int size() {
        return 1;
    }

    private int getValue() {
        return super.getBytes().get(0) & 255; // & 255 исправляет отрицательное число, возвращаемое getBytes()
    }
}
