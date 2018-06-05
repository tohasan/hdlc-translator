package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.RawData;

import java.util.Arrays;

public class ReadRequestElement extends CommonFrame {

    public ReadRequestElement() {
        this.setItems(Arrays.asList(
                new Vas(this),
                new ShortName(this)
        ));
    }
}