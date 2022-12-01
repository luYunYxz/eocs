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







