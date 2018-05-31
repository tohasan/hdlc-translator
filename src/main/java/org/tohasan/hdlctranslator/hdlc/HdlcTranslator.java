package org.tohasan.hdlctranslator.hdlc;

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
            new Quantity(this.frame),
         //   new VAS(this.frame),  // для запроса на чтение
         //   new ShortName(this.frame),  // для запроса на чтение
                // Заголовок блока данных
            new ReadResponseFormat(this.frame),  // для ответа на запрос на чтение
            new LastBlock(this.frame),  // для ответа на запрос на чтение
            new BlockNumber(this.frame),  // для ответа на запрос на чтение
            new DataBlockLength(this.frame),  // для ответа на запрос на чтение
                // конец заголовка блока (длина блока данных рассматривается в составе заголовка)
            new ItemNumber(this.frame),  // для ответа на запрос на чтение
            new ResultDiagnosticFlag(this.frame),  // для ответа на запрос на чтение
            new ItemType(this.frame),  // для ответа на запрос на чтение
            new ItemLength(this.frame),  // для ответа на запрос на чтение
            new ItemValue(this.frame),  // для ответа на запрос на чтение
            new RawData(this.frame),  // не разобранные данные
            new FrameCheckSequence(this.frame),
            new FrameDelimiter(this.frame)
        ));
    }
}
