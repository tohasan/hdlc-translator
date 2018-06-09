package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.*;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.FrameField;

import java.util.Optional;

/**
 * ApduContent определяет прикладное назначение (тип) APDU пакета.
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */
public class ApduContent extends FrameField {

    public ApduContent(Frame frame) {
        super(frame);
    }

    @Override
    protected Frame initNestedFrame() {
        Optional<FrameItem> apduTypeOptional = this.frame.getItems().stream()
            .filter(item -> item instanceof ApduTypeField)
            .findFirst();

        if (!apduTypeOptional.isPresent()) {
            throw new RuntimeException("Incorrect package structure");
        }

        FrameItem apduType = apduTypeOptional.get();

        switch (apduType.getBytes().get(0)) {
            case ApduTypeField.READ_REQUEST:
                return new ApduReadRequest();
            case ApduTypeField.READ_RESPONSE:
                return new ApduReadResponse();
            case ApduTypeField.WRITE_REQUEST:
                return new ApduWriteRequest();
            case ApduTypeField.WRITE_RESPONSE:
                return new ApduWriteResponse();
            case ApduTypeField.APPLICATION_ASSOCIATION_REQUEST:
                return new ApplicationAssosiationRequest();
            case ApduTypeField.APPLICATION_ASSOCIATION_RESPONSE:
                return new ApplicationAssosiationRequest();
            case ApduTypeField.GET_REQUEST:
                return new ApduGetRequest();
            case ApduTypeField.GET_RESPONSE:
                return new ApduGetResponse();
            default:
                System.out.println("WARN Unsupported apdu type");
                return null;
        }
    }
}
