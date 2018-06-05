package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

class ReadRequestElement extends CommonFrame {

    ReadRequestElement() {
        this.setItems(Arrays.asList(
                new Vas(this),
                new ShortName(this)
        ));
    }
}