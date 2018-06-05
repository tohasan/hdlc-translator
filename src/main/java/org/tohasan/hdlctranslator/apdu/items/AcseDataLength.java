package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.AcseTagType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * ACSE Data Length – (1 байт), специфицирует длину закодированного значения ACSE (Association Control Service Element - Элемент Службы Управления Ассоциацией) в байтах.
 * <p>
 * author: IgorKaSan
 * date: 01.06.2018.
 */
public class AcseDataLength extends CommonItem {
    private final static byte SERVICE_VALUE = (byte) 0x80;

    AcseDataLength(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - " + Integer.toString(getValue());
    }

    @Override
    public int size() {
        return !isMechanismName() ? 1 : 0;
    }

    @Override
    public int getValue() {
        if (isMechanismName()) {
            return getGeneralLength();
        }
        return super.getBytes().get(0) != SERVICE_VALUE ? super.getBytes().get(0) & 255 : 0;
    }

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isMechanismName() {
        Optional<FrameItem> typeOptional = this.frame.getItems().stream()
            .filter(item -> item instanceof AcseTagName)
            .findFirst();

        return typeOptional.isPresent() && AcseTagType.MECHANISM_NAME.getValue() == typeOptional.get().getValue();
    }

    private int getGeneralLength() {
        Optional<FrameItem> lengthOptional = this.frame.getItems().stream()
            .filter(item -> item instanceof AcseLength)
            .findFirst();

        return lengthOptional.map(FrameItem::getValue).orElse(0);
    }
}
