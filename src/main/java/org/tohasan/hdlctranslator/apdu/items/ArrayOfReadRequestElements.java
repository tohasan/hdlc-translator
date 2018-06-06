package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.FrameField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class ArrayOfReadRequestElements extends FrameField {

    public ArrayOfReadRequestElements(Frame frame) {
        super(frame);
    }

    @Override
    protected Frame initNestedFrame() {
        Optional<FrameItem> quantityOptional = this.frame.getItems().stream()
            .filter(item -> item instanceof Quantity)
            .findFirst();

        if (!quantityOptional.isPresent()) {
            throw new RuntimeException("Incorrect package structure");
        }

        FrameItem elementsQuantity = quantityOptional.get();

//        for (int i = 0; i < elementsQuantity.getBytes().get(0); i++) {
//            ));
//        }

//        ArrayList<FrameItem> rre = new ArrayList();
//        rre.add(new ReadRequestElement());
//
//        this.frame.setItems(rre);

        return new ReadRequestElement();
//                return this.frame;
    }
}
