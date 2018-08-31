package org.tohasan.hdlctranslator.apdu

import org.tohasan.hdlctranslator.common.entities.Frame
import spock.lang.Specification

/**
 * ApduGetResponseSpec – проверяет корректность обработки APDU фреймов типа GetResponse.
 *
 * ApduGetResponse применяется для ответов на запросы по логическому имени и имеет следующую структуру:
 *	- GetResponseMode (1 байт) - тип GetResponse (GRE[2]),
 *	- GetResponseVas (1 байт) - тип переменной доступа (VAS - VariableAccessSpecification) для определенного типа GetResponse (GREVAS),
 *	- LastBlock flag (1 байт) - признак последнего блока (для GREVAS[2] – GetResponseWithDatablock),
 *  - LongBlockNumber (4 байта) - номер блока данных (для GREVAS[2] – GetResponseWithDatablock),
 *	- ResultDiagnosticFlag (1 байт) - значение диагностики источника результата (0x00 - success),
 *	- DataBlockLength (1 байт) - длина блока данных в байтах (для GREVAS[2] – GetResponseWithDatablock),
 *  - DynamicListField (~ байт)	- массив GetResponseElement.
 *
 * author: IgorKaSan
 * date: 11.07.2018.
 */
class ApduGetResponseSpec  extends Specification {
    Frame frame

    def setup() {
        frame = new ApduGetResponse()
    }

    def "should parse simple GetResponseNormal"() {
        // GET_RESPONSE with no next following block and one GetResponseElement frame
        given:
        def result =  frame.parse('01 C1 00 12 00 08')

        expect:
        result ==
                '01 - тип GetResponse GRE[1]\n' +
                'C1 - тип GetResponseNormal GREN[193]\n' +
                '00 - значение диагностики источника результата (0x00 - success)\n' +
                '12 - тип данных (ItemType) - LONG_UNSIGNED (DataType[18])\n' +
                '0008 - значение элемента данных (ItemValue)'
    }

    def "should parse multi elements GetResponseNormal"() {
        // GET_RESPONSE with no next following block and few GetResponseElement frames
        given:
        def result =  frame.parse('01 C1 00 02 02 0F FD 16 21')

        expect:
        result ==
                '01 - тип GetResponse GRE[1]\n' +
                'C1 - тип GetResponseNormal GREN[193]\n' +
                '00 - значение диагностики источника результата (0x00 - success)\n' +
                '02 - тип данных (ItemType) - STRUCTURE (DataType[2])\n' +
                '02 - длина элемента данных в байтах (ItemLength) - 2\n' +
                '0F - тип данных (ItemType) - INTEGER (DataType[15])\n' +
                'FD - значение элемента данных (ItemValue)\n' +
                '16 - тип данных (ItemType) - ENUM (DataType[22])\n' +
                '21 - значение элемента данных (ItemValue)'
    }

    def "should parse simple GetResponseWithDatablock"() {
        // GET_RESPONSE with next following block flag and one GetResponseElement frame
        given:
        def result =  frame.parse('02 C1 00 00 00 00 01 00 02 01 50')

        expect:
        result ==
                '02 - тип GetResponse GRE[2]\n' +
                'C1 - тип GetResponseNormal GREN[193]\n' +
                '00 - признак последнего блока (LastBlock flag)\n' +
                '00000001 - номер блока данных (BlockNumber) - 1\n' +
                '00 - значение диагностики источника результата (0x00 - success)\n' +
                '02 - длина блока данных в байтах (DataBlockLength) - 2\n' +
                '01 - тип данных (ItemType) - ARRAY (DataType[1])\n' +
                '50 - длина элемента данных в байтах (ItemLength) - 80'
    }

    def "should parse multi elements GetResponseWithDatablock"() {
        // GET_RESPONSE with next following block flag and few GetResponseElement frames
        given:
        def result =  frame.parse('02 C1 00 00 00 00 02 00 0F 02 04 12 00 08 11 00 09 06 00 00 01 00 00 FF')

        expect:
        result ==
                '02 - тип GetResponse GRE[2]\n' +
                'C1 - тип GetResponseNormal GREN[193]\n' +
                '00 - признак последнего блока (LastBlock flag)\n' +
                '00000002 - номер блока данных (BlockNumber) - 2\n' +
                '00 - значение диагностики источника результата (0x00 - success)\n' +
                '0F - длина блока данных в байтах (DataBlockLength) - 15\n' +
                '02 - тип данных (ItemType) - STRUCTURE (DataType[2])\n' +
                '04 - длина элемента данных в байтах (ItemLength) - 4\n' +
                '12 - тип данных (ItemType) - LONG_UNSIGNED (DataType[18])\n' +
                '0008 - значение элемента данных (ItemValue)\n' +
                '11 - тип данных (ItemType) - UNSIGNED (DataType[17])\n' +
                '00 - значение элемента данных (ItemValue)\n' +
                '09 - тип данных (ItemType) - OCTET_STRING (DataType[9])\n' +
                '06 - длина элемента данных в байтах (ItemLength) - 6\n' +
                '0000010000FF - значение элемента данных (ItemValue) - идентификатор объекта (OBIS код) - 0.0.1.0.0.255'
    }

    def "should parse multi elements GetResponseWithDatablock with ripped end element"() {
        // GET_RESPONSE with next following block flag, few GetResponseElement frames and ripped end element
        given:
        def result =  frame.parse('02 C1 00 00 00 00 01 00 65 01 4D 02 04 12 00 03 09 06 01 00 01 08 00 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 01 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 02 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 03 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 04 FF 0F 02 12 00 00 02 04 12 00 03 09 06 01 00')

        expect:
        result ==
                '02 - тип GetResponse GRE[2]\n' +
                'C1 - тип GetResponseNormal GREN[193]\n' +
                '00 - признак последнего блока (LastBlock flag)\n' +
                '00000002 - номер блока данных (BlockNumber) - 2\n' +
                '00 - значение диагностики источника результата (0x00 - success)\n' +
                '0F - длина блока данных в байтах (DataBlockLength) - 15\n' +
                '02 - тип данных (ItemType) - STRUCTURE (DataType[2])\n' +
                '04 - длина элемента данных в байтах (ItemLength) - 4\n' +
                '12 - тип данных (ItemType) - LONG_UNSIGNED (DataType[18])\n' +
                '0008 - значение элемента данных (ItemValue)\n' +
                '11 - тип данных (ItemType) - UNSIGNED (DataType[17])\n' +
                '00 - значение элемента данных (ItemValue)\n' +
                '09 - тип данных (ItemType) - OCTET_STRING (DataType[9])\n' +
                '06 - длина элемента данных в байтах (ItemLength) - 6\n' +
                '0000010000FF - значение элемента данных (ItemValue) - идентификатор объекта (OBIS код) - 0.0.1.0.0.255'
    }
}