package hack.aseembler;

/**
 * 这个是一个parser,主要将将每一条指令解析为基础的对象.
 *
 * @author muzhe
 * @data 2022/12/8 7:22 下午
 */
public interface Parser {

    /**
     * 将一条指令解析为基础元素
     * 空白内容
     * A指令
     * C指令   cmp + dest + jmp
     *
     * @return
     */
    Code parse();


}
