package hack.aseembler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author muzhe
 * @data 2022/12/8 7:24 下午
 */
public class HackAssembler {

    private static final String filePath = "/Users/muzhewang/practice/workload/HackAssembler/src/main/resources/rect/";

    public static void main(String[] args) throws IOException {

        //初始化符号表
        intiSymbolTable();
        //获取到奥文件地址
//        if (args == null || args.length < 2) {
//            throw new IllegalArgumentException("source file can not empty");
//        }
        ParserManager parserManager = new ParserManager();
        String assemFile = args[0];
//        String assemFile = filePath + "RectL.asm";
        Scanner scanner = new Scanner(new File(assemFile));
        parserManager.parse(scanner);
        List<String> binCodes = parserManager.parseResult();
        String binFile = args[1];
//        String binFile = filePath +"RectL.hack";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(binFile));
        for (String binCode : binCodes) {
            bufferedWriter.append(binCode);
            bufferedWriter.append("\n");
        }
        scanner.close();
        bufferedWriter.close();
    }

    private static void intiSymbolTable() {

        //初始化符号表.添加系统中的默认符号
        SymbolTable.putSymbol("R0", 0);
        SymbolTable.putSymbol("R1", 1);
        SymbolTable.putSymbol("R2", 2);
        SymbolTable.putSymbol("R3", 3);
        SymbolTable.putSymbol("R4", 4);
        SymbolTable.putSymbol("R5", 5);
        SymbolTable.putSymbol("R6", 6);
        SymbolTable.putSymbol("R7", 7);
        SymbolTable.putSymbol("R8", 8);
        SymbolTable.putSymbol("R9", 9);
        SymbolTable.putSymbol("R10", 10);
        SymbolTable.putSymbol("R11", 11);
        SymbolTable.putSymbol("R12", 12);
        SymbolTable.putSymbol("R13", 13);
        SymbolTable.putSymbol("R14", 14);
        SymbolTable.putSymbol("R15", 15);
        SymbolTable.putSymbol("SCREEN", 16384);
        SymbolTable.putSymbol("KBD", 24576);
        SymbolTable.putSymbol("SP", 0);
        SymbolTable.putSymbol("LCL", 1);
        SymbolTable.putSymbol("ARG", 2);
        SymbolTable.putSymbol("THIS", 3);
        SymbolTable.putSymbol("THAT", 4);


    }
}
