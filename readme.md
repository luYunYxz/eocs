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
b= And(Not(sel[0]),sel[1]) And in
c= And(sel[0],Not(sel[1])) And in
d = And(sel[0],sel[1]) And in


