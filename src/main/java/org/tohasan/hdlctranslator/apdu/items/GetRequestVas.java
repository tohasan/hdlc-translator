package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * GetRequestVas – (1 байт), определяет переменную доступа:
 *  - GetRequestNormal[1] - INVOKE_ID_AND_PRIORITY
 *  - GetRequestNormal[2] - COSEM_ATTRIBUTE_DESCRIPTION
 *  - GetRequestNormal[3] - ACCESS_SELECTION
 *
 *  - GetRequestNext[1] - INVOKE_ID_AND_PRIORITY
 *  - GetRequestNext[2] - BLOCK_NUMBER
 *
 *  - GetRequestWithList[1] - INVOKE_ID_AND_PRIORITY
 *  - GetRequestWithList[2] - COSEM_ATTRIBUTE_DESCRIPTION_LIST
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class GetRequestVas extends CommonItem {
    final static int INVOKE_ID_AND_PRIORITY =0x01;
    final static int COSEM_ATTRIBUTE_DESCRIPTION =0x02;
    final static int ACCESS_SELECTION =0x03;
    // TODO: для всех типов GetRequest

    public GetRequestVas(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тип GetRequestNormal GRN[" +  Integer.toString(getValue()) + "]";
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