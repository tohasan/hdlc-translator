package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.FrameItem;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;
import org.tohasan.hdlctranslator.hdlc.enums.ControlFieldType;

import java.util.Optional;

/**
 * HCS (Header Check Sequence - <код целостности заголовка>) – (2 байта).
 * Отсутствует в SNRM запросе и может отсутствовать в других командах,
 * типа, Receive ready, при этом в ответе присутствует.
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class HeaderCheckSequence extends CommonItem {

    public HeaderCheckSequence(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "код целостности заголовка (header check sequence)";
    }

    @Override
    public int size() {
        Optional<FrameItem> controlFieldOptional = this.frame.getItems().stream()
            .filter(item -> item instanceof ControlField)
            .findFirst();

        int defaultSize = 2;
        if (!controlFieldOptional.isPresent()) {
            return defaultSize;
        }

        FrameItem controlField = controlFieldOptional.get();

        return controlField.getBytes().get(0) != ControlFieldType.SNRM.getValue() ? defaultSize : 0;
    }
}
