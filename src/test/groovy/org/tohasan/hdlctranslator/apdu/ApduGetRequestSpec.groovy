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
}