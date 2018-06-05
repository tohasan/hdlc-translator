package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.FrameField;

import java.util.Optional;

public class ArrayOfReadRequestElements extends FrameField {

    public ArrayOfReadRequestElements(Frame frame) {
        super(frame);
    }

    @Override
    protected Frame initNestedFrame() {
        Optional<FrameItem> apduTypeOptional = this.frame.getItems().stream()
            .filter(item -> item instanceof Quantity)
            .findFirst();

        if (!apduTypeOptional.isPresent()) {
            throw new RuntimeException("Incorrect package structure");
        }

        FrameItem elementsQuantity = apduTypeOptional.get();

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
