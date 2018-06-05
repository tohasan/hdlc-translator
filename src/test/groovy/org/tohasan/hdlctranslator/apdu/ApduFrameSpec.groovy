package org.tohasan.hdlctranslator.apdu

import org.tohasan.hdlctranslator.common.entities.Frame
import spock.lang.Specification

class ApduFrameSpec extends Specification {
    Frame frame

    def setup() {
        frame = new ApduFrame()
    }

    //	Logical Device Name readout (SN request)
    def "should parse simple READ_REQUEST apdu package"() {
        given:
        def result = frame.parse('E6 E6 00 05 01 02 FD 08')

        expect:
        result ==
                'E6E600 - логическое управление каналом (logical link control)\n' +
                '05 - тип APDU пакета (APDU type) APDU[5]\n' +
                '01 - количество элементов в последовательности (Quantity) - 1\n' +
                '02 - тип переменной доступа (VAS type) VAS[2]\n' +
                'FD08 - короткое имя запрашиваемого объекта (ShortName)'
    }

    // 	Logical Device Name readout (SN response)
    def "should parse simple READ_RESPONSE apdu package"() {
        given:
        def result = frame.parse('E6 E7 00 0C 01 02 01 00 01 0F 01 00 0A 0B 5A 49 50 30 31 37 36 39 33 32 36')

        expect:
        result ==
                'E6E700 - логическое управление каналом (logical link control)\n' +
                '0C - тип APDU пакета (APDU type) APDU[12]\n' +
                '01 - количество элементов в последовательности (Quantity) - 1\n' +
                '02 - тип переменной доступа (ReadResponseFormat) ReadResponse[2]\n' +
                '01 - признак последнего блока (LastBlock flag)\n' +
                '0001 - номер блока данных (BlockNumber) - 1\n' +
                '0F - длина блока данных в байтах (DataBlockLength) - 15\n' +
                '01 - номер элемента данных в последовательности (ItemNumber) - 1\n' +
                '00 - значение диагностики источника результата (0x00 - success)\n' +
                '0A - тип данных элемента данных в последовательности (ItemType) - DataType[10]\n' +
                '0B - длина элемента данных в байтах (ItemLength) - 11\n' +
                '5A49503031373639333236 - значение элемента данных в последовательности (ItemValue)'
    }

    // 	AA using lowest security level (response)
    def "should parse simple AARQ - lowest security level"() {
        given:
        def result = frame.parse('E6 E6 00 60 1D A1 09 06 07 60 85 74 05 08 01 02 BE 10 04 0E 01 00 00 00 06 5F 1F 04 00 1C 03 20 00 00')

        expect:
        result ==
                'E6E600 - логическое управление каналом (logical link control)\n' +
                '60 - тип APDU пакета (APDU type) APDU[96]\n' +
                '1D - длина запроса ассоциации приложения в байтах (AARQ Length) - 29\n' +
                'A1 - тег элемента управления ассоциацией (ACSE Tag) - [161]\n' +
                '09 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 9\n' +
                '06 - тип данных закодированного значения ACSE (ACSE Data Type) - DataType[6]\n' +
                '07 - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - 7\n' +
                '60857405080102 - значения элемента службы управления ассоциацией (ACSE Value)\n' +
                'BE - тег элемента управления ассоциацией (ACSE Tag) - [190]\n' +
                '10 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 16\n' +
                '04 - тип данных закодированного значения ACSE (ACSE Data Type) - DataType[4]\n' +
                '0E - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - 14\n' +
                '01000000065F1F04001C03200000 - значения элемента службы управления ассоциацией (ACSE Value)'
    }

    // 	AA using low level authentication (LLS) with blank password (response)
    def "should parse simple AARQ - low level authentication with blank password"() {
        given:
        def result = frame.parse('E6 E6 00 60 3A A1 09 06 07 60 85 74 05 08 01 02 A6 0A 04 08 45 47 4D 36 39 33 32 36 8A 02 07 80 8B 07 60 85 74 05 08 02 01 AC 02 80 00 BE 10 04 0E 01 00 00 00 06 5F 1F 04 00 1C 1B 20 00 00')

        expect:
        result ==
                'E6E600 - логическое управление каналом (logical link control)\n' +
                '60 - тип APDU пакета (APDU type) APDU[96]\n' +
                '3A - длина запроса ассоциации приложения в байтах (AARQ Length) - 58\n' +
                'A1 - тег элемента управления ассоциацией (ACSE Tag) - [161]\n' +
                '09 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 9\n' +
                '06 - тип данных закодированного значения ACSE (ACSE Data Type) - DataType[6]\n' +
                '07 - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - 7\n' +
                '60857405080102 - значения элемента службы управления ассоциацией (ACSE Value)\n' +
                'A6 - тег элемента управления ассоциацией (ACSE Tag) - [166]\n' +
                '0A - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 10\n' +
                '04 - тип данных закодированного значения ACSE (ACSE Data Type) - DataType[4]\n' +
                '08 - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - 8\n' +
                '45474D3639333236 - значения элемента службы управления ассоциацией (ACSE Value)\n' +
                '8A - тег элемента управления ассоциацией (ACSE Tag) - [138]\n' +
                '02 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 2\n' +
                '07 - тип данных закодированного значения ACSE (ACSE Data Type) - DataType[7]\n' +
                '80 - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - 0\n' +
                '8B - тег элемента управления ассоциацией (ACSE Tag) - [139]\n' +
                '07 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 7\n' +
                '60857405080201 - значения элемента службы управления ассоциацией (ACSE Value)\n' +
                'AC - тег элемента управления ассоциацией (ACSE Tag) - [172]\n' +
                '02 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 2\n' +
                '80 - тип данных закодированного значения ACSE (ACSE Data Type) - DataType[-128]\n' +
                '00 - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - 0\n' +
                'BE - тег элемента управления ассоциацией (ACSE Tag) - [190]\n' +
                '10 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 16\n' +
                '04 - тип данных закодированного значения ACSE (ACSE Data Type) - DataType[4]\n' +
                '0E - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - 14\n' +
                '01000000065F1F04001C1B200000 - значения элемента службы управления ассоциацией (ACSE Value)'
    }

    def "should parse AssociationLN request (logical name access)"() {
        given:
        def result =  frame.parse('E6 E6 00 C0 01 C1 00 0F 00 00 28 00 00 FF 02 00')

        expect:
        result ==
                'E6E600 - логическое управление каналом (logical link control)\n' +
                'C0 - тип APDU пакета (APDU type) APDU[192]\n' +
                '01 - тип GetRequest GR[1]\n' +
                'C1 - тип GetRequestNormal GRN[-63]\n' +
                '000F - идентификатор родительского класса - 0x15\n' +
                '0000280000FF - идентификатор объекта (OBIS код) - 0.0.40.0.0.255\n' +
                '02 - идентификатор атрибута - 0x2\n' +
                '00 - флаг завершения описания атрибута - 0x0'
    }

    def "should parse AssociationLN response (logical name access)"() {
        given:
        def result =  frame.parse('E6 E7 00 C4 02 C1 00 00 00 00 01 00 02 01 50')

        expect:
        result ==
                'E6E700 - логическое управление каналом (logical link control)\n' +
                'C4 - тип APDU пакета (APDU type) APDU[196]\n' +
                '02 - тип GetRequest GR[2]\n' +
                'C1 - тип GetRequestNormal GRN[-63]\n' +
                '00 - признак последнего блока (LastBlock flag)\n' +
                '00000001 - номер блока данных (BlockNumber) - 1\n' +
                '00 - значение диагностики источника результата (0x00 - success)\n' +
                '02 - длина блока данных в байтах (DataBlockLength) - 2\n' +
                '0150 - неразобранные данные (raw data)'
    }
}
