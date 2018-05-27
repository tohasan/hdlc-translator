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
}
