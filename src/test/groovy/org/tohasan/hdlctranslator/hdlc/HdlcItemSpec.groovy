package org.tohasan.hdlctranslator.hdlc

import org.tohasan.hdlctranslator.common.entities.Package
import org.tohasan.hdlctranslator.common.entities.impl.DataPackage
import org.tohasan.hdlctranslator.common.utils.StringHelper
import spock.lang.Specification

class HdlcItemSpec extends Specification {
    HdlcItem item
    Package pack

    def setup() {
        item = new HdlcItem(new HdlcFrame()) {
            @Override
            int size() {
                return 2
            }

            @Override
            protected String getDescriptionTip() {
                return 'no description'
            }
        }

        def stringHelper = new StringHelper('7E A0 0A 00 02 50 75 03 93 B7 E1 7E')
        pack = new DataPackage(stringHelper.getBytes())
    }

    def "should extract specified number of bytes if package size is fixed"() {
        given:
        pack.nextByte()
        item.extract(pack)

        expect:
        item.getBytes().size() == 2
    }

    def "should extract bytes in reverse order oppose to input hex string"() {
        given:
        pack.nextByte()
        item.extract(pack)

        expect:
        item.getBytes() == [(byte) 0x0A, (byte) 0xA0]
    }

    def "should prepare description as hex string of bytes"() {
        given:
        pack.nextByte()
        item.extract(pack)

        expect:
        item.getDescription() == 'A00A - no description'
    }
}
