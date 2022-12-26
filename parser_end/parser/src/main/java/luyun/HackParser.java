package luyun;

import com.google.common.io.Files;
import luyun.parser.CommandLineParser;
import luyun.parser.command.common.FileNameContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author muzhe
 * @data 2022/12/26 9:05 上午
 */
public class HackParser {




    public static void main(String[] args) throws IOException {
        String vmFileName = "/Users/muzhewang/practice/workload/parser/src/main/resources/vm/StaticTest.vm";
        String hackFileName = "";
        File vmFile = new File(vmFileName);
        hackFileName = getHackFileName(vmFile);
        FileNameContext.setFileName(vmFileName.substring(vmFileName.lastIndexOf("/")+1));
        File hackFile = new File(hackFileName);
        List<String> commandLines = new ArrayList<String>();
        Scanner scanner = new Scanner(vmFile);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.trim().isEmpty() || command.trim().startsWith("//")) {
                continue;
            }
            List<String> commands = CommandLineParser.getCommand(command);
            commandLines.addAll(commands);
        }
        BufferedWriter bufferedWriter = Files.newWriter(hackFile, Charset.forName("UTF-8"));
        for (String str : commandLines) {
            bufferedWriter.write(str + "\n");
        }
        scanner.close();
        bufferedWriter.close();
        FileNameContext.clear();
    }

    private static String getHackFileName(File vmFile) {
        String absolutePath = vmFile.getAbsolutePath();
        if (!absolutePath.endsWith(".vm")) {
            throw new IllegalArgumentException("文件名非法");
        }
        String asmlPath = absolutePath.replace(".vm", ".asm");
        return asmlPath;
    }

}
