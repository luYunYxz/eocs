package luyun.parser.command.stack;

import java.util.List;

/**
 * 命令解析.将 vm 命令解析为 hack命令
 *
 * @author muzhe
 * @data 2022/12/16 5:48 下午
 */
public interface StackCommandParser {

    /**
     * 将vm命令转变为 hack指令
     *
     * @param command
     * @return
     */
    public List<String> parser(StackCommand command);

}
