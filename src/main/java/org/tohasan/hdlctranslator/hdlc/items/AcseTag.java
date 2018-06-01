package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ACSE Tag – (1 байт), специфицирует ACSE (Association Control Service Element - Элемент Службы Управления Ассоциацией).
 *  AARQ/AARE (Application Association Request/Response) перечисление
 *
 * author: IgorKaSan
 * date: 01.06.2018.
 */

public class AcseTag extends HdlcItem {

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
        int code;
        code = super.getBytes().get(0) & 255; // & 255 исправляет отрицательное число, возвращаемое getBytes()
        return code;
    }
}
