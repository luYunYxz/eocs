package luyun.parser.command.logic;

import jdk.nashorn.internal.runtime.regexp.joni.constants.internal.StackType;
import luyun.parser.command.stack.StackParser;
import luyun.parser.command.stack.StackTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author muzhe
 * @data 2022/12/22 2:58 下午
 */
public class LogicParser {

    /**
     * and运算
     *
     * @return
     */
    public static List<String> and() {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.addAll(StackParser.popValue2D());
        hackCodes.add("@" + StackTypeEnum.SP.getStackName());
        hackCodes.add("A=M-1");
        hackCodes.add("M=D&M");
        return hackCodes;
    }

    /**
     * or运算
     *
     * @return
     */
    public static List<String> or() {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.addAll(StackParser.popValue2D());
        hackCodes.add("@" + StackTypeEnum.SP.getStackName());
        hackCodes.add("A=M-1");
        hackCodes.add("M=D|M");
        return hackCodes;
    }

    /**
     * 计算not.
     *
     * @return
     */
    public static List<String> not() {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.add("@" + StackTypeEnum.SP.getStackName());
        hackCodes.add("A=M-1");
        hackCodes.add("M=!M");
        return hackCodes;
    }

}
