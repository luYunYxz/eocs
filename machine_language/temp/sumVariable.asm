//给定一个数RAM[0] 50 求 1+2+...+ RAM[0]

@sum
M=0

@i
M=0

(LOOP)
@R0
D=M

@i
D=D-M

@STOP
D;JLE

@sum
D=M
@i
D=D+M

@sum
M=D

@i
D=M

D=D+1
@i
M=D

@LOOP
0;JMP

(STOP)
@sum
D=M
@R1
M=D

(END)
@END
0;JMP