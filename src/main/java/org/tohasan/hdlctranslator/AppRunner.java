package org.tohasan.hdlctranslator;

import org.tohasan.hdlctranslator.entities.HdlcFrame;
import org.tohasan.hdlctranslator.processor.HdlcProcessor;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * author: IgorKaSan
 * date: 04.03.2018.
 */
public class AppRunner {
        private final static String HDLC_FLOW_FILENAME = "hdlcflow.txt";
        public static void main(String[] args){

            try (
                    InputStream inputStream = new FileInputStream(HDLC_FLOW_FILENAME);
//                    FileWriter writer = new FileWriter("hdlcdef.txt", false)
            ) {
                HdlcProcessor processor = new HdlcProcessor(inputStream);
                List<HdlcFrame> hdlcFrameList = processor.extractMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
