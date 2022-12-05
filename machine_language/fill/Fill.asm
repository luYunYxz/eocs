// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

//keyboard的特点是按下的时候用,@KBD值非0,弹起的时候为0
//
// LOOP
//  获取keyboard kbd
// if(kbd == 0)JMP BLACk 
//  print WHITE  0

//BLACK:
// print BLACK  -1

//将屏幕全部染色.
// 屏幕是: 256 * 32*16
// @count = 256 * 32 + Screen
// @addr = SCREEN
//LOOP
//  count - addr
// <= 0 jGE loop
//RAM[addr] = 0 / -1

(LOOP)

@KBD
D=M
@WHITE
D;JEQ
//打印黑色

@SCREEN
D=A
@bAddr
M=D
@8192
D=D+A
@bCount
M=D

(PRINTBLACK)
@bCount
D=M
@bAddr
D=D-M
@LOOP
D;JLE

@bAddr
A=M
M=-1

@bAddr
M=M+1
@PRINTBLACK
0;JMP




(WHITE)

@SCREEN
D=A
@addr
M=D
@8192
D=D+A
@count
M=D

(PRINTWHITE)
@count
D=M
@addr
D=D-M

@LOOP
D;JLE

@addr
A=M // 设置地址
M=0 //设置白色

@addr
M=M+1
@PRINTWHITE
0;JMP




@LOOP
0;JMP

