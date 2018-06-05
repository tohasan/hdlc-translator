package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * GetRequestVas – (1 байт), определяет переменную доступа:
 *  GetRequestNormal[1] - INVOKE_ID_AND_PRIORITY
 *  GetRequestNormal[2] - COSEM_ATTRIBUTE_DESCRIPTION
 *  GetRequestNormal[3] - ACCESS_SELECTION
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class GetRequestVas extends CommonItem {
    final static int INVOKE_ID_AND_PRIORITY =0x01;
    final static int COSEM_ATTRIBUTE_DESCRIPTION =0x02;
    final static int ACCESS_SELECTION =0x03;

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
}