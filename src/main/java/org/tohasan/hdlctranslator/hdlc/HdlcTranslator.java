package org.tohasan.hdlctranslator.hdlc;

import org.tohasan.hdlctranslator.apdu.items.*;
import org.tohasan.hdlctranslator.common.StringHelper;
import org.tohasan.hdlctranslator.entities.Frame;
import org.tohasan.hdlctranslator.entities.PackageItem;
import org.tohasan.hdlctranslator.hdlc.items.*;

import java.util.Arrays;

public class HdlcTranslator {
    private Frame frame;

    public HdlcTranslator() {
        this.resetFrame();
    }

    public String parse(String hexStr) {
        StringHelper stringHelper = new StringHelper(hexStr);
        HdlcPackage pack = new HdlcPackage(stringHelper.getBytes());

        this.resetFrame();

        for (PackageItem packageItem : frame.getItems()) {
            packageItem.extract(pack);
        }

        return frame.getDescription();
    }

    private void resetFrame() {
        this.frame = new HdlcFrame();
        this.frame.setItems(Arrays.asList(
            new FrameDelimiter(this.frame),
            new FrameFormatDefinition(this.frame),
            new Address(this.frame),
            new Address(this.frame),
            new ControlField(this.frame),
            new HeaderCheckSequence(this.frame),
         //   new InformationField(this.frame),
            new LLC(this.frame),
            new ApduTypeField(this.frame),
            new AarqLength(this.frame),  // для запроса ассоциации приложения
                // Элемент Службы Управления Ассоциацией tag [1]
            new AcseTag(this.frame),  // для ассоциации приложения
            new AcseLength(this.frame),  // для ассоциации приложения
            new AcseDataType(this.frame),  // для ассоциации приложения
            new AcseDataLength(this.frame),  // для ассоциации приложения
            new AcseValue(this.frame),  // для ассоциации приложения
                // Конец элемента Службы Управления Ассоциацией
                // Элемент Службы Управления Ассоциацией tag [6]
            new AcseTag(this.frame),  // для ассоциации приложения
            new AcseLength(this.frame),  // для ассоциации приложения
            new AcseDataType(this.frame),  // для ассоциации приложения
            new AcseDataLength(this.frame),  // для ассоциации приложения
            new AcseValue(this.frame),  // для ассоциации приложения
                // Конец элемента Службы Управления Ассоциацией
                // Элемент Службы Управления Ассоциацией tag [10]
            new AcseTag(this.frame),  // для ассоциации приложения
            new AcseLength(this.frame),  // для ассоциации приложения
            new AcseDataType(this.frame),  //
            new AcseDataLength(this.frame),  //
                // Конец элемента Службы Управления Ассоциацией
                // Элемент Службы Управления Ассоциацией tag [11]
            new AcseTag(this.frame),  // для ассоциации приложения
            new AcseLength(this.frame),  // для ассоциации приложения
            new AcseValue(this.frame),  // для ассоциации приложения
                // Конец элемента Службы Управления Ассоциацией
                // Элемент Службы Управления Ассоциацией tag [12]
            new AcseTag(this.frame),  // для ассоциации приложения
            new AcseLength(this.frame),  // для ассоциации приложения
            new AcseDataType(this.frame),  //
            new AcseDataLength(this.frame),  //
                // Конец элемента Службы Управления Ассоциацией
                // Элемент Службы Управления Ассоциацией tag [30]
            new AcseTag(this.frame),  // для ассоциации приложения
            new AcseLength(this.frame),  // для ассоциации приложения
            new AcseDataType(this.frame),  // для ассоциации приложения
            new AcseDataLength(this.frame),  // для ассоциации приложения
            new AcseValue(this.frame),  // комплексное значение, надо разбирать дальше
                // Конец элемента Службы Управления Ассоциацией
            new RawData(this.frame),  // не разобранные данные
            new FrameCheckSequence(this.frame),
            new FrameDelimiter(this.frame)
        ));
    }
}
