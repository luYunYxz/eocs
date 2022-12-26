package luyun.parser.parser;

import luyun.common.CommandType;
import luyun.parser.command.arithmetic.ArithmeticParser;
import luyun.parser.command.compare.CompareParser;
import luyun.parser.command.logic.LogicParser;
import luyun.parser.parser.CommandParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author muzhe
 * @data 2022/12/22 8:36 下午
 */
public class HackCommandParser  {

    private static Map<CommandType, CommandParser> TYPE_PARSER_MAP = new HashMap<>();

    static {
        TYPE_PARSER_MAP.put(CommandType.ADD, () -> {return ArithmeticParser.add();});
        TYPE_PARSER_MAP.put(CommandType.SUB,()->{return ArithmeticParser.sub();});
        TYPE_PARSER_MAP.put(CommandType.NEG,()->{return ArithmeticParser.neg();});
        TYPE_PARSER_MAP.put(CommandType.EQ,()->{return CompareParser.eq();});
        TYPE_PARSER_MAP.put(CommandType.LT,()->{return CompareParser.lt();});
        TYPE_PARSER_MAP.put(CommandType.GT,()->{return CompareParser.gt();});

        TYPE_PARSER_MAP.put(CommandType.AND,()->{return LogicParser.and();});
        TYPE_PARSER_MAP.put(CommandType.OR,()->{return LogicParser.or();});
        TYPE_PARSER_MAP.put(CommandType.NOT,()->{return LogicParser.not();});

    }

    /**
     * 是否当前命解析器进行解析
     * @param commandType
     * @return
     */
    public static boolean isMatch(CommandType commandType) {
        return TYPE_PARSER_MAP.containsKey(commandType);
    }

    /**
     * 对命令进行解析
     * @param commandType
     * @return
     */
    public static List<String> parse(CommandType commandType) {
        return TYPE_PARSER_MAP.get(commandType).parse();
    }
}
