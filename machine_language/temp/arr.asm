//arr数组,在底层的的实现将地址存放在一个变量中,
//然后依次遍历arr指向的地址后面的n各地址.

//将 100开始的地址赋值给 arr.
@100
D=A
@arr
M=D

//将10设置给n
@10
D=A
@n
M=D

//将i设置为0
@i
M=1


(Loop)

@n
D=M
@i
D=D-M
@End
D;JLE

@arr
D=M
@i
A=D+M
M=-1

@i
D=M
M=D+1

@Loop
0;JMP


(End)
@End
0;JMP
