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
public class AcseTagName extends CommonItem {

    AcseTagName(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тег элемента управления ассоциацией (ACSE Tag) - [" +  Integer.toString(getValue() & 255) + "]";
    }

    @Override
    public int size() {
        return 1;
    }
}
