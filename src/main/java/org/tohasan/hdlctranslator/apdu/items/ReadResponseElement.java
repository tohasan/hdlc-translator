package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

public class ReadResponseElement extends CommonFrame {

    public ReadResponseElement() {
        this.setItems(Arrays.asList(
                new ResultDiagnosticFlag(this),  // для ответа на запрос на чтение
                new ItemType(this),  // для ответа на запрос на чтение
                new ItemLength(this),  // для ответа на запрос на чтение
                new ItemValue(this)  // для ответа на запрос на чтение
        ));
    }
}