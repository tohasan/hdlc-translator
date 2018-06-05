package org.tohasan.hdlctranslator.apdu.items

import org.tohasan.hdlctranslator.apdu.ApduReadRequest
import org.tohasan.hdlctranslator.common.entities.Frame
import spock.lang.Specification

class AcseTagSpec extends Specification {
    Frame frame

    def setup() {
        frame = new AcseTag()
    }

    def "should parse tag without data type (mechanism name)"() {
        given:
        def result = frame.parse('8B 07 60 85 74 05 08 02 01')

        expect:
        result ==
                '8B - тег элемента управления ассоциацией (ACSE Tag) - [139]\n' +
                '07 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 7\n' +
                '60857405080201 - значения элемента службы управления ассоциацией (ACSE Value)'
    }

    def "should parse tag with service value of value length"() {
        given:
        def result = frame.parse('8A 02 07 80')

        expect:
        result ==
                '8A - тег элемента управления ассоциацией (ACSE Tag) - [138]\n' +
                '02 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 2\n' +
                '07 - тип данных закодированного значения ACSE (ACSE Data Type) - DataType[7]\n' +
                '80 - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - 0'
    }

    def "should parse usual tag with all fields"() {
        given:
        def result = frame.parse('A1 09 06 07 60 85 74 05 08 01 02')

        expect:
        result ==
                'A1 - тег элемента управления ассоциацией (ACSE Tag) - [161]\n' +
                '09 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 9\n' +
                '06 - тип данных закодированного значения ACSE (ACSE Data Type) - DataType[6]\n' +
                '07 - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - 7\n' +
                '60857405080102 - значения элемента службы управления ассоциацией (ACSE Value)'
    }

    def "should parse tag with zero length of value"() {
        given:
        def result = frame.parse('AC 02 80 00')

        expect:
        result ==
                'AC - тег элемента управления ассоциацией (ACSE Tag) - [172]\n' +
                '02 - длина элемента службы управления ассоциацией в байтах (ACSE Length) - 2\n' +
                '80 - тип данных закодированного значения ACSE (ACSE Data Type) - DataType[-128]\n' +
                '00 - длина значения элемента службы управления ассоциацией в байтах (ACSE Data Length) - 0'
    }
}
