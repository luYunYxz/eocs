//给定一个数RAM[0] 50 求 1+2+...+ RAM[0]

//sum = RAM[1]
@R1
M=0

//i = RAM[2] = 0
@R2
M=0

(LOOP)
@R0
D=M

@R2
D=D-M

@END
D;JLE

@R1
D=M
@R2
D=D+M

@R1
M=D

@R2
D=M+1
M=D

@LOOP
0;JMP


(END)
@END
0;JMP
