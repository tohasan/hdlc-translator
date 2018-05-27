package org.tohasan.hdlctranslator.hdlc;

import org.tohasan.hdlctranslator.common.StringHelper;
import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.entities.PackageItem;
import org.tohasan.hdlctranslator.hdlc.items.*;

import java.util.Arrays;

public class HdlcTranslator {
    private Frame frame;

    public HdlcTranslator() {
        this.resetFrame();
    }

    public String parse(String hexStr) {
        StringHelper stringHelper = new StringHelper(hexStr);
        HdlcPackage pack = new HdlcPackage(stringHelper.getBytes());

        this.resetFrame();

        for (PackageItem packageItem : frame.getItems()) {
            packageItem.extract(pack);
        }

        return frame.getDescription();
    }

    private void resetFrame() {
        this.frame = new HdlcFrame();
        this.frame.setItems(Arrays.asList(
            new FrameDelimiter(this.frame),
            new FrameFormatDefinition(this.frame),
            new Address(this.frame),
            new Address(this.frame),
            new ControlField(this.frame),
            new HeaderCheckSequence(this.frame),
            new InformationField(this.frame),
            new FrameCheckSequence(this.frame),
            new FrameDelimiter(this.frame)
        ));
    }
}
