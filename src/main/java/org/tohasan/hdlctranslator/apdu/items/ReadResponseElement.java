package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

public class ReadResponseElement extends CommonFrame {

    public ReadResponseElement() {
        this.setItems(Arrays.asList(
//                new ResultDiagnosticFlag(this),
                new ItemType(this),
                new ItemLength(this),
                new ItemLengthExtend(this),
                new ItemValue(this)
        ));
    }
}