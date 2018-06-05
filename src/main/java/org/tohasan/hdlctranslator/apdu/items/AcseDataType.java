package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.apdu.enums.AcseTagType;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.Optional;

/**
 * ACSE Data Type – (1 байт), специфицирует тип данных закодированного значения ACSE (Association Control Service Element - Элемент Службы Управления Ассоциацией).
 * <p>
 * author: IgorKaSan
 * date: 01.06.2018.
 */
public class AcseDataType extends CommonItem {

    AcseDataType(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тип данных закодированного значения ACSE (ACSE Data Type) - DataType[" + Integer.toString(getValue()) + "]";
    }

    @Override
    public int size() {
        return !isMechanismName() ? 1 : 0;
    }

    // TODO: Убрать заточку на конкретное значение в типе. Обобщить это!
    private boolean isMechanismName() {
        Optional<FrameItem> typeOptional = this.frame.getItems().stream()
            .filter(item -> item instanceof AcseTagName)
            .findFirst();

        return typeOptional.isPresent() && AcseTagType.MECHANISM_NAME.getValue() == typeOptional.get().getValue();
    }
}