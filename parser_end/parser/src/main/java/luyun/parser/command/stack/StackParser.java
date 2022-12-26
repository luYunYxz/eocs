package luyun.parser.command.stack;

import luyun.parser.command.common.FileNameContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author muzhe
 * @data 2022/12/22 10:18 上午
 */
public class StackParser {

    /**
     * 将一个常量写到堆栈中
     *
     * @param value
     * @return
     */
    public static List<String> pushConstant(String value) {
        List<String> hackCodes = new ArrayList<>();
        List<String> setConstantCodes = setConstant2D(value);
        hackCodes.addAll(setConstantCodes);
        List<String> codes = pushD2Stack();
        hackCodes.addAll(codes);
        return hackCodes;
    }

    /**
     * 将第rank个 direct 的结果写到了 结果中
     * //这种是 temp和stack对应的空间.
     *
     * @param rank
     * @return
     */
    public static List<String> pushDirect(StackTypeEnum stack, String rank) {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.addAll(setDirectToD(stack, rank));
        hackCodes.addAll(pushD2Stack());
        return hackCodes;
    }

    /**
     * 将某个直接寻址的地址空间的值设置到D寄存器中
     *
     * @param stack
     * @param rank
     * @return
     */
    private static Collection<String> setDirectToD(StackTypeEnum stack, String rank) {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.add("@" + stack.getStackName());
        hackCodes.add("D=A");
        hackCodes.add("@" + rank);
        hackCodes.add("A=D+A");
        hackCodes.add("D=M");
        return hackCodes;
    }

    /**
     * 将stackType类型中的第rank个元素推到stack中
     *
     * @param stackType
     * @param rank
     * @return
     */
    public static List<String> pushInDirect(StackTypeEnum stackType, String rank) {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.addAll(setInDirect2D(stackType, rank));
        hackCodes.addAll(pushD2Stack());
        return hackCodes;
    }

    /**
     * 使用直接寻址的实现.将栈顶的元素写到对应的位置上去.
     *
     * @param stackType
     * @param rank
     * @return
     */
    public static List<String> popDirect(StackTypeEnum stackType, String rank) {

        List<String> hackCodes = new ArrayList<>();
        hackCodes.addAll(calDirAdd2Temp0(stackType, rank));
        hackCodes.addAll(popValue2D());
        hackCodes.addAll(setD2Temp0Addr());
        hackCodes.addAll(setTemp0());
        return hackCodes;
    }

    /**
     * 将D寄存在中的只写到temp0指定的地址中
     *
     * @return
     */
    private static Collection<String> setD2Temp0Addr() {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.add("@" + StackTypeEnum.TEMP.getStackName());
        hackCodes.add("A=M");
        hackCodes.add("M=D");
        return hackCodes;
    }

    /**
     * 计算直接寻址并加地址暂存在temp0地址空间中
     *
     * @param stackType
     * @param rank
     * @return
     */
    private static Collection<String> calDirAdd2Temp0(StackTypeEnum stackType, String rank) {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.add("@" + stackType.getStackName());
        hackCodes.add("D=A");
        hackCodes.add("@" + rank);
        hackCodes.add("D=D+A");
        hackCodes.add("@" + StackTypeEnum.TEMP.getStackName());
        hackCodes.add("M=D");
        return hackCodes;
    }

    /**
     * 将栈顶元素出栈并写到D寄存器中
     *
     * @return
     */
    public static Collection<String> popValue2D() {
        List<String> hackCodes = new ArrayList<>();
        //这里先将D-1,并记录到M中
        hackCodes.add("@" + StackTypeEnum.SP.getStackName());
        hackCodes.add("M=M-1");
        hackCodes.add("A=M");
        hackCodes.add("D=M");
        return hackCodes;
    }

    /**
     * 使用寻址的方式将当前堆顶pop出去
     *
     * @param stackType
     * @param rank
     * @return
     */
    public static List<String> popInDirect(StackTypeEnum stackType, String rank) {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.addAll(calInDirect2Temp0(stackType, rank));
        hackCodes.addAll(popValue2D());
        hackCodes.addAll(setD2Temp0Addr());
        hackCodes.addAll(setTemp0());
        return hackCodes;
    }

    private static Collection<String> setTemp0() {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.add("@" + StackTypeEnum.TEMP.getStackName());
        hackCodes.add("M=0");
        return hackCodes;
    }

    /**
     * 计算间接寻址的地址,并暂存在temp0中.
     *
     * @param stack
     * @param rank
     * @return
     */
    private static List<String> calInDirect2Temp0(StackTypeEnum stack, String rank) {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.add("@" + stack.getStackName());
        hackCodes.add("D=M");
        hackCodes.add("@" + rank);
        hackCodes.add("D=D+A");
        hackCodes.add("@" + StackTypeEnum.TEMP.getStackName());
        hackCodes.add("M=D");
        return hackCodes;
    }

    /**
     * 将常数设置到D寄存器
     *
     * @param value
     * @return
     */
    private static List<String> setConstant2D(String value) {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.add("@" + value);
        hackCodes.add("D=A");
        return hackCodes;
    }

    /**
     * 将D寄存器中的只push到stack中
     *
     * @return
     */
    private static List<String> pushD2Stack() {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.add("@SP");
        hackCodes.add("A=M");
        hackCodes.add("M=D");
        hackCodes.add("@SP");
        hackCodes.add("M=M+1");
        return hackCodes;
    }

    /**
     * 将间接寻址位置上的数据移动到D寄存器中
     *
     * @param stack
     * @param rank
     * @return
     */
    private static Collection<String> setInDirect2D(StackTypeEnum stack, String rank) {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.add("@" + stack.getStackName());
        hackCodes.add("D=M");
        hackCodes.add("@" + rank);
        hackCodes.add("A=D+A");
        hackCodes.add("D=M");
        return hackCodes;
    }

    /**
     * 如果value是0是@This目标地址
     * 如果value不是0地址是@That目标地址
     *
     * @param stackType
     * @param value
     * @return
     */
    public static List<String> popPointer(StackTypeEnum stackType, String value) {
        List<String> hackCodes = new ArrayList<>();
        String pointAddr = "@THAT";
        if ("0".equals(value)) {
            pointAddr = "@THIS";
        }
        hackCodes.addAll(popValue2D());
        hackCodes.add(pointAddr);
        hackCodes.add("M=D");
        return hackCodes;
    }

    /**
     * 将pointer指向的 地址中的数据,写到 stackType中去
     *
     * @param stackType
     * @param value
     * @return
     */
    public static List<String> pushPointer(StackTypeEnum stackType, String value) {
        List<String> hackCodes = new ArrayList<>();
        String pointAddr = "@THAT";
        if ("0".equals(value)) {
            pointAddr = "@THIS";
        }
        hackCodes.add(pointAddr);
        hackCodes.add("D=M");
        hackCodes.addAll(pushD2Stack());
        return hackCodes;
    }

    /**
     * 和 pushstack类似.首先将stack的数据写到D中.
     * 然后讲D --> stack.rank
     * @param stackType
     * @param rank
     * @return
     */
    public static List<String> popStatic(StackTypeEnum stackType, String rank) {
        List<String> hackCodes = new ArrayList<>();
        hackCodes.addAll(popValue2D());
        hackCodes.add("@"+FileNameContext.get() + "." + rank);
        hackCodes.add("M=D");
        return hackCodes;
    }

    /**
     * 将 statck中第rank个元素添加到堆栈中.
     * //首先将fileName.rank的指向的地址,移动到D中. D --> stack
     * @param stackType
     * @param rank
     * @return
     */
    public static List<String> pushStatic(StackTypeEnum stackType, String rank) {
        List<String> hackcodes = new ArrayList<>();
        hackcodes.add("@" + FileNameContext.get()+"." + rank);
        hackcodes.add("D=M");
        hackcodes.addAll(pushD2Stack());
        return hackcodes;
    }
}
