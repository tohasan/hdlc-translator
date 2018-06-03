package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ACSE Data Type – (1 байт), специфицирует тип данных закодированного значения ACSE (Association Control Service Element - Элемент Службы Управления Ассоциацией).
 *
 * author: IgorKaSan
 * date: 01.06.2018.
 */

public class AcseDataType extends HdlcItem {

    public AcseDataType(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тип данных закодированного значения ACSE (ACSE Data Type) - DataType[" +  Integer.toString(getValue()) + "]";
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