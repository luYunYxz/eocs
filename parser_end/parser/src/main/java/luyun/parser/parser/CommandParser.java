package luyun.parser.parser;


import java.util.List;

/**
 * 处理除push pop以外的所有指令
 *
 * @author muzhe
 * @data 2022/12/22 8:33 下午
 */
public interface CommandParser {

    /**
     * 解析命令
     *
     * @return
     */
    public List<String> parse();
}
