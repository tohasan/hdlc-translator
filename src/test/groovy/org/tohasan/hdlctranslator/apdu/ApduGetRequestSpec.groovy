package org.tohasan.hdlctranslator.apdu

import org.tohasan.hdlctranslator.common.entities.Frame
import spock.lang.Specification

/**
 * ApduGetRequestSpec – проверяет корректность обработки APDU фреймов типа GetRequest.
 *
 * ApduGetRequest применяется для запросов по логическому имени и имеет следующую структуру:
 * 	- GetRequestMode (1 байт) - тип GetRequest (GR[]),
 * 	- GetRequestVas (1 байт) - тип переменной доступа (VAS - VariableAccessSpecification) для определенного типа GetRequest (GRVAS[]),
 * 	- LongBlockNumber (4 байта) - номер блока данных (для GRVAS[2] – GetRequestNext),
 *  - DynamicListField (~ байт)	- массив GetRequestElement.
 *
 * author: IgorKaSan
 * date: 11.07.2018.
 */
class ApduGetRequestSpec extends Specification {
    Frame frame

    def setup() {
        frame = new ApduGetRequest()
    }

    def "should parse simple GetRequestNormal"() {
        // GET_REQUEST with one GetRequestElement frame
        given:
        def result = frame.parse('01 C1 00 0F 00 00 28 00 00 FF 02 00')

        expect:
        result ==
                '01 - тип GetRequest GR[1]\n' +
                'C1 - тип GetRequestNormal GRN[193]\n' +
                '000F - идентификатор родительского класса - 0x15\n' +
                '0000280000FF - идентификатор объекта (OBIS код) - 0.0.40.0.0.255\n' +
                '02 - идентификатор атрибута - 2\n' +
                '00 - флаг завершения описания атрибута - 0x0'
    }

    def "should parse multi elements GetRequestNormal"() {
        // GET_REQUEST with few GetRequestElement frames
        given:
        def result = frame.parse('01 C1 00 0F 00 00 28 00 00 FF 01 00 00 0F 00 00 28 00 00 FF 02 00')

        expect:
        result ==
                '01 - тип GetRequest GR[1]\n' +
                'C1 - тип GetRequestNormal GRN[193]\n' +
                '000F - идентификатор родительского класса - 0x15\n' +
                '0000280000FF - идентификатор объекта (OBIS код) - 0.0.40.0.0.255\n' +
                '01 - идентификатор атрибута - 1\n' +
                '00 - флаг завершения описания атрибута - 0x0\n' +
                '000F - идентификатор родительского класса - 0x15\n' +
                '0000280000FF - идентификатор объекта (OBIS код) - 0.0.40.0.0.255\n' +
                '02 - идентификатор атрибута - 2\n' +
                '00 - флаг завершения описания атрибута - 0x0'
    }

    def "should parse GetRequestForNextDataBlock"() {
        // GET_REQUEST with LongBlockNumber field and without GetRequestElement frame
        given:
        def result = frame.parse('02 C1 00 00 00 04')

        expect:
        result ==
                '02 - тип GetRequest GR[2]\n' +
                'C1 - тип GetRequestNormal GRN[193]\n' +
                '00000004 - номер блока данных (BlockNumber) - 4'
    }

    def "should parse ParametrezedGetRequest"() {
        // GET_REQUEST with parameters (Reading profile generic data for period)
        given:
        def result = frame.parse('01 C1 00 07 01 00 63 02 00 FF 02 01 01 02 04 02 04 12 00 08 09 06 00 00 01 00 00 FF 0F 02 12 00 00 09 0C 07 E2 05 01 02 01 00 26 FF FF 4C 00 09 0C 07 E2 05 06 07 02 1E 26 FF FF 4C 00 01 00')

        expect:
        result ==
                '01 - тип GetRequest GR[1]\n' +
                'C1 - тип GetRequestNormal GRN[193]\n' +
                '0007 - идентификатор родительского класса - 0x7\n' +
                '0100630200FF - идентификатор объекта (OBIS код) - 1.0.99.2.0.255\n' +
                '02 - идентификатор атрибута - 2\n' +
                '01 - флаг завершения описания атрибута - 0x1\n' +
                '01 - флаг начала описания параметров - 0x1\n' +
                '02 - тип данных (ItemType) - STRUCTURE (DataType[2])\n' +
                '04 - длина элемента данных в байтах (ItemLength) - 4\n' +
                '02 - тип данных (ItemType) - STRUCTURE (DataType[2])\n' +
                '04 - длина элемента данных в байтах (ItemLength) - 4\n' +
                '12 - тип данных (ItemType) - LONG_UNSIGNED (DataType[18])\n' +
                '0008 - значение элемента данных (ItemValue)\n' +
                '09 - тип данных (ItemType) - OCTET_STRING (DataType[9])\n' +
                '06 - длина элемента данных в байтах (ItemLength) - 6\n' +
                '0000010000FF - значение элемента данных (ItemValue) - идентификатор объекта (OBIS код) - 0.0.1.0.0.255\n' +
                '0F - тип данных (ItemType) - INTEGER (DataType[15])\n' +
                '02 - значение элемента данных (ItemValue)\n' +
                '12 - тип данных (ItemType) - LONG_UNSIGNED (DataType[18])\n' +
                '0000 - значение элемента данных (ItemValue)\n' +
                '09 - тип данных (ItemType) - OCTET_STRING (DataType[9])\n' +
                '0C - длина элемента данных в байтах (ItemLength) - 12\n' +
                '07E2050102010026FFFF4C00 - значение элемента данных (ItemValue)\n' +
                '09 - тип данных (ItemType) - OCTET_STRING (DataType[9])\n' +
                '0C - длина элемента данных в байтах (ItemLength) - 12\n' +
                '07E2050607021E26FFFF4C00 - значение элемента данных (ItemValue)\n' +
                '01 - тип данных (ItemType) - ARRAY (DataType[1])\n' +
                '00 - длина элемента данных в байтах (ItemLength) - 0'
    }
}