package org.tohasan.hdlctranslator.apdu;

import org.tohasan.hdlctranslator.apdu.items.AarqLength;
import org.tohasan.hdlctranslator.apdu.items.AcseTag;
import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import org.tohasan.hdlctranslator.common.entities.impl.DynamicListField;

import java.util.Arrays;

/**
 * Application Assosiation Request – запрос ассоциации приложения с применением самого низкого уровня безопасности
 * <p>
 * author: IgorKaSan
 * date: 04.05.2018.
 */
public class ApplicationAssosiationRequest extends CommonFrame {

    public ApplicationAssosiationRequest() {
        this.setItems(Arrays.asList(
            new AarqLength(this),
            new DynamicListField<>(this, AcseTag.class)
        ));
    }
}
