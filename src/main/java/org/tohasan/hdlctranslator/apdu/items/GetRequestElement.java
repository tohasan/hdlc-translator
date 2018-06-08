package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.RawData;

import java.util.Arrays;

public class GetRequestElement extends CommonFrame {

    public GetRequestElement() {
        this.setItems(Arrays.asList(
                new ParentClassId(this),
                new ObjectId(this),
                new AttributeId(this),
                new AttributeDescriptor(this)
        ));
    }
}