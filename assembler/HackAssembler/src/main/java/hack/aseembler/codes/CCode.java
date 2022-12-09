package hack.aseembler.codes;

import hack.aseembler.Code;

import java.util.HashMap;
import java.util.Map;

/**
 * @author muzhe
 * @data 2022/12/9 9:30 上午
 */
public class CCode implements Code {

    private static Map<String, String> CMP_CODE_MAP = new HashMap<String, String>();
    private static Map<String, String> DEST_CODE_MAP = new HashMap<>();
    private static Map<String, String> JUMP_CODE_MAP = new HashMap<>();

    static {

        CMP_CODE_MAP.put("0", "0101010");
        CMP_CODE_MAP.put("1", "0111111");
        CMP_CODE_MAP.put("-1", "0111010");
        CMP_CODE_MAP.put("D", "0001100");
        CMP_CODE_MAP.put("A", "0110000");
        CMP_CODE_MAP.put("M", "1110000");
        CMP_CODE_MAP.put("!D", "0001101");
        CMP_CODE_MAP.put("!A", "0110001");
        CMP_CODE_MAP.put("!M", "1110001");
        CMP_CODE_MAP.put("-D", "0001111");
        CMP_CODE_MAP.put("-A", "0110011");
        CMP_CODE_MAP.put("-M", "1110011");
        CMP_CODE_MAP.put("D+1", "0011111");
        CMP_CODE_MAP.put("A+1", "0110111");
        CMP_CODE_MAP.put("M+1", "1110111");
        CMP_CODE_MAP.put("D-1", "0001110");
        CMP_CODE_MAP.put("A-1", "0110010");
        CMP_CODE_MAP.put("M-1", "1110010");
        CMP_CODE_MAP.put("D+A", "0000010");
        CMP_CODE_MAP.put("D+M", "1000010");
        CMP_CODE_MAP.put("D-A", "0010011");
        CMP_CODE_MAP.put("D-M", "1010011");
        CMP_CODE_MAP.put("A-D", "0000111");
        CMP_CODE_MAP.put("M-D", "1000111");
        CMP_CODE_MAP.put("D&A", "0000000");
        CMP_CODE_MAP.put("D&M", "1000000");
        CMP_CODE_MAP.put("D|A", "0010101");
        CMP_CODE_MAP.put("D|M", "1010101");

        DEST_CODE_MAP.put("M", "001");
        DEST_CODE_MAP.put("D", "010");
        DEST_CODE_MAP.put("DM", "011");
        DEST_CODE_MAP.put("MD", "011");
        DEST_CODE_MAP.put("A", "100");
        DEST_CODE_MAP.put("AM", "101");
        DEST_CODE_MAP.put("AD", "110");
        DEST_CODE_MAP.put("ADM", "111");
        DEST_CODE_MAP.put("", "000");

        JUMP_CODE_MAP.put("", "000");
        JUMP_CODE_MAP.put("JGT", "001");
        JUMP_CODE_MAP.put("JEQ", "010");
        JUMP_CODE_MAP.put("JGE", "011");
        JUMP_CODE_MAP.put("JLT", "100");
        JUMP_CODE_MAP.put("JNE", "101");
        JUMP_CODE_MAP.put("JLE", "110");
        JUMP_CODE_MAP.put("JMP", "111");
    }

    private String dest = "";

    private String jmp = "";

    private String comp = "";

    public CCode(String instruction) {
        int eqlIndex = instruction.indexOf("=");
        if (eqlIndex != -1) {
            dest = instruction.substring(0, eqlIndex);
        }
        int semiIndex = instruction.indexOf(";");
        if (semiIndex != -1) {
            this.jmp = instruction.substring(semiIndex + 1);
            this.comp = instruction.substring(eqlIndex + 1, semiIndex);
        } else {
            this.comp = instruction.substring(eqlIndex+1);
        }
    }


    @Override
    public String translate() {
        StringBuilder stringBuilder = new StringBuilder("111");
        stringBuilder.append(CMP_CODE_MAP.get(this.comp));
        stringBuilder.append(DEST_CODE_MAP.get(this.dest));
        stringBuilder.append(JUMP_CODE_MAP.get(this.jmp));
        return stringBuilder.toString();
    }


}
