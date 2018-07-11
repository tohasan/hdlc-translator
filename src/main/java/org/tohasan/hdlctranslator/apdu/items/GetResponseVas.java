package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * GetRequestVas – (1 байт), определяет переменную доступа:
 *  - GetResponseNormal[1] - INVOKE_ID_AND_PRIORITY
 *  - GetResponseNormal[2] - GET_DATA_RESULT
 *
 *  - GetResponseWithDatablock[1] - INVOKE_ID_AND_PRIORITY
 *  - GetResponseWithDatablock[2] - DATA_BLOCK_G
 *
 *  - GetResponseWithList[1] - INVOKE_ID_AND_PRIORITY
 *  - GetResponseWithList[2] - GET_DATA_RESULT_LIST
 *
 * author: IgorKaSan
 * date: 11.07.2018.
 */
public class GetResponseVas extends CommonItem {
    final static int INVOKE_ID_AND_PRIORITY =0x01;
    final static int GET_DATA_RESULT =0x02;
    // TODO: для всех типов GetResponse

    public GetResponseVas(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тип GetResponseNormal GREN[" +  Integer.toString(getValue()) + "]";
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public int getValue() {
        return super.getBytes().get(0) & 255;   // & 255 исправляет отрицательное число, возвращаемое getBytes()
    }
}
