package luyun.parser.command.common;

/**
 * @author muzhe
 * @data 2022/12/26 11:33 上午
 */
public class FileNameContext {
    private final static ThreadLocal<String> PARSER_FILE_NAME = new ThreadLocal<>();

    public static void setFileName(String fileName) {
        PARSER_FILE_NAME.set(fileName);
    }

    public static void clear() {
        PARSER_FILE_NAME.remove();
    }

    public static String get() {
        return PARSER_FILE_NAME.get();
    }
}
