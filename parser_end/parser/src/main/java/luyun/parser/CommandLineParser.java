package luyun.parser;

import com.google.common.base.Splitter;
import luyun.common.CommandType;
import luyun.parser.command.stack.StackTypeEnum;
import luyun.parser.parser.HackCommandParser;
import luyun.parser.parser.HackPopPushParser;

import java.util.Collections;
import java.util.List;

/**
 * 对于文件的parser
 *
 * @author muzhe
 * @data 2022/12/16 5:37 下午
 */
public class CommandLineParser {

    /**
     * 对当前命令行进行翻译
     *
     * @return
     */
    public static List<String> getCommand(String commandLine) {

        List<String> commandLines = Splitter.on(" ").splitToList(commandLine.trim());
        if (commandLines == null || commandLines.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        String commandTypeStr = commandLines.get(0);
        CommandType commandType = CommandType.getCommandType(commandTypeStr);
        if (HackPopPushParser.isMatch(commandType)) {
          return HackPopPushParser.parse(commandType, StackTypeEnum.getStackType(commandLines.get(1)), commandLines.get(2));
        } else {
            return HackCommandParser.parse(commandType);
        }
    }

}
