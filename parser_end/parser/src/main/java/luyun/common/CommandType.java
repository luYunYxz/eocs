package luyun.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author muzhe
 * @data 2022/12/16 5:45 下午
 */
public enum CommandType {


    /**
     * 加法
     */
    ADD,

    /**
     * 减法
     */
    SUB,

    /**
     * 取反
     */
    NEG,

    /**
     * 相等
     */
    EQ,

    /**
     * 大于
     */
    GT,

    /**
     * 小于
     */
    LT,

    /**
     * 逻辑与
     */
    AND,

    /**
     * 逻辑或
     */
    OR,

    /**
     * 逻辑非
     */
    NOT,

    /**
     * push指令
     */
    PUSH,

    /**
     * pop指令
     */
    POP,
    ;
    private static Map<String, CommandType> COMMAND_TYPE_MAP = new HashMap<>();

    static {
        COMMAND_TYPE_MAP.put("add", ADD);
        COMMAND_TYPE_MAP.put("sub", SUB);
        COMMAND_TYPE_MAP.put("neg", NEG);
        COMMAND_TYPE_MAP.put("eq", EQ);
        COMMAND_TYPE_MAP.put("gt", GT);
        COMMAND_TYPE_MAP.put("lt", LT);
        COMMAND_TYPE_MAP.put("and", AND);
        COMMAND_TYPE_MAP.put("or", OR);
        COMMAND_TYPE_MAP.put("not", NOT);
        COMMAND_TYPE_MAP.put("push", PUSH);
        COMMAND_TYPE_MAP.put("pop", POP);
    }

    public static CommandType getCommandType(String commandType) {
        return COMMAND_TYPE_MAP.get(commandType);
    }

}
