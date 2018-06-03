package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.Llc;
import org.tohasan.hdlctranslator.apdu.items.RawData;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.PackageItem;

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
                new Llc(this.frame),
                new RawData(this.frame)
        ));
    }
}
