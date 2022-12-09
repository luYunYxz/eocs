package hack.aseembler;

import hack.aseembler.parser.HackParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author muzhe
 * @data 2022/12/8 7:42 下午
 */
public class ParserManager {


    //当前系统中所有的parser
    private List<Parser> parsers = new ArrayList<>();

    /**
     * 根据文件流
     *
     * @param itr 文件列表
     */
    public void parse(Scanner itr) {
        //给定一个文件列表,将其内部转变为一些列的Parser
        while (itr.hasNext()) {
            String instruction = itr.nextLine().trim();
            if (isInstruction(instruction)) {
                if (isLabel(instruction)) {
                    //获取到标签的结果
                    String label = getLabel(instruction);
                    SymbolTable.putSymbol(label.trim(), parsers.size());
                } else {
                    String realInstruction = getInstruction(instruction);
                    parsers.add(new HackParser(realInstruction));
                }
            }
        }
    }

    /**
     * 将后面的注释给去除掉.
     *
     * @param instruction
     * @return
     */
    private String getInstruction(String instruction) {
        int noteIndex = instruction.indexOf("//");
        if (noteIndex!=-1){
            return instruction.substring(0,noteIndex ).replaceAll(" ", "");
        }else {
            return instruction.replaceAll(" ","");
        }
    }

    /**
     * 获取标签就是将最开始的和最后的括号去除掉.同时将前后的空格去除
     *
     * @param instruction
     * @return
     */
    private String getLabel(String instruction) {
        return instruction
                .substring(1, instruction.length() - 1)
                .trim();
    }

    /**
     * 判断当前命令是否是标签.
     * <p>
     * 是否以 括号括起来的一个字符串
     *
     * @param instruction
     * @return
     */
    private boolean isLabel(String instruction) {
        String pattern = "^\\(.+\\)$";
        boolean isMatch = Pattern.matches(pattern, instruction);
        return isMatch;
    }

    public List<String> parseResult() {

        List<String> binFiles = new ArrayList<>(parsers.size());
        for (Parser parser : parsers) {
            String binCode = parser.parse().translate();
            binFiles.add(binCode);
        }
        return binFiles;
    }

    /**
     * 是否是一条指令.剔除掉注释和非指令
     * instruction中是没有空格的
     *
     * @param instruction
     * @return
     */
    private boolean isInstruction(String instruction) {
        if (instruction == null || instruction.length() == 0 || instruction.startsWith("//")) {
            return false;
        } else {
            return true;
        }
    }
}
