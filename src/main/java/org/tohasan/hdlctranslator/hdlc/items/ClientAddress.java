package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * CA (ClientAddress - <адрес клиента>) – 1 байт, значение идентифицирует клиента.
 *         Зарезервированные адреса клиента:
 *             0x00 – Нет станции (No-station),
 *             0x01 – Процесс управляется клиентом (Client Management Process),
 *             0x10 – Общий доступ (Public Client – самый низкий уровень безопасности).
 *         Адрес клиента используется для выбора уровня доступа. При кодировании адреса клиента резервируется младший значащий бит, который затем устанавливается в единицу.
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class ClientAddress extends HdlcItem {

    public ClientAddress(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "адрес клиента (client address) = " + Integer.toString(getValue());
    }

    @Override
    public int size() {
        return 1;
    }

    private int getValue() {
        int address;
        address = super.getBytes().get(0) >> 1;
        return address;
    }
}
