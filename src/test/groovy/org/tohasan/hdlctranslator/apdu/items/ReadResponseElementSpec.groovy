package org.tohasan.hdlctranslator.apdu.items

import org.tohasan.hdlctranslator.common.entities.Frame
import spock.lang.Specification

/**
 * ReadResponseElementSpec – проверяет корректность обработки значения элемента данных (ReadResponseElement).
 *
 * ReadResponseElement имеет следующую структуру:
 * 	-	ItemType (1 байт) - тип данных элемента в последовательности,
 * 	-	ItemLength (1 байт) - длина элемента данных (или поля длины данных ItemLengthExtend),
 *  -	ItemLengthExtend (~ байт, определяется ItemLength) - длина элемента данных (для байтовых строк в байтах),
 * 	-	ItemValue (~ байт, определяется ItemType и ItemLength) - значение элемента данных.
 *
 * author: IgorKaSan
 * date: 12.07.2018.
 */
class ReadResponseElementSpec extends Specification {
    Frame frame

    def setup() {
        frame = new ReadResponseElement()
    }

    def "should parse data element ARRAY type"() {
        given:
        def result = frame.parse('02 02')

        expect:
        result ==
                '02 - тип данных (ItemType) - STRUCTURE (DataType[2])\n' +
                '02 - длина элемента данных в байтах (ItemLength) - 2'
    }

    def "should parse data element BIT_STRING type"() {
        given:
        def result = frame.parse('04 18 00 10 1C')

        expect:
        result ==
                '04 - тип данных (ItemType) - BIT_STRING (DataType[4])\n' +
                '18 - длина элемента данных в байтах (ItemLength) - 24\n' +
                '00101C - значение элемента данных (ItemValue)'
    }

    def "should parse data element OCTET_STRING type"() {
        given:
        def result = frame.parse('09 06 00 00 01 00 00 FF')

        expect:
        result ==
                '09 - тип данных (ItemType) - OCTET_STRING (DataType[9])\n' +
                '06 - длина элемента данных в байтах (ItemLength) - 6\n' +
                '0000010000FF - значение элемента данных (ItemValue) - идентификатор объекта (OBIS код) - 0.0.1.0.0.255'
    }

    def "should parse data element UNSIGNED type"() {
        given:
        def result = frame.parse('11 00')

        expect:
        result ==
                '11 - тип данных (ItemType) - UNSIGNED (DataType[17])\n' +
                '00 - значение элемента данных (ItemValue)'
    }

    def "should parse data element LONG_UNSIGNED type"() {
        given:
        def result = frame.parse('12 00 08')

        expect:
        result ==
                '12 - тип данных (ItemType) - LONG_UNSIGNED (DataType[18])\n' +
                '0008 - значение элемента данных (ItemValue)'
    }

    def "should parse data element INTEGER type"() {
        given:
        def result = frame.parse('0F FD')

        expect:
        result ==
                '0F - тип данных (ItemType) - INTEGER (DataType[15])\n' +
                'FD - значение элемента данных (ItemValue)'
    }

    def "should parse data element ENUM type"() {
        given:
        def result = frame.parse('16 21')

        expect:
        result ==
                '16 - тип данных (ItemType) - ENUM (DataType[22])\n' +
                '21 - значение элемента данных (ItemValue)'
    }

    def "should signal about element with unknown data type"() {
        given:
        def result = frame.parse('13 02')

        expect:
        result ==
                '13 - тип данных (ItemType) - НЕИЗВЕСТНЫЙ_ТИП (DataType[19])'
    }
}
