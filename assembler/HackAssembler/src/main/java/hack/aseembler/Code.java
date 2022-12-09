package hack.aseembler;

/**
 * @author muzhe
 * @data 2022/12/8 7:22 下午
 */
public interface Code {

    /**
     * 将指令转义为一条二进制的指令
     *
     * @return 二进制指令
     */
    String translate();

}
