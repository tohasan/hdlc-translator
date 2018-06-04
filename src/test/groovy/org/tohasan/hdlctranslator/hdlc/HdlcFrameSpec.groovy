package org.tohasan.hdlctranslator.hdlc

import org.tohasan.hdlctranslator.common.entities.Frame
import spock.lang.Specification

class HdlcFrameSpec extends Specification {
    Frame hdlcFrame

    def setup() {
        hdlcFrame = new HdlcFrame()
    }

    def "should parse hdlc package without header checksum field (has no HCS (Header Check Sequence))"() {
        given:
        def result = hdlcFrame.parse('7E A0 0A 00 02 50 75 03 93 B7 E1 7E')

        expect:
        result ==
                '7E - разделитель кадров (frame delimiter)\n' +
                'A00A - определение формата кадра (frame format) - несегментированный кадр - длина кадра: 10\n' +
                '00025075 - адрес сервера (server address) = 1:5178 - логический адрес сервера (upper part server address):физический адрес сервера (lower part setver address)\n' +
                '03 - адрес клиента (client address) = 1\n' +
                '93 - управляющее поле (control field)\n' +
                'B7E1 - код целостности кадра (frame check sequence)\n' +
                '7E - разделитель кадров (frame delimiter)'
    }

    def "should parse hdlc package with header checksum field (has HCS (Header Check Sequence))"() {
        given:
        def result = hdlcFrame.parse('7E A0 15 03 00 02 50 75 73 83 C2 81 80 06 05 01 80 06 01 80 75 6C 7E')

        expect:
        result ==
                '7E - разделитель кадров (frame delimiter)\n' +
                'A015 - определение формата кадра (frame format) - несегментированный кадр - длина кадра: 21\n' +
                '03 - адрес клиента (client address) = 1\n' +
                '00025075 - адрес сервера (server address) = 1:5178 - логический адрес сервера (upper part server address):физический адрес сервера (lower part setver address)\n' +
                '73 - управляющее поле (control field)\n' +
                '83C2 - код целостности заголовка (header check sequence)\n' +
                '818006050180060180 - информационное поле (information field)\n' +
                '756C - код целостности кадра (frame check sequence)\n' +
                '7E - разделитель кадров (frame delimiter)'
    }

    //	Logical Device Name readout (SN request)
    def "should parse simple READ_REQUEST apdu package"() {
        given:
        def result = hdlcFrame.parse('7E A0 14 00 02 A0 AD 03 32 3A 58 E6 E6 00 05 01 02 FD 08 10 D3 7E')

        expect:
        result ==
                '7E - разделитель кадров (frame delimiter)\n' +
                'A014 - определение формата кадра (frame format) - несегментированный кадр - длина кадра: 20\n' +
                '0002A0AD - адрес сервера (server address) = 1:10326 - логический адрес сервера (upper part server address):физический адрес сервера (lower part setver address)\n' +
                '03 - адрес клиента (client address) = 1\n' +
                '32 - управляющее поле (control field)\n' +
                '3A58 - код целостности заголовка (header check sequence)\n' +
                'E6E600 - логическое управление каналом (logical link control)\n' +
                '05 - тип APDU пакета (APDU type) APDU[5]\n' +
                '01 - количество элементов в последовательности (Quantity) - 1\n' +
                '02 - тип переменной доступа (VAS type) VAS[2]\n' +
                'FD08 - короткое имя запрашиваемого объекта (ShortName)\n' +
                '10D3 - код целостности кадра (frame check sequence)\n' +
                '7E - разделитель кадров (frame delimiter)'
    }

    // 	Logical Device Name readout (SN response)
    def "should parse simple READ_RESPONSE apdu package"() {
        given:
        def result = hdlcFrame.parse('7E A0 25 03 00 02 A0 AD 52 8D 39 E6 E7 00 0C 01 02 01 00 01 0F 01 00 0A 0B 5A 49 50 30 31 37 36 39 33 32 36 8C 6F 7E')

        expect:
        result ==
                '7E - разделитель кадров (frame delimiter)\n' +
                'A025 - определение формата кадра (frame format) - несегментированный кадр - длина кадра: 37\n' +
                '03 - адрес клиента (client address) = 1\n' +
                '0002A0AD - адрес сервера (server address) = 1:10326 - логический адрес сервера (upper part server address):физический адрес сервера (lower part setver address)\n' +
                '52 - управляющее поле (control field)\n' +
                '8D39 - код целостности заголовка (header check sequence)\n' +
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
                '5A49503031373639333236 - значение элемента данных в последовательности (ItemValue)\n' +
                '8C6F - код целостности кадра (frame check sequence)\n' +
                '7E - разделитель кадров (frame delimiter)'
    }

    // 	AA using lowest security level (response)
    def "should parse simple AARQ - lowest security level"() {
        given:
        def result = hdlcFrame.parse('7E A0 2E 00 02 4A 19 21 10 B3 F4 E6 E6 00 60 1D A1 09 06 07 60 85 74 05 08 01 02 BE 10 04 0E 01 00 00 00 06 5F 1F 04 00 1C 03 20 00 00 38 5B 7E')

        expect:
        result ==
                '7E - разделитель кадров (frame delimiter)\n' +
                'A02E - определение формата кадра (frame format) - несегментированный кадр - длина кадра: 46\n' +
                '00024A19 - адрес сервера (server address) = 1:4748 - логический адрес сервера (upper part server address):физический адрес сервера (lower part setver address)\n' +
                '21 - адрес клиента (client address) = 16\n' +
                '10 - управляющее поле (control field)\n' +
                'B3F4 - код целостности заголовка (header check sequence)\n' +
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
                '01000000065F1F04001C03200000 - значения элемента службы управления ассоциацией (ACSE Value)\n' +
                '385B - код целостности кадра (frame check sequence)\n' +
                '7E - разделитель кадров (frame delimiter)'
    }

    // 	AA using low level authentication (LLS) with blank password (response)
    def "should parse simple AARQ - low level authentication with blank password"() {
        given:
        def result = hdlcFrame.parse('7E A0 4B 00 02 A0 AD 03 10 11 31 E6 E6 00 60 3A A1 09 06 07 60 85 74 05 08 01 02 A6 0A 04 08 45 47 4D 36 39 33 32 36 8A 02 07 80 8B 07 60 85 74 05 08 02 01 AC 02 80 00 BE 10 04 0E 01 00 00 00 06 5F 1F 04 00 1C 1B 20 00 00 B5 3C 7E')

        expect:
        result ==
                '7E - разделитель кадров (frame delimiter)\n' +
                'A04B - определение формата кадра (frame format) - несегментированный кадр - длина кадра: 75\n' +
                '0002A0AD - адрес сервера (server address) = 1:10326 - логический адрес сервера (upper part server address):физический адрес сервера (lower part setver address)\n' +
                '03 - адрес клиента (client address) = 1\n' +
                '10 - управляющее поле (control field)\n' +
                '1131 - код целостности заголовка (header check sequence)\n' +
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
                '80 - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - -128\n' +
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
                '01000000065F1F04001C1B200000 - значения элемента службы управления ассоциацией (ACSE Value)\n' +
                'B53C - код целостности кадра (frame check sequence)\n' +
                '7E - разделитель кадров (frame delimiter)'
    }

    def "should parse AssociationLN request (logical name access)"() {
        given:
        def result = hdlcFrame.parse('7E A0 1A 02 57 21 32 4F 91 E6 E6 00 C0 01 C1 00 0F 00 00 28 00 00 FF 02 00 91 53 7E')

        expect:
        result ==
                '7E - разделитель кадров (frame delimiter)\n' +
                'A01A - определение формата кадра (frame format) - несегментированный кадр - длина кадра: 26\n' +
                '0257 - адрес сервера (server address) = 1:43 - логический адрес сервера (upper part server address):физический адрес сервера (lower part setver address)\n' +
                '21 - адрес клиента (client address) = 16\n' +
                '32 - управляющее поле (control field)\n' +
                '4F91 - код целостности заголовка (header check sequence)\n' +
                'E6E600 - логическое управление каналом (logical link control)\n' +
                'C0 - тип APDU пакета (APDU type) APDU[192]\n' +
                '01 - тип GetRequest GR[1]\n' +
                'C1 - тип GetRequestNormal GRN[-63]\n' +
                '000F - идентификатор родительского класса - 0x15\n' +
                '0000280000FF - идентификатор объекта (OBIS код) - 0x-1\n' +
                '02 - идентификатор атрибута - 0x2\n' +
                '00 - флаг завершения описания атрибута - 0x0\n' +
                '9153 - код целостности кадра (frame check sequence)\n' +
                '7E - разделитель кадров (frame delimiter)'
    }

    def "should parse AssociationLN response (logical name access)"() {
        given:
        def result = hdlcFrame.parse('7E A0 19 21 02 57 52 51 5B E6 E7 00 C4 02 C1 00 00 00 00 01 00 02 01 50 99 F4 7E')

        expect:
        result ==
                '7E - разделитель кадров (frame delimiter)\n' +
                'A019 - определение формата кадра (frame format) - несегментированный кадр - длина кадра: 25\n' +
                '21 - адрес клиента (client address) = 16\n' +
                '0257 - адрес сервера (server address) = 1:43 - логический адрес сервера (upper part server address):физический адрес сервера (lower part setver address)\n' +
                '52 - управляющее поле (control field)\n' +
                '515B - код целостности заголовка (header check sequence)\n' +
                'E6E700 - логическое управление каналом (logical link control)\n' +
                'C4 - тип APDU пакета (APDU type) APDU[196]\n' +
                '02 - тип GetRequest GR[2]\n' +
                'C1 - тип GetRequestNormal GRN[-63]\n' +
                '00 - признак последнего блока (LastBlock flag)\n' +
                '00000001 - номер блока данных (BlockNumber) - 1\n' +
                '00 - значение диагностики источника результата (0x00 - success)\n' +
                '02 - длина блока данных в байтах (DataBlockLength) - 2\n' +
                '0150 - неразобранные данные (raw data)\n' +
                '99F4 - код целостности кадра (frame check sequence)\n' +
                '7E - разделитель кадров (frame delimiter)'
    }
}
