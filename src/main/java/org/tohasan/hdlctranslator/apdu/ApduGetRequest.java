package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.*;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.DynamicListField;
import org.tohasan.hdlctranslator.common.entities.impl.RawData;

import java.util.Arrays;

/**
 * ApduGetRequest – APDU фрейм типа Get Request, применяется для запросов по логическому имени
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class ApduGetRequest extends CommonFrame {

    public ApduGetRequest() {
        this.setItems(Arrays.asList(
                new GetRequestType(this),
                new GetRequestVas(this),
                new LongBlockNumber(this),
                new DynamicListField<>(this, GetRequestElement.class)
        ));
    }
}