package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * ResultDiagnosticFlag – (1 байт), специфицирует значение диагностики источника результата
 * 0x00 - успешно (success).
 *
 * author: IgorKaSan
 * date: 31.05.2018.
 */

public class ResultDiagnosticFlag extends HdlcItem {

    public ResultDiagnosticFlag(Frame frame) {
        super(frame);
    }

    @Override
    protected String getDescriptionTip() {
        return "значение диагностики источника результата (0x00 - success)";
    }

    @Override
    public int size() {
        return 1;
    }
}