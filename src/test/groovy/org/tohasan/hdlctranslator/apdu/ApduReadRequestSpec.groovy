package org.tohasan.hdlctranslator.apdu

import org.tohasan.hdlctranslator.common.entities.Frame
import spock.lang.Specification

class ApduReadRequestSpec extends Specification {
    Frame frame

    def setup() {
        frame = new ApduReadRequest()
    }

    def "should parse simple READ_REQUEST apdu package"() {
        given:
        def result = frame.parse('01 02 FD 08')

        expect:
        result ==
                '01 - количество элементов в последовательности (Quantity) - 1\n' +
                '02 - тип переменной доступа (VAS type) VAS[2]\n' +
                'FD08 - короткое имя запрашиваемого объекта (ShortName)'
    }
}
