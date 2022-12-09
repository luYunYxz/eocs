package hack.aseembler.parser;

import hack.aseembler.Code;
import hack.aseembler.Parser;
import hack.aseembler.codes.ACode;
import hack.aseembler.codes.CCode;

/**
 * 这里是一个编译器.其中包括了一条指令.同时将这条指令进行转换分解.
 *
 * @author muzhe
 * @data 2022/12/8 8:08 下午
 */
public class HackParser implements Parser {

    private String instruction;

    /**
     * @param instruction
     */
    public HackParser(String instruction) {
        this.instruction = instruction;
    }

    /**
     * @return
     */
    @Override
    public Code parse() {

        //当前有两种类型的命令
        // A,C两种
        if (this.instruction.startsWith("@")) {
            //这个指令是A指令
            ACode code = new ACode(this.instruction);
            return code;
        } else {
            CCode code = new CCode(this.instruction);
            return code;
        }
    }
}
