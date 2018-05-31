package org.tohasan.hdlctranslator.hdlc

import spock.lang.Specification

class HdlcTranslatorSpec extends Specification {
    HdlcTranslator hdlcTranslator

    def setup() {
        hdlcTranslator = new HdlcTranslator()
    }

    def "should parse hdlc package without header checksum field (has no HCS (Header Check Sequence))"() {
        given:
        def result = hdlcTranslator.parse('7E A0 0A 00 02 50 75 03 93 B7 E1 7E')

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
        def result = hdlcTranslator.parse('7E A0 15 03 00 02 50 75 73 83 C2 81 80 06 05 01 80 06 01 80 75 6C 7E')

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

    //	Logical Device Name readout (request)
    def "should parse simple READ_REQUEST apdu package"() {
        given:
        def result = hdlcTranslator.parse('7E A0 14 00 02 A0 AD 03 32 3A 58 E6 E6 00 05 01 02 FD 08 10 D3 7E')

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

    // 	Logical Device Name readout (response)
    def "should parse simple READ_RESPONSE apdu package"() {
        given:
        def result = hdlcTranslator.parse('7E A0 25 03 00 02 A0 AD 52 8D 39 E6 E7 00 0C 01 02 01 00 01 0F 01 00 0A 0B 5A 49 50 30 31 37 36 39 33 32 36 8C 6F 7E')

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
}
