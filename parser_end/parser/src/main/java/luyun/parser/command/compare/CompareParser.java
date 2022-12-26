package luyun.parser.command.compare;

import luyun.common.CommandType;
import luyun.parser.command.stack.StackParser;
import luyun.parser.command.stack.StackTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author muzhe
 * @data 2022/12/22 3:34 下午
 */
public class CompareParser {

    /**
     * 标签序列
     */
    private static int labelCount = 0;

    private static Map<CommandType, String> COMMAND_TYPE_JMP_MAP = new HashMap<>();

    static {
        COMMAND_TYPE_JMP_MAP.put(CommandType.LT, "D;JLT");
        COMMAND_TYPE_JMP_MAP.put(CommandType.GT, "D;JGT");
        COMMAND_TYPE_JMP_MAP.put(CommandType.EQ, "D;JEQ");
    }

    /**
     * 比较栈顶两个元素是否相等
     *
     * @return
     */
    public static List<String> eq() {
        return compare(CommandType.EQ);
    }

    /**
     * gt
     *
     * @return
     */
    public static List<String> gt() {
        return compare(CommandType.GT);
    }

    /**
     * 比较栈的元素,小于
     *
     * @return
     */
    public static List<String> lt() {
        return compare(CommandType.LT);
    }

    /**
     * 比较,比价的逻辑类似.其中主要是跳转的
     *
     * @param commandType
     * @return
     */
    private static List<String> compare(CommandType commandType) {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.addAll(StackParser.popValue2D());
        hackCodes.add("@" + StackTypeEnum.SP.getStackName());
        hackCodes.add("A=M-1");
        hackCodes.add("D=M-D");
        hackCodes.add("@TRUE_" + labelCount);
        hackCodes.add(COMMAND_TYPE_JMP_MAP.get(commandType));
        hackCodes.add("@" + StackTypeEnum.SP.getStackName());
        hackCodes.add("A=M-1");
        hackCodes.add("M=0");
        hackCodes.add("@END_" + labelCount);
        hackCodes.add("0;JMP");
        hackCodes.add("(TRUE_" + labelCount + ")");
        hackCodes.add("@" + StackTypeEnum.SP.getStackName());
        hackCodes.add("A=M-1");
        hackCodes.add("M=-1");
        hackCodes.add("(END_" + labelCount + ")");
        labelCount++;
        return hackCodes;
    }

}
