package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.apdu.ApduFrame;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.Package;
import org.tohasan.hdlctranslator.common.entities.impl.FrameField;

/**
 * IF (Information Field - <информационное поле>)
 * Максимальное значение длины информационного поля 2030 байт (значение по умолчанию 128 байт)
 * <p>
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class InformationField extends FrameField {

    public InformationField(Frame frame) {
        super(frame);
    }

    @Override
    protected Frame initNestedFrame() {
        return new ApduFrame();
    }
}
