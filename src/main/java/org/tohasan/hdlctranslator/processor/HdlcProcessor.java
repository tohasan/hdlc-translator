package org.tohasan.hdlctranslator.processor;

import org.tohasan.hdlctranslator.common.Postprocessor;
import org.tohasan.hdlctranslator.common.Preprocessor;
import org.tohasan.hdlctranslator.entities.FrameFormatDefinition;
import org.tohasan.hdlctranslator.entities.HdlcFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * author: IgorKaSan
 * date: 04.03.2018.
 */
public class HdlcProcessor {
    private InputStream inputStream;
    public HdlcProcessor(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<HdlcFrame> extractMessage() {
        // Инициализируем список HDLC кадров
        List<HdlcFrame> hdlcFrameList = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            String hdlcMessage;

            while ((hdlcMessage = buffer.readLine()) != null){
                Preprocessor preprocessor = new Preprocessor(hdlcMessage);
                byte[] bytes = preprocessor.getBytes();
                int byteArrayLength = bytes.length;

                byte[] startDelimiter = new byte[1];
                startDelimiter[0] = bytes[0];
                byte[] frameFormat = new byte[2];
                frameFormat[0] = bytes[1];
                frameFormat[1] = bytes[2];
                FrameFormatDefinition frameFormatDefinition = new FrameFormatDefinition();
                frameFormatDefinition.setFrameFormatDefinition(frameFormat);
                byte[] addressField = new byte[5];
                addressField[0] = bytes[3];
                addressField[1] = bytes[4];
                addressField[2] = bytes[5];
                addressField[3] = bytes[6];
                addressField[4] = bytes[7];
                byte[] controlField = new byte[1];
                controlField[0] = bytes[8];
                byte[] headerCheckSequence = new byte[2];
                headerCheckSequence[0] = bytes[9];
                headerCheckSequence[1] = bytes[10];
                byte[] informationField = new byte[byteArrayLength - 14];
                int j = 0;
                for (int i = 11; i < byteArrayLength - 3; i++) {
                    informationField[j] = bytes[i];
                    j++;
                }
                byte[] frameCheckSequence = new byte[2];
                frameCheckSequence[0] = bytes[byteArrayLength - 3];
                frameCheckSequence[1] = bytes[byteArrayLength - 2];
                byte[] endDelimiter = new byte[1];
                endDelimiter[0] = bytes[byteArrayLength - 1];

                //Postprocessor postprocessor = new Postprocessor(bytes);
                //String startDelemiterDefinition = new Postprocessor(startDelimiter).getString();
                System.out.println(new Postprocessor(bytes).getString() + "\n");

                System.out.println(new Postprocessor(startDelimiter).getString() + " - флаг начала последовательности");
//                System.out.println(new Postprocessor(frameFormat).getString() + " - определение формата кадра");
                System.out.println(new Postprocessor(frameFormatDefinition.getFrameFormatDefinition()).getString() + " - определение формата кадра");
                System.out.println("-- " + Integer.toString(frameFormatDefinition.getFrameSize()) + " - длина (размер) кадра ("+ (Integer.toString(bytes.length)) + ")");
                System.out.println(new Postprocessor(addressField).getString() + " - адресное поле");
                System.out.println(new Postprocessor(controlField).getString() + " - управляющее поле");
                System.out.println(new Postprocessor(headerCheckSequence).getString() + " - код целостности заголовка");
                System.out.println("Информационное поле:" + new Postprocessor(informationField).getString());
                System.out.println(new Postprocessor(frameCheckSequence).getString() + " - код целостности HDLC кадра");
                System.out.println(new Postprocessor(endDelimiter).getString() + " - флаг конца последовательности");
                // Обработка (разбор) строки (сообщения)
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return hdlcFrameList;
    }

}
