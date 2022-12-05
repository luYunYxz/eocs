# the elements of computer system

## 布尔逻辑

### 背景知识

#### 布尔代数

无论多么复杂的布尔函数都可以适用 `not` `or` `and` 这三种布尔算子来实现.

`not` `or` `and`这三种算子有可以通过 `Nand` 或 `Nor`来实现.
`Nand` 表示 not(x and y)
`Nor`表示  not (x or y)
x or y = (x Nand y) Nand(y Nand y)
这样只需要实现一种 NAnd的物理结构就可以帮助实现布尔代数的任何硬件实现.

#### 逻辑门

##### Nand
最基础的门电路,所有其他的门电路都是以Nad为基础实现.因为元器件实现是Nand的实现.其他的逻辑算子都是通过Nand来实现的.
```
Input: a,b
Output: b
x Nand b --> !(a&&b)
```

使用 真值表

(0,1) --> 1
(0,0) --> 1
(1,0) --> 1
(1,1) --> 0


##### Not
Input a
Output b

```
Not(a) =  Nand(a,a)
```

##### Or

a Or b = Nand( Not(a),Not(b))



##### Xor

Xor(a,b) == not(a==b)
(0,0) --> 0
(0,1) --> 1
(1,0) --> 1
(1,1) --> 0


a xor b = (a and not(b) ) or (not(a) and b )


##### Mux
if sel == 0  out= a 
   sel == 1  out = b

真值表:
a b sel 
(0,0,0) --> 0
(0,1,0) --> 0
(1,0,0) --> 1
(1,1,0) --> 1
(0,0,1) --> 0
(0,1,1) --> 1
(1,0,1) --> 0
(1,1,1) --> 1

总结出这个公式来:
sel + or表示二选一.
and :当其中一个值为1的时候,由另外一个值决定结果.

Mux(a,b,sel) = (not(seal) and a) or (seal and b)


##### DMux

真值表
(in,seal) --> (a,b)
(0,0) --> (0,0)
(0,1) --> (0,0)
(1,0) --> (1,0)
(1,1) --> (0,1)


a= (not(seal) and in)or (seal and 0 )  == > not(seal) and in
b= (not(seal) and 0) or (seal and in ) ==> seal and in 


##### DMux4Way

In in,sel[2]
Out a,b,c,d

(in,sel[0],sel[1]) --> (a,b,c,d)

a = And(Not(sel[0]),Not(sel[1])) And in
b= And(sel[0],Not(sel[1])) And in
c= And(Not(sel[0]),sel[1]) And in
d = And(sel[0],sel[1]) And in



##### DMux8Way
反向选择8路电路:in表示输入.sel[3]表示选择器.输出有a..h8路.根据sel[3]的信号决定从a-h中的一路输出.
这里对应 2进制计数法,如下.

In in, sel[3]
out a,b,c,d,e,f,g,h
a -- 000 
b -- 001 
c -- 010
d -- 011
e -- 100
f -- 101 
g -- 110
h -- 111

a= And(Not(sel[2]),Not(sel[1]),Not(sel[0]) And in   
b= And(Not(sel[2]),Not(sel[1]),sel[0])And in
c= And(Not(sel[2]),sel[1],Not(sel[0]))And in
d= And(Not(sel[2]),sel[1],sel[0])And in
e= And(sel[2],Not(sel[1]),Not(sel[0])) And in
f= And(sel[2],Not(sel[1]),sel[0]) And in
g= And(sel[2],sel[1],Not(sel[0])) And in
h= And(sel[2],sel[1],sel[0])And in

##### Mux4Way16
多路选择器.有四组 16路的引脚.a[16],b[16],c[16],d[16]
有一个两个引脚的选择电路 sel[2]

a == 00
b == 01
c == 10
d == 11

out = ((not(sel[0]) And not(sel[1])) & a[]) or 
((Not(sel[1]) And sel[0]) and b[])
or ((sel[1] And (Not(sel[0]))) And c[])
or((sel[1] And sel[0]) And d[])

##### Mux8Way16
八中16路选择器.有8*16中输入引脚 + 3路选择线路sel[3].
其中对应的
a == 000
b == 001
c == 010
d == 011
e == 100
f == 101
g == 110
h == 111

这个和 Mux4Way16之间的关系?


Mux16(Mux4way16(a,b,c,d,sel[1..2]),Mux4Way16(e,f,g,h,sel[1..2]),sel[3])




#### 布尔运算

##### 原码,反码,补码
todo

##### 半加器
两个bool值的加法
a + b  sum  carray
(0,0)  (0,0)
(0,1)  (1,0)
(1,0)  (1,0)
(1,1)  (0,1)

这里:
sum = Xor(a,b)
carray = And(a,b)


##### 全加器 
三个二进制的加法

(a,b,c) --> (sum,carray)
(0,0,0) --> (0,0)
(0,0,1) --> (1,0)
(0,1,0) --> (1,0)
(0,1,1) --> (0,1)
(1,0,0) --> (1,0)
(1,0,1) --> (0,1)
(1,1,0) --> (0,1)
(1,1,1) --> (1,1)

sum(sum(a+b),c) = sum
Xor(c,Xor(a,b)) = sum
sum(carray(a+b),carray(sum(a,b),c)) = carray


##### Add16
a[0] b[0]  使用半加器得到sum和carry
后面从1开始到15 使用  a[i],b[i],carry[i-1]的全加器,得到 sum[i],carry[i]

##### Incr 1

in[0] +1 的实现
(int0,1) --> sum ,carry
0,1           1    0
1,1           0    1
sum = not(in(0))
carry= in(0)

##### ALU

如果int[16]为空,或者全为0,就设置为false
//Mux必须有两个输入+一组sel.
在高级语言中,是先执行逻辑判断(sel)后执行对应的逻辑,只选择被命中的分支.
但是在电路中,是将所有的结果都指向出来.然后转给分支(Mux)进行处理.得到想要的结果.这里是思路上的差异.

##### 总结


#### 时序逻辑
##### Bit
  If load[t] == 1 then out[t+1] = in[t]
                  else out does not change (out[t+1] = out[t])
时序逻辑是用一个 Mux的选择器+ DFF数据触发器.其中涉及到FeedBack是允许的.就是后面出现的变量能够在前面提前出现.这里的变量在芯片上是一个连接.
Bit中DFF会有两个输出,一个输出是指向了 开始的位置,还有一个是指向最终的输出.

##### 寄存器
是16位Bit的封装.共用一个load.

##### PC
一个16位的带有reset,load的控制位.
因为带有16位,所以需要16位置的Bit来记录起状态. 这个不知道怎么做.
这里搞错顺序了.

##### RAM8 
接口参数:
IN in[16], load, address[3];
OUT out[16];
有一个16位的输入,和 load+三位的地址选择器.
输出是对应寄存器的结果.

其中需要8个16位的寄存器. int和这16位的寄存器连接.

      用 address3+load生成对应的寄存器的 8个load地址.分别连接.
第三步:将8个寄存器的out结果连接上一个Mux8Way16的选择器.用address帮助选出输出来.


##### RAM64
接口参数:
    IN in[16], load, address[6];
    OUT out[16];
    这个是存放了64个Register.那么可以抽象为 8个RAM8.低三位作为 RAM8内部的寻址空间.
    高八位用来定位8个RAM8中的一个.
   总线宽度是16位,这个和之前一样.
   //适用 DMUX8 + address[4..6]定位出其中的la
   //用in+la + address[0..2]进行执行.得到8个 out
   //用 Mux+address[4..6]+ out得到对应的out


##### RAM512
接口参数:
    IN in[16], load, address[9];
    OUT out[16];
    高三位作为MUX和DMUX的选择.低6位作为RAM64内部的定位Register的实现.

##### RAM4K
接口参数:
IN in[16], load, address[12];
OUT out[16];

使用8个RAM512来构造一个RAM4K

##### PC
pc中包括了一个16位字长.有三个引脚,
reset设置为1表示强制为0.
reset为0的时候:
   load == 1 的时候就使用新输入的值
   load == 0 的时候:
      inc == 1 的时候  out = out[t]+1
      int == 0 的时候  out = out[t]
因为是大量的逻辑判断.需要从最内部适用Mux来实现.
同时这里涉及到了 out,那么需要适用feedback的实现.就是前面可以指向后面的引脚.
第二点是前面做了很多的操作,最终需要讲结果写到Register中的时候,适用load = true表示将计算结果写到Register中去.
Register中 load= false表示是读.load=true表示写.
PC是每一次都在改变的.每次都是写.

第三章:引入了feedback的概念.是依赖DFF中的clocked实现.上一个时钟周期的输出何以作为当前时钟周期的输入.在写法上,就是Clocked的逻辑门在out的时候会有两个out,一个是指向输入的.另外一个是指向最终输出的.这两个指向就是两根引线.

#### 第四章 
机器语言.
程序是cpu和寄存器对内存的操作.
对计算机进行了抽象.抽象为Cpu,寄存器,内存.
##### 寄存器
寄存器有两个D寄存器,A寄存器.字长是16位.
内存的空间是2^16 * 16的空间.被平均分为两部分,2^15 *16的指令空间. 2^15*16的数据空间.
寄存器中D用来存放数据.
A寄存器是一个多功能寄存器.@value表示将value中的值加载到A寄存器中.
关键看后面是如何使用的:
例如后面接了一条 D=D+A指令,就是立即数.
D=D+M 这个时候就表示获取到M[value]d的数据.

##### 指令
指令的格式是 16位.第一位用来区分指令类型.
0开头的表示A指令(地址指令).后面的15位是地址空间.
1开头的表示C指令(执行指令).其中前三位不是用.后面的7位表示指令类型.后面的三位是:dest表示指令执行结果存放地址.xxx(A,M,D).最后三位表示jump类型,LEG:表示执行结果中第一位和0的关系.
jump的策略是跳转到ROM(A)的指令的地址.各种不同的JMP和之前结果值比较满足了条件,然后跳转到a寄存器中指向的ROM地址.

##### 一些指令的范例
- 寄存器访问
  - D=10
  > @10  //将10赋给D寄存器 
  > D=A   //这里是A,表示立即数.如果M表示获取RAM[10]
 - D++
 > @1
 > D=D+A  以上的实现 太复杂 D寄存器可以直接+1的.
 > D=D+1
 - D=RAM[17] 内存中17位置的数据加载到 D寄存器中来
  > @17
  > D=M
 - RAM[5] = RAM[3]
   > @3
   > D=M
   > @5
   > M=D
 - RAM[17]=10
   > @10
   > D=A
   > @17
   > M=D
总结,两个寄存器,D寄存器用来存放数据,不能直接进行赋值.如果需要复制,必须从A或者M寄存器中获取值.
D寄存器能直接+1,不能直接+其他自然数.但是能和AM寄存器相加.
D寄存器不能直接将数值赋给内存.需要赋给M寄存器.
-  分支程序
  分支指令执行顺序如下:
  > @value     //设置A寄存器地址空间
  > D:Jump指令  //根据D的MSB执行响应的跳转到 上一步中A寄存器中的指令地址中.

  防止PC指令递增
  因为PC指令是递增执行的,所以到执行完成以后,就会在地址空间停止.但是PC是不会停止的,就在末尾添加一个死循环
  > @End    //设置程序末尾地址
  > 0:JMP  // 适用JMP地址,无条件跳转

  变量替换
  很多时候在跳转的时候,使用行数来指定跳转指令地址,这样很不方便,因为代码在变动中,如果添加了几行代码以后,跳转的行数就需要修改,就很不方便写代码,也不方便阅读.
  所以引入了 变量.
  > (Varible) 使用括号确定变量
  > 后面适用@Varible 来适用变量.

 - 循环
   循环和 分支有点类似.主要是跳转的时候需要添加一个 LOOP.
   我们在写代码的时候,可以先写伪代码,然后翻译成机器代码.
- 变量
  便于代码更容易理解,可以自定义变量,这个变量会在内存中分配一个寄存器.
  > @sum 表示统计和,编译的时候会分配一个合适的寄存器.
  > @i  表示 for循环中使用到的i 
- 数组
  数组的寻址是间接寻址,首先将数组的对应的基址存储在arr寄存器中.每次访问的时候,首先读取出arr中的基址,然后和下标进行求和定位真实的地址.

  

  

##### 实现一
实现一个乘法.就是m*n是将 n个m相加.是一个for循环.

##### 根据输入把屏幕染黑
用一个循环监听KBD位,如果不为0,就将屏幕染黑.否则染白.
屏幕从RAM[SCREEN]开始的 256 * 32 个寄存器.
染白 寄存器 = 0
染黑 寄存器 = 1

Hack的机器语言比较简单.但是使用起来很不方便.如果想学习一门语言,需要学会 存取变量, 基础运算,分支,循环,数组这些运算的语法.