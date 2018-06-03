package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.apdu.ApduFrame;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.Package;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.List;

/**
 * IF (Information Field - <информационное поле>)
 * Максимальное значение длины информационного поля 2030 байт (значение по умолчанию 128 байт)
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class InformationField extends CommonItem {
    private Frame apduFrame;

    public InformationField(Frame frame) {
        super(frame);
        this.apduFrame = new ApduFrame();
    }

    @Override
    public List<Byte> extract(Package pack) {
        getBytes().clear();
        getBytes().addAll(this.apduFrame.extract(pack));
        return getBytes();
    }

    @Override
    public String getDescription() {
        return !this.empty() ? apduFrame.getDescription() : "";
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
