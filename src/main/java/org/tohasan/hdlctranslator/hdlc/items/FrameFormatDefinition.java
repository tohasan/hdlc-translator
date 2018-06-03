package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.Package;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.List;

/**
 * FF (Frame Format - <формат кадра>) – 2 байта:
 *      [15..12] биты – <Тип кадра> (FrameType), всегда "1010" (0xA). Возможные типы кадров:
 *              (I frame) READ REQUEST,
 *              (I frame) READ RESPONSE,
 *              (RR frame) RECEIVE READY,
 *      [11] – <Признак сегментирования кадра> (Segmentation). Возможные значения поля:
 *                  0 – единственный (последний) кадр,
 *                  1 – есть следующий кадр,
 *      [10..8] – не используются (RFU),
 *      [7..0] – <Длина кадра в байтах> (Length). При расчете длины кадра флаги начала и конца кадра не учитываются.
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class FrameFormatDefinition extends CommonItem {
    private final static byte MASK_IS_SEGMENTED = 0x08;

    private boolean isSegmented;
    private byte frameSize;

    public FrameFormatDefinition(Frame frame) {
        super(frame);
    }

    @Override
    public List<Byte> extract(Package pack) {
        super.extract(pack);

        byte flags = getBytes().get(1);
        isSegmented = 0 < (flags & MASK_IS_SEGMENTED);

        frameSize = getBytes().get(0);

        return getBytes();
    }

    @Override
    protected String getDescriptionTip() {
        return "определение формата кадра (frame format)" +
            (isSegmented ? " - сегментированный кадр" : " - несегментированный кадр") +
            String.format(" - длина кадра: %d", frameSize);
    }

    @Override
    public int size() {
        return 2;
    }
}
