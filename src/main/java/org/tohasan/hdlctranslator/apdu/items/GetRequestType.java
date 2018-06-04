package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * GetRequestType – (1 байт), определяет тип Get Request запоса:
 *  GetRequest[1] - GET_REQUEST_NORMAL
 *  GetRequest[2] - GET_REQUEST_NEXT
 *  GetRequest[3] - GET_REQUEST_WITH_LIST
 *
 * author: IgorKaSan
 * date: 04.05.2018.
 */

public class GetRequestType extends CommonItem {
    final static int GET_REQUEST_NORMAL =0x01;
    final static int GET_REQUEST_NEXT =0x02;
    final static int GET_REQUEST_WITH_LIST =0x03;

    public GetRequestType(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тип GetRequest GR[" +  Integer.toString(getValue()) + "]";
    }

    @Override
    public int size() {
        return 1;
    }

    private int getValue() {
        return super.getBytes().get(0);
    }
}

