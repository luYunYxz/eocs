package hack.aseembler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author muzhe
 * @data 2022/12/8 7:23 下午
 */
public class SymbolTable {

    private static int START_VARIABLE = 16;

    private static final Map<String, Integer> Symbol_Table = new HashMap<String, Integer>();

    /**
     * 是否存在符号
     *
     * @param symbol
     * @return
     */
    public static boolean existSymbol(String symbol) {
        return Symbol_Table.containsKey(symbol);
    }

    /**
     * 根据 符号名称获取到对应的地址
     *
     * @param symbol
     * @return
     */
    public static Integer getAddr(String symbol) {
        if (existSymbol(symbol)) {
            return Symbol_Table.get(symbol);
        } else {
            int addr = START_VARIABLE;
            START_VARIABLE++;
            Symbol_Table.put(symbol, addr);
            return addr;
        }
    }

    /**
     * 添加符号和地址映射
     *
     * @param symbol
     * @param addr
     * @return
     */
    public static void putSymbol(String symbol, Integer addr) {
        Symbol_Table.put(symbol, addr);
    }
}
