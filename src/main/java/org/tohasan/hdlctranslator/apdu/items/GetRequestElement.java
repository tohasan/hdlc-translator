package org.tohasan.hdlctranslator.apdu.items;

import org.tohasan.hdlctranslator.common.entities.impl.CommonFrame;
import java.util.Arrays;

/**
 * GetRequestElement - структура GetRequest запроса, определяющая запрашиваемый элемент:
 *  - ParentClassId (2 байта) (идентификатор родительского класса),
 *	- ObjectId (6 байт) (идентификатор объекта (OBIS код)),
 *	- AttributeId (1 байт) (идентификатор атрибута),
 *	- AttributeDescriptor (1 байт) (флаг завершения описания атрибута),
 *
 * author: IgorKaSan
 * date: 08.06.2018.
 */
public class GetRequestElement extends CommonFrame {

    public GetRequestElement() {
        this.setItems(Arrays.asList(
                new ParentClassId(this),
                new ObjectId(this),
                new AttributeId(this),
                new AttributeDescriptor(this)
        ));
    }
}