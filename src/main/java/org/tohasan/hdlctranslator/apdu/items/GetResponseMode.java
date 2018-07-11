package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * GetResponseMode – (1 байт), определяет тип Get Response запоса:
 *  - GetResponse[1] - GET_RESPONSE_NORMAL
 *  - GetResponse[2] - GET_RESPONSE_WITH_DATABLOCK
 *  - GetResponse[3] - GET_RESPONSE_WITH_LIST
 *
 * author: IgorKaSan
 * date: 11.07.2018.
 */
public class GetResponseMode extends CommonItem {
    final static int GET_RESPONSE_NORMAL =0x01;
    final static int GET_RESPONSE_WITH_DATABLOCK =0x02;
    final static int GET_RESPONSE_WITH_LIST =0x03;

    public GetResponseMode(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тип GetResponse GRE[" +  Integer.toString(getValue()) + "]";
    }

    @Override
    public int size() {
        return 1;
    }
}
