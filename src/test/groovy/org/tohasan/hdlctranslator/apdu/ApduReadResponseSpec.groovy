package org.tohasan.hdlctranslator.apdu

import org.tohasan.hdlctranslator.common.entities.Frame
import spock.lang.Specification


/**
 * ApduReadResponseSpec – проверяет корректность обработки APDU фреймов типа ReadResponse.
 *
 * ApduReadResponse применяется для ответов на запросы по короткому имени и имеет следующую структуру:
 * 	- Quantity (1 байт) - количество элементов в последовательности,
 * 	- ReadResponseFormat (1 байт) - формат/тип ответа на запрос (например: [2] – data-block-result),
 * 	- LastBlock flag (1 байт) - признак последнего блока,
 *  - BlockNumber (2 байта) - номер блока данных,
 * 	- DataBlockLength (1 байт) - длина блока данных в байтах,
 * 	- Quantity (1 байт) - количество элементов в блоке данных,
 *  - ResultDiagnosticFlag (1 байт) - значение диагностики источника результата (0x00 - success),
 *  - DynamicListField (~ байт)	- массив ReadResponseElement.
 *
 * author: IgorKaSan
 * date: 12.07.2018.
 */
class ApduReadResponseSpec extends Specification {
    Frame frame

    def setup() {
        frame = new ApduReadResponse()
    }

    def "should parse simple ReadResponse"() {
        // READ_RESPONSE with one simple ReadResponseElement frame
        given:
        def result = frame.parse('01 02 01 00 01 0F 01 00 0A 0B 5A 49 50 30 31 37 36 39 33 32 36')

        expect:
        result ==
                '01 - количество элементов в последовательности (Quantity) - 1\n' +
                '02 - тип переменной доступа (ReadResponseFormat) ReadResponse[2]\n' +
                '01 - признак последнего блока (LastBlock flag)\n' +
                '0001 - номер блока данных (BlockNumber) - 1\n' +
                '0F - длина блока данных в байтах (DataBlockLength) - 15\n' +
                '01 - количество элементов в последовательности (Quantity) - 1\n' +
                '00 - значение диагностики источника результата (0x00 - success)\n' +
                '0A - тип данных (ItemType) - VISIBLE_STRING (DataType[10])\n' +
                '0B - длина элемента данных в байтах (ItemLength) - 11\n' +
                '5A49503031373639333236 - значение элемента данных в последовательности (ItemValue)'
    }

    def "should parse multi elements ReadResponse"() {
        // READ_RESPONSE with few ReadResponseElement frames
        given:
        def result = frame.parse('01 02 00 00 01 6D 02 00 12 00 08 09 06 00 00 01 00 00 FF')

        expect:
        result ==
                '01 - количество элементов в последовательности (Quantity) - 1\n' +
                '02 - тип переменной доступа (ReadResponseFormat) ReadResponse[2]\n' +
                '00 - признак последнего блока (LastBlock flag)\n' +
                '0001 - номер блока данных (BlockNumber) - 1\n' +
                '6D - длина блока данных в байтах (DataBlockLength) - 109\n' +
                '02 - количество элементов в последовательности (Quantity) - 2\n' +
                '00 - значение диагностики источника результата (0x00 - success)\n' +
                '12 - тип данных (ItemType) - LONG_UNSIGNED (DataType[18])\n' +
                '0008 - значение элемента данных в последовательности (ItemValue)\n' +
                '09 - тип данных (ItemType) - OCTET_STRING (DataType[9])\n' +
                '06 - длина элемента данных в байтах (ItemLength) - 6\n' +
                '0000010000FF - значение элемента данных в последовательности (ItemValue)'
    }
}