package org.tohasan.hdlctranslator.entities;

import org.tohasan.hdlctranslator.common.Preprocessor;

/**
 * author: IgorKaSan
 * date: 04.03.2018.
 */
public class FrameFormatDefinition {
    // FF (Frame Format - <формат кадра>) – 2 байта:
    //  [15..12] биты – <Тип кадра> (FrameType), всегда "1010" (0xA). Возможные типы кадров: (I frame) READ REQUEST, (I frame) READ REASPONSE, (RR frame) RECEIVE READY,
    //  [11] – <Признак сегментирования кадра> (Segmentation). Возможные значения поля: 0 – единственный (последний) кадр, 1 – есть следующий кадр,
    //  [10..8] – не используются (RFU),
    //  [7..0] – <Длина кадра в байтах> (Length). При расчете длины кадра флаги начала и конца кадра не учитываются.
    private byte[] frameFormatDefinition;

    public void setFrameFormatDefinition(byte[] frameFormatDefinition) {
        this.frameFormatDefinition = frameFormatDefinition;
    }

    public byte[] getFrameFormatDefinition() {
        return frameFormatDefinition;
    }

    public int getFrameSize() {
        return (int) this.frameFormatDefinition[1];
    }

}
