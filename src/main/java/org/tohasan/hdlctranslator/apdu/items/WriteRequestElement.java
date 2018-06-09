package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;

import java.util.Arrays;

/**
 * WriteRequestElement - структура WriteRequest запроса, определяющая запрашиваемый элемент:
 *  -	VAS (1 байт) - тип переменной доступа (VAS - VariableAccessSpecification),
 *  -	ShortName (2 байт) – короткое имя записываемого объекта (атрибута объекта),
 *	-	ItemType (1 байт) тип данных элемента в последовательности,
 *	-	ItemLength (1 байт) длина элемента данных (или поля длины данных ItemLengthExtend),
 *  -	ItemLengthExtend (~ байт, определяется ItemLength) длина элемента данных (для байтовых строк в байтах),
 *	-	ItemValue (~ байт, определяется ItemType и ItemLength) значение элемента данных.
 *
 * author: IgorKaSan
 * date: 09.06.2018.
 */
public class WriteRequestElement extends CommonFrame {

    public WriteRequestElement() {
        this.setItems(Arrays.asList(
//                new Vas(this),
//                new ShortName(this),
                new ItemType(this),
                new ItemLength(this),
                new ItemLengthExtend(this),
                new ItemValue(this)
        ));
    }
}