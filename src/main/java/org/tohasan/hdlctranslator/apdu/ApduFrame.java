package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.ApduContent;
import org.tohasan.hdlctranslator.apdu.items.ApduTypeField;
import org.tohasan.hdlctranslator.apdu.items.Llc;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

public class ApduFrame extends CommonFrame {

    public ApduFrame() {
        this.setItems(Arrays.asList(
            new Llc(this),
            new ApduTypeField(this),
            new ApduContent(this)
        ));
    }
}
