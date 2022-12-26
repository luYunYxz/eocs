package luyun.parser.command.stack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import luyun.common.AddressingTypeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author muzhe
 * @data 2022/12/17 2:40 下午
 */
@AllArgsConstructor
@Getter
public enum StackTypeEnum {

    /**
     * constat类型的数据,也是存放在sp指定的stack中
     */
    CONSTANT("SP", AddressingTypeEnum.INDIRECT),

    POINTER("", AddressingTypeEnum.DIRECT),

    LOCAL("LCL", AddressingTypeEnum.INDIRECT),

    THAT("THAT", AddressingTypeEnum.INDIRECT),

    THIS("THIS", AddressingTypeEnum.INDIRECT),

    STATIC("16", AddressingTypeEnum.DIRECT),

    ARGUMENT("ARG", AddressingTypeEnum.INDIRECT),

    //temp是一个特殊的地址,系统中没有为其设置别名.从逻辑地址开始
    TEMP("5", AddressingTypeEnum.DIRECT),

    /**
     * 通用stack的地址
     */
    SP("SP", AddressingTypeEnum.INDIRECT);

    private String stackName;

    /**
     * 寻址方式
     */
    private AddressingTypeEnum addressingType;


    private static Map<String, StackTypeEnum> STACK_MAP = new HashMap<>();

    static {
        STACK_MAP.put("constant", CONSTANT);
        STACK_MAP.put("local", LOCAL);
        STACK_MAP.put("argument", ARGUMENT);
        STACK_MAP.put("this", THIS);
        STACK_MAP.put("that", THAT);
        STACK_MAP.put("temp", TEMP);
        STACK_MAP.put("static", STATIC);
        STACK_MAP.put("pointer",POINTER);
    }

    public static StackTypeEnum getStackType(String stackType) {
        return STACK_MAP.get(stackType);
    }

}
