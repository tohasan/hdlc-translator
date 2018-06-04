package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.Package;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

import java.util.List;

/**
 * CA (ClientAddress - <адрес клиента>) – 1 байт, значение идентифицирует клиента.
 *         Зарезервированные адреса клиента:
 *             0x00 – Нет станции (No-station),
 *             0x01 – Процесс управляется клиентом (Client Management Process),
 *             0x10 – Общий доступ (Public Client – самый низкий уровень безопасности).
 *         Адрес клиента используется для выбора уровня доступа. При кодировании адреса клиента резервируется младший значащий бит, который затем устанавливается в единицу.
 * SA (ServerAddress - <адрес сервера>) – состоит из двух частей,
 *     верхняя часть (upper part) – это <логический адрес устройства> (logical device address),
 *     нижняя часть (lower part) – это <физический адрес устройства> (physical device address).
 * Возможны три варианта адресации сервера:
 *     Однобайтовая адресация (1 байт). Есть только верхний адрес (logical device address). В некоторых случаях, нижняя часть может быть опущена.
 *     Двухбайтовая адресация (2 байта). Есть верхний адрес (logical device address) – 1 байт и нижний адрес (physical device address) – 1 байт.
 *     Четырехбайтовая адресация (4 байта). Есть верхний адрес – 2 байта и нижний адрес – два байта.
 * При кодировании адреса сервера резервируется младший значащий бит каждого байта (бит расширения), которые затем устанавливаются в ноль,
 *                                     и только младший значащий бит нижней части адреса устанавливается в единицу.
 * Примечание: Адрес сервера может быть длиной 1, 2 или 4 байта.
 *             Для адресации серверов используется метод расширенной адресации, при этом адрес сервера может быть разделен на «Верхний» и «Нижний».
 *             «Верхний» адрес может быть адресом логического устройства внутри физического устройства,
 *             а «нижний» – адресом физического устройства при многоточечной конфигурации сети.
 *             «Верхний» адрес должен присутствовать обязательно, «Нижний» может отсутствовать.
 *             Признаком наличия «Нижнего» адреса является нулевой младший бит в байте «Верхнего» адреса.
 *             При однобайтовой адресации младший бит адреса должен быть установлен в «1»,
 *             при многобайтовой адресации младшие биты всех байт, кроме последнего, должны быть установлены в «0», а у последнего – в «1».
 *             Содержимое адреса располагается в старших 7 битах каждого адреса, таким образом, адресное пространство при 1 байтовой адресации составляет от 0х00 до 0х7F,
 *                                                                                                                  а при 2-х байтовой адресации – от 0х00 до 0х3FFF.
 * Примечание: Верхняя и нижняя части адреса кодируются независимо друг от друга и уже в закодированном виде подставляются в поле адреса.
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class Address extends CommonItem {
    private final static byte MASK_IS_FINAL_PART = 0x01;
    private final static byte MASK_SIGN_REMOVE = 0x7F;

    private final static int ONE_BYTE_ADDRESS = 1;
    private final static int TWO_BYTE_ADDRESS = 2;
    private final static int FOUR_BYTE_ADDRESS = 4;

    private int upperPart = 0;
    private int lowerPart = 0;

    public Address(Frame frame) {
        super(frame);
    }

    @Override
    public List<Byte> extract(Package pack) {
        getBytes().clear();

        byte addressPart;
        do {
            addressPart = pack.nextByte();
            getBytes().add(0, addressPart);
        } while ((addressPart & MASK_IS_FINAL_PART) == 0);

        return getBytes();
    }

    @Override
    protected String getDescriptionTip() {
        if (this.isClient()) {
            return "адрес клиента (client address) = " + getValue();
        }

        return "адрес сервера (server address) = " + getValue() +
            " - логический адрес сервера (upper part server address):физический адрес сервера (lower part setver address)";
    }

    @Override
    public int size() {
        return getBytes().size();
    }

    private String getValue() {
        switch (this.getBytes().size()) {
            case ONE_BYTE_ADDRESS:
                upperPart = super.getBytes().get(0) >> 1 & MASK_SIGN_REMOVE;
                break;

            case TWO_BYTE_ADDRESS:
                upperPart = super.getBytes().get(1) >> 1 & MASK_SIGN_REMOVE;
                lowerPart = super.getBytes().get(0) >> 1 & MASK_SIGN_REMOVE;
                break;

            case FOUR_BYTE_ADDRESS:
                lowerPart = (super.getBytes().get(1) >> 1 & MASK_SIGN_REMOVE) << 7 | (super.getBytes().get(0) >> 1 & MASK_SIGN_REMOVE);
                // upperPart = ((super.getBytes().get(1) >>> 1) << 7) | (super.getBytes().get(0) >>> 1);
                // upper 1: 1 1 1 1 1 1 0 0
                // upper 0: 1 1 0 1 1 0 1 0
                // -> upper 1: 0 1 1 1 1 1 1 0
                // -> upper 0: 0 1 1 0 1 1 0 1
                // upper 1 << 7 -> 0 1 1 1 1 1 1 0 0 0 0 0 0 0
                // upper 0:                    0 1 1 0 1 1 0 1
                // | или +

                // 1 1 0 1 1 0 1 0 -> 1 1 1 0 1 1 0 1
                //                    0 1 1 1 1 1 1 1 = 0x7F
                // & наложить маску
                upperPart = (super.getBytes().get(3) >> 1 & MASK_SIGN_REMOVE) << 7 | (super.getBytes().get(2) >> 1 & MASK_SIGN_REMOVE);
                break;
        }

        return this.isClient() ? Integer.toString(upperPart) : String.format("%d:%d", upperPart, lowerPart);
    }

    private boolean isClient() {
        return getBytes().size() == 1;
    }
}
