package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

public class AcseTag extends CommonFrame {

    public AcseTag() {
        this.setItems(Arrays.asList(
            new AcseTagName(this),
            new AcseLength(this),
            new AcseDataType(this),
            new AcseDataLength(this),
            new AcseValue(this)
        ));
    }
}
