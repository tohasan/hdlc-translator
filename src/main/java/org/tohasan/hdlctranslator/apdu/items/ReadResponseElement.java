package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

/**
 * ReadResponseElement - структура ReadResponse ответа, определяющая запрашиваемый элемент:
 *  -	ResultDiagnosticFlag (1 байт) - значение диагностики источника результата (0x00 - success) (возможно элемент заголовка ReadResponse ответа),
 *	-	ItemType (1 байт) - тип данных элемента в последовательности,
 *	-	ItemLength (1 байт) - длина элемента данных (или поля длины данных ItemLengthExtend),
 *  -	ItemLengthExtend (~ байт, определяется ItemLength) - длина элемента данных (для байтовых строк в байтах),
 *	-	ItemValue (~ байт, определяется ItemType и ItemLength) - значение элемента данных.
 *
 * author: IgorKaSan
 * date: 07.06.2018.
 */
public class ReadResponseElement extends CommonFrame {

    public ReadResponseElement() {
        this.setItems(Arrays.asList(
//                new ResultDiagnosticFlag(this),
                new ItemType(this),
                new ItemLength(this),
                new ItemLengthExtend(this),
                new ItemValue(this)
        ));
    }
}