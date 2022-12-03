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

