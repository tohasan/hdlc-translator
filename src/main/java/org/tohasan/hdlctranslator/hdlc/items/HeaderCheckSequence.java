package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.hdlc.HdlcItem;

/**
 * HCS (Header Check Sequence - <код целостности заголовка>) – (2 байта).
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class HeaderCheckSequence extends HdlcItem {

    @Override
    public StringBuffer getDescription() {
        StringBuffer description = super.getDescription();

        description.append(" - код целостности заголовка (header check sequence)");
        return description;
    }

    @Override
    public int size() {
        return 2;
    }
}
