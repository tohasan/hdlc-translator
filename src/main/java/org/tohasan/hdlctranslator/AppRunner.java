package org.tohasan.hdlctranslator;

import org.tohasan.hdlctranslator.common.Postprocessor;
import org.tohasan.hdlctranslator.common.Preprocessor;
import org.tohasan.hdlctranslator.entities.PackageItem;
import org.tohasan.hdlctranslator.hdlc.HdlcFrame;
import org.tohasan.hdlctranslator.hdlc.HdlcPackage;
import org.tohasan.hdlctranslator.hdlc.items.*;
import org.tohasan.hdlctranslator.processor.HdlcProcessor;

import java.io.*;
import java.util.Arrays;
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
                BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
                Preprocessor preprocessor = new Preprocessor(buffer.readLine());

                HdlcPackage pack = new HdlcPackage(preprocessor.getBytes());

                System.out.println(new Postprocessor(preprocessor.getBytes()).getString() + "\n");

                HdlcFrame frame = new HdlcFrame(Arrays.asList(
                        new FrameDelimiter(),
                        new FrameFormatDefinition(),
                        new ClientAddress(),
                        new ServerAddress(),
                        new HeaderCheckSequence(),
                        new ControlField(),
                        new InformationField(),
                        new FrameCheckSequence(),
                        new FrameDelimiter()
                ));

                for (PackageItem packageItem : frame.getItems()) {
                    packageItem.extract(pack, frame);
                }

                System.out.println(frame.getDescription());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
