package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * ObjectId – (6 байт), определяет идентификатор объекта (ОБИС код).
 * <p>
 * author: IgorKaSan
 * date: 04.05.2018.
 */
public class ObjectId extends CommonItem {

    public ObjectId(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "идентификатор объекта (OBIS код) - " + getParsedValue();
    }

    @Override
    public int size() {
        return 6;
    }

    private String getParsedValue() {
        int size = this.size();
        int arrayMaxIndex = size - 1;
        StringBuilder obis = new StringBuilder();

        byte[] bytes = new byte[size];

        for (int i = 0; i < size; i++) {
            bytes[arrayMaxIndex - i] = super.getBytes().get(i);
            if (0 < obis.length()) {
                obis.insert(0, Integer.toString(bytes[arrayMaxIndex - i] & 255) + ".");
            } else {
                obis.insert(0, Integer.toString(bytes[arrayMaxIndex - i] & 255));
            }
        }

        return obis.toString();
    }
}
