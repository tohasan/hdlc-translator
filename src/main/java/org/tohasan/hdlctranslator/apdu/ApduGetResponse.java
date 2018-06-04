package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.*;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.RawData;

import java.util.Arrays;

/**
 * ApduGetResponse – APDU фрейм типа Get Response, применяется для ответов на запросы по логическому имени
 *
 * author: IgorKaSan
 * date: 04.05.2018.
 */

public class ApduGetResponse extends CommonFrame {

    public ApduGetResponse() {
        this.setItems(Arrays.asList(
                new GetRequestType(this),
                new GetRequestVas(this),
                new LastBlock(this),
                new GetResponseBlockNumber(this),
                new ResultDiagnosticFlag(this),
                new DataBlockLength(this),
                new RawData(this)
        ));
    }
}