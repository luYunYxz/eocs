package hack.aseembler.codes;

import com.sun.rowset.JdbcRowSetResourceBundle;
import hack.aseembler.Code;
import hack.aseembler.SymbolTable;

import java.util.regex.Pattern;

/**
 * @author muzhe
 * @data 2022/12/9 9:30 上午
 */
public class ACode implements Code {

    /**
     * 指令的内容.
     * 可能为一个数值.也可能是一个 变量
     */
    private String value;

    public ACode(String instruction) {
        this.value = instruction.substring(1);
    }

    /**
     * 解析A指令
     *
     * @return
     */
    @Override
    public String translate() {
        boolean isNumber = Pattern.matches("[0-9]*", this.value);
        Integer addr = 0;
        if (isNumber) {
            //这里将一个变量变为一个二进制.
            addr = Integer.parseInt(this.value);
        } else {
            //这里是一个变量.需要添加到变量表
            addr = SymbolTable.getAddr(this.value);
        }
        String binaryString = Integer.toBinaryString(addr);
        if (binaryString.length() == 16) {
            return binaryString;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            int zeroLen = 16 - binaryString.length();
            for (int i = 0; i < zeroLen; i++) {
                stringBuilder.append("0");
            }
            stringBuilder.append(binaryString);
            return stringBuilder.toString();
        }
    }
}
