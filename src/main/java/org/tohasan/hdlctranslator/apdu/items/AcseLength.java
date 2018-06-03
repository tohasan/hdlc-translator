package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ACSE Length – (1 байт), специфицирует длину ACSE (Association Control Service Element - Элемент Службы Управления Ассоциацией).
 *
 * author: IgorKaSan
 * date: 01.06.2018.
 */

public class AcseLength extends HdlcItem {

    public AcseLength(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "длина элемента службы управления ассоциацией в байтах (ACSE Length) - " +  Integer.toString(getValue());
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