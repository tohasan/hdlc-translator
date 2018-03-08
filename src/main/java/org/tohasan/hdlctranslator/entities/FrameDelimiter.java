package org.tohasan.hdlctranslator.entities;

/**
 * author: IgorKaSan
 * date: 04.03.2018.
 */
public class FrameDelimiter {
    // FD (Frame Delimiter - <разделитель кадров>) – 1 байт,
    // все HDLC кадры должны начинаться и заканчиваться полем флага "01111110" (0x7E).
    private Byte frameDelimiter;

    public Byte getFrameDelimiter() {
        return frameDelimiter;
    }

    public void setFrameDelimiter(Byte frameDelimiter) {
        this.frameDelimiter = frameDelimiter;
    }
}
