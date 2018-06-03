package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * AARQ Length – (1 байт), специфицирует длину содержимого AARQ (Application Association Request - запрос ассоциации приложения) в байтах.
 *
 * author: IgorKaSan
 * date: 01.06.2018.
 */

public class AarqLength extends HdlcItem {

    public AarqLength(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "длина запроса ассоциации приложения в байтах (AARQ Length) - " +  Integer.toString(getValue());
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
