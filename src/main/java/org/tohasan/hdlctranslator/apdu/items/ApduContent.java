package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.ApduAssosiationRequest;
import org.tohasan.hdlctranslator.apdu.ApduReadRequest;
import org.tohasan.hdlctranslator.apdu.ApduReadResponse;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.Package;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.List;
import java.util.Optional;

public class ApduContent extends CommonItem {
    private Frame apduContent;

    public ApduContent(Frame frame) {
        super(frame);
    }

    @Override
    public List<Byte> extract(Package pack) {
        Optional<FrameItem> apduTypeOptional = this.frame.getItems().stream()
            .filter(item -> item instanceof ApduTypeField)
            .findFirst();

        if (!apduTypeOptional.isPresent()) {
            throw new RuntimeException("Incorrect package structure");
        }

        FrameItem apduType = apduTypeOptional.get();

        getBytes().clear();

        switch (apduType.getBytes().get(0)) {
            case ApduTypeField.READ_REQUEST:
                this.apduContent = new ApduReadRequest();
                break;
            case ApduTypeField.READ_RESPONSE:
                this.apduContent = new ApduReadResponse();
                break;
            case ApduTypeField.APPLICATION_ASSOCIATION_REQUEST:
                this.apduContent = new ApduAssosiationRequest();
                break;
            default:
                this.apduContent = null;
                System.out.println("WARN Unsupported apdu type");
        }

        if (this.apduContent != null) {
            getBytes().addAll(this.apduContent.extract(pack));
        }

        return getBytes();
    }

    @Override
    public String getDescription() {
        return this.apduContent != null && !this.empty() ? this.apduContent.getDescription() : "";
    }

    @Override
    protected String getDescriptionTip() {
        return "";
    }

    @Override
    public int size() {
        return getBytes().size();
    }
}
