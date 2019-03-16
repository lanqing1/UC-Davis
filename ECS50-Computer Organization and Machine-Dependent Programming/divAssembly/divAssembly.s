.global _start

.data
dividend:
	.long 64 
divisor:
	.long 4 

.text
#while( remainder>=divisor){
#    long a=(remainder>>(shiftNum))/divisor;
#    a=a<<(shiftNum);
#    quotient+=a;
#    remainder-=a*divisor;
#    shiftNum--;
#  }

_start: 
#shiftNum=31=CL
movb $31,%cl
movl $0,%ebx #let ebx = temp quotient
movl dividend,%esi #let esi be the temp remainder

while_loop:
#while remainder-divisor>=0 :edx-divisor
#neg: remainder-divisor<0
cmpl divisor,%esi
#jump to while_end when less than 0
jb while_end

#long a=(remainder>>(cl))/divisor;
#let a=%ebx
movl %esi, %eax#eax=remainder
#eax=eax>>cl
shrl %cl,%eax # eax=remainder>>(cl)

divl divisor #eax=edx:eax/divisor
			 #edx=edx:eax%divisor

#quotient+=a<<(shiftNum);
shll %cl,%eax #eax=eax<<(shiftNum)
addl %eax, %ebx #ebx+=eax ebx is the quotient
#remainder-=a*divisor;
mull divisor #eax=eax*divisor
subl %eax,%esi #esi-=eax esi is remainder
#shiftNum--
decb %cl 

#jump to while_loop if cl>=0
jmp while_loop

while_end:
movl %ebx,%eax #eax=ebx=quotient
movl %esi,%edx #edx=esi=remainder

done:
	nop

