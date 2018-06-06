package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.Quantity;
import org.tohasan.hdlctranslator.apdu.items.ReadRequestElement;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.DynamicListField;

import java.util.Arrays;

public class ApduReadRequest extends CommonFrame {

    public ApduReadRequest() {
        this.setItems(Arrays.asList(
            new Quantity(this),
            new DynamicListField<>(this, ReadRequestElement.class)
        ));
    }
}
