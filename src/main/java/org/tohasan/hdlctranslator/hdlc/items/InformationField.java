package org.tohasan.hdlctranslator.hdlc.items;

import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.entities.Package;
import org.tohasan.hdlctranslator.entities.PackageItem;
import org.tohasan.hdlctranslator.hdlc.HdlcItem;

import java.util.List;

/**
 * IF (Information Field - <информационное поле>)
 * Максимальное значение длины информационного поля 2030 байт (значение по умолчанию 128 байт)
 *
 * author: IgorKaSan
 * date: 09.03.2018.
 */
public class InformationField extends HdlcItem {

    @Override
    public void extract(Package pack, Frame frame) {
        List<PackageItem> items = frame.getItems();

        int sumFieldSizeExcludeCurrent = 0;
        for (PackageItem item : items) {
            if (item != this) {
                sumFieldSizeExcludeCurrent += item.size();
            }
        }

        int size = pack.size() - sumFieldSizeExcludeCurrent;

        for (int i = 0; i < size; i++) {
            getBytes().add(0, pack.nextByte());
        }
    }

    @Override
    public StringBuffer getDescription() {

        StringBuffer description = new StringBuffer().append("Информационное поле (information field): ");

        description.append(super.getDescription());
        return description;
    }

    @Override
    public int size() {
        return getBytes().size();
    }
}
