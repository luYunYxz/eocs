package luyun.parser.parser;

import luyun.common.CommandType;
import luyun.parser.command.stack.StackParser;
import luyun.parser.command.stack.StackTypeEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author muzhe
 * @data 2022/12/22 8:55 下午
 */
public class HackPopPushParser {


    public static Map<StackTypeEnum, PopPushParser> POP_TYPE_PARSER_MAP = new HashMap<>();
    public static Map<StackTypeEnum, PopPushParser> PUSH_TYPE_PARSER_MAP = new HashMap<>();
    public static Map<CommandType, Map<StackTypeEnum, PopPushParser>> COMMAND_PARSER_MAP = new HashMap<>();


    static {
        POP_TYPE_PARSER_MAP.put(StackTypeEnum.ARGUMENT, ((stackType, value) -> {return StackParser.popInDirect(stackType,value);}));
        POP_TYPE_PARSER_MAP.put(StackTypeEnum.LOCAL,((stackType, value) -> {return StackParser.popInDirect(stackType,value);}));
        POP_TYPE_PARSER_MAP.put(StackTypeEnum.THIS,((stackType, value) -> {return StackParser.popInDirect(stackType,value);}));
        POP_TYPE_PARSER_MAP.put(StackTypeEnum.THAT,((stackType, value) -> {return StackParser.popInDirect(stackType,value);}));
        POP_TYPE_PARSER_MAP.put(StackTypeEnum.TEMP,((stackType, value) -> {return StackParser.popDirect(stackType,value);}));
        POP_TYPE_PARSER_MAP.put(StackTypeEnum.STATIC,((stackType, value) -> {return StackParser.popStatic(stackType,value);}));
        POP_TYPE_PARSER_MAP.put(StackTypeEnum.POINTER,((stackType, value) -> {return StackParser.popPointer(stackType,value);}));

        PUSH_TYPE_PARSER_MAP.put(StackTypeEnum.CONSTANT,((stackType, value) -> {return StackParser.pushConstant(value);}));
        PUSH_TYPE_PARSER_MAP.put(StackTypeEnum.LOCAL,(((stackType, value) -> {return StackParser.pushInDirect(stackType,value);})));
        PUSH_TYPE_PARSER_MAP.put(StackTypeEnum.ARGUMENT,(((stackType, value) -> {return StackParser.pushInDirect(stackType,value);})));
        PUSH_TYPE_PARSER_MAP.put(StackTypeEnum.THIS,(((stackType, value) -> {return StackParser.pushInDirect(stackType,value);})));
        PUSH_TYPE_PARSER_MAP.put(StackTypeEnum.THAT,(((stackType, value) -> {return StackParser.pushInDirect(stackType,value);})));
        PUSH_TYPE_PARSER_MAP.put(StackTypeEnum.TEMP,(((stackType, value) -> {return StackParser.pushDirect(stackType,value);})));
        PUSH_TYPE_PARSER_MAP.put(StackTypeEnum.STATIC,(((stackType, value) -> {return StackParser.pushStatic(stackType,value);})));
        PUSH_TYPE_PARSER_MAP.put(StackTypeEnum.POINTER,(((stackType, value) -> {return StackParser.pushPointer(stackType,value);})));

        COMMAND_PARSER_MAP.put(CommandType.POP,POP_TYPE_PARSER_MAP);
        COMMAND_PARSER_MAP.put(CommandType.PUSH,PUSH_TYPE_PARSER_MAP);
    }

    /**
     * 当前解析器是否支持当前命令
     * @param commandType
     * @return
     */
    public static boolean isMatch(CommandType commandType){
        return COMMAND_PARSER_MAP.containsKey(commandType);
    }

    /**
     * 解析当前命令
     * @param commandType
     * @param stackType
     * @param value
     * @return
     */
    public static List<String> parse(CommandType commandType, StackTypeEnum stackType, String value) {
        if (!COMMAND_PARSER_MAP.containsKey(commandType)){
            throw new IllegalArgumentException("no support");
        }
        PopPushParser stackParser= COMMAND_PARSER_MAP.get(commandType).get(stackType);
        if (stackParser == null){
            throw new IllegalArgumentException("no support");
        }
        return stackParser.parse(stackType,value);
    }
}
