//屏幕的特点是 256 * 512 一共有256行,512列
//将 左边的50行,16列点亮
//伪代码

// 因为这里字长是16位.访问的时候也只能访问16位.
//所以这题就是把每一行的第一个字都设置为-1
//每一行有32个字
//所以 换行的时候就只需+32
// addr=screen
//i=0
// LOOP
// if (i >= 50 ) goto ENd 
// RAM(address) = -1
// i++
// address += 32
//godo loop
// end
// goto end


//设置 n的行数
@0
D=M
@n
M=D

//设置i的值
@i
M=0

//设置address
@SCREEN
D=A
@addr
M=D

(LOOP)
@i
D=M
@n
D=D-M
@END
D;JGE

@addr
A=M  //addr中存放了 屏幕的地址.所以这个时候需要讲 RAM[addr]设置为-1
M=-1

@i
M=M+1

@addr
D=M
@32
D=D+A
@addr
M=D
@LOOP
0;JMP




(END)
@END
0;JMP

