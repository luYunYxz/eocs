package luyun.parser.command.arithmetic;

import luyun.parser.command.stack.StackParser;
import luyun.parser.command.stack.StackTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author muzhe
 * @data 2022/12/22 2:38 下午
 */
public class ArithmeticParser {

    /**
     * 解析add的
     *
     * @return
     */
    public static List<String> add() {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.addAll(StackParser.popValue2D());
        hackCodes.add("@" + StackTypeEnum.SP.getStackName());
        hackCodes.add("A=M-1");
        hackCodes.add("M=D+M");
        return hackCodes;
    }

    /**
     * 求差
     *
     * @return
     */
    public static List<String> sub() {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.addAll(StackParser.popValue2D());
        hackCodes.add("@" + StackTypeEnum.SP.getStackName());
        hackCodes.add("A=M-1");
        hackCodes.add("M=M-D");
        return hackCodes;
    }

    /**
     * 求反
     *
     * @return
     */
    public static List<String> neg() {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.add("@" + StackTypeEnum.SP.getStackName());
        hackCodes.add("A=M-1");
        hackCodes.add("M=-M");
        return hackCodes;
    }

}
