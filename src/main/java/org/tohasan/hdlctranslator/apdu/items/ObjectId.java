package org.tohasan.hdlctranslator.apdu.items;

import com.sun.deploy.util.StringUtils;
import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.math.BigInteger;

/**
 * ObjectId – (6 байт), определяет идентификатор объекта (ОБИС код).
 *
 * author: IgorKaSan
 * date: 04.05.2018.
*/
public class ObjectId extends CommonItem {

    public ObjectId(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "идентификатор объекта (OBIS код) - " +  getValue();
    }

    @Override
    public int size() {
        return 6;
    }

    private String getValue() {
        byte[] bytes;
        int size = this.size();
        int arrayMaxIndex = size - 1;
        String obis = "";


        bytes = new byte[size];

        for (int i = 0; i < size; i++) {
//            bytes[i] = super.getBytes().get(i);
            bytes[arrayMaxIndex - i] = super.getBytes().get(i);
            if (obis.length() > 0) { obis = Integer.toString(bytes[arrayMaxIndex - i] & 255) + "." + obis; }
            else {obis = Integer.toString(bytes[arrayMaxIndex - i] & 255) + obis;}
        }

//        Вариант преобразования байтового массива в строку отображаемую в шестнадцатеричной нотации
//        String str = new BigInteger(1, bytes).toString(16);
//        str = "000000000000".substring(1,size*2 - str.length() + 1) + str.toUpperCase();


//        return str.toUpperCase();
//        return String.format("%10s%n", str.toUpperCase());
        return obis;
    }
}
