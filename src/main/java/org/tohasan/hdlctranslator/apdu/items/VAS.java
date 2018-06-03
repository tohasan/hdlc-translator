package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * VariableAccessSpecification - VAS – (1 байт), специфицирует тип доступа (переменную доступа):
 * – доступ по имени объекта (variable-name ObjectName) - 0x02
 * – детальный доступ (detailed-access) – не используется в DLMS/COSEM - 0x03
 * – параметризованный доступ (Parameterized-Access) - 0x04
 * – доступ по номеру блока (Block-Number-Access) - 0x05
 * – доступ на по-блочное чтение данных (Read-Data-Block-Access) - 0x06
 * – доступ на по-блочную запись данных (Write-Data-Block-Access) - 0x07
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class VAS extends HdlcItem {

    public VAS(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "тип переменной доступа (VAS type) VAS[" +  Integer.toString(getValue()) + "]";
    }

    @Override
    public int size() {
        return 1;
    }

    private int getValue() {
        int code;
        code = super.getBytes().get(0);
        return code;
    }
}
