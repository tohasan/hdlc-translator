package org.tohasan.hdlctranslator.hdlc;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.entities.PackageItem;
import org.tohasan.hdlctranslator.hdlc.items.*;

import java.util.Arrays;

public class ApduTranslator {
    private Frame frame;

    public ApduTranslator() {
        this.resetFrame();
    }

    public String parse(byte[] bytes) {
        ApduPackage pack = new ApduPackage(bytes);

        this.resetFrame();

        for (PackageItem packageItem : frame.getItems()) {
            packageItem.extract(pack);
        }

        return frame.getDescription();
    }

    private void resetFrame() {
        this.frame = new ApduFrame();
        this.frame.setItems(Arrays.asList(
                new LLC(this.frame),
                new RawData(this.frame)
        ));
    }
}
