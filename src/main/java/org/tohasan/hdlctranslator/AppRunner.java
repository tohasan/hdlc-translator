package org.tohasan.hdlctranslator;

import org.tohasan.hdlctranslator.common.entities.Frame;
import org.tohasan.hdlctranslator.hdlc.HdlcFrame;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author: IgorKaSan
 * date: 04.03.2018.
 */
public class AppRunner {
    private final static String HDLC_FLOW_FILENAME = "hdlcflow.txt";
    private final static String RESULT_FILENAME = "hdlcflow.parsed.txt";

    public static void main(String[] args) {
        Frame hdleFrame = new HdlcFrame();

        try (
            Stream<String> stream = Files.lines(Paths.get(HDLC_FLOW_FILENAME));
            PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get(RESULT_FILENAME)))
        ) {
            String result = stream
                .map(hdleFrame::parse)
                .collect(Collectors.joining("\n\n"));

            pw.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
