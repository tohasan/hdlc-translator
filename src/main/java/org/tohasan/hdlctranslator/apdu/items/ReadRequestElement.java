package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

/**
 * ReadRequestElement - структура ReadRequest запроса, определяющая запрашиваемый элемент:
 *  -	VAS (1 байт) - тип переменной доступа (VAS - VariableAccessSpecification),
 *	-	BlockNumber (2 байта) - номер блока данных (для VAS[5] – BlockNumberAccess),
 *	-	ShortName (2 байт) - короткое имя объекта (атрибута объекта).
 *
 * author: IgorKaSan
 * date: 07.06.2018.
 */
public class ReadRequestElement extends CommonFrame {

    public ReadRequestElement() {
        this.setItems(Arrays.asList(
                new Vas(this),
                new BlockNumber(this),
                new ShortName(this)
        ));
    }
}