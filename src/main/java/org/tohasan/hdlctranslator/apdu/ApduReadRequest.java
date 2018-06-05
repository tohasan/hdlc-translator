package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.*;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.RawData;

import java.util.Arrays;

public class ApduReadRequest extends CommonFrame {

    public ApduReadRequest() {
        this.setItems(Arrays.asList(
            new Quantity(this),
            new ArrayOfReadRequestElements(this),
//            new Vas(this),
//            new ShortName(this),
            new RawData(this)
        ));
    }
}
