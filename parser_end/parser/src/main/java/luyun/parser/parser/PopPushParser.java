package luyun.parser.parser;

import luyun.parser.command.stack.StackTypeEnum;

import java.util.List;

/**
 * @author muzhe
 * @data 2022/12/22 8:54 下午
 */
public interface PopPushParser {

    /**
     * 对pop和push进行解析
     * @param stackType
     * @param value
     * @return
     */
    public List<String> parse(StackTypeEnum stackType, String value);
}
