package luyun.parser.command.stack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import luyun.common.CommandType;

/**
 * @author muzhe
 * @data 2022/12/17 2:36 下午
 */

@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackCommand {

    /**
     * 命令类型
     */
    private CommandType commandType;

    /**
     * 获取到堆栈类型
     */
    private StackTypeEnum stackType;

    /**
     * 获取命令value值
     */
    private int commandValue;


}
