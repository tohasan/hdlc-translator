package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.common.entities.impl.CommonItem;

/**
 * CF (Control Field - <управляющее поле>) – 1 байт, задает тип команды или ответа, а также значения счетчиков, отправленных/принятых кадров,
 *     [7..5] биты – <значение счетчика принятых кадров> (RFNumber),
 *     [4] – <бит опроса/завершения> (PF – poll/final),
 *     [3..1] – <значение счетчика отправленных кадров> (SFNumber),
 *     [0] – <тип кадра> (FrameType).
 *     Возможные типы кадров и соответствующие им значения:
 *         0 – I – Информационный кадр (Information frame).
 *         1 – RR – Готов к приему (Receive ready)
 *         1 – RNR – Не готов к приему (Receive not ready)
 *         1 – SNRM – Установить режим нормального ответа (Set Normal Response Mode)
 *         1 – DISC – Разъединить (Disconnect)
 *         1 – UA – Ненумерованное подтверждение (Unnumbered Acknowledgment)
 *
 * author: IgorKaSan
 * date: 10.03.2018.
 */
public class ControlField  extends CommonItem {

    public ControlField(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "управляющее поле (control field)";
    }

    @Override
    public int size() {
        return 1;
    }

}
