package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ACSE Data Length – (1 байт), специфицирует длину закодированного значения ACSE (Association Control Service Element - Элемент Службы Управления Ассоциацией) в байтах.
 *
 * author: IgorKaSan
 * date: 01.06.2018.
 */

public class AcseDataLength extends HdlcItem {

    public AcseDataLength(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - " +  Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 1;
    }

    private int getValue() {
        int code;
        code = super.getBytes().get(0);
        return code;
    }
}
