.global _start

.data
num1:
	.long 1 #upper 32 bits
	.long 2 #lower 32 bits
num2:
	.long 3 #upper 32 bits
	.long 4 #lower 32 bits

.text
_start:

#edx will hold the upper 32 bits of sum
#eax will hold the lower 32 bits of sum

#move upper 32 bits of num1 to edx
movl num1,%edx #edx=num1[0]

#add lower sum to eax
movl num1+4,%eax #eax=num1[1]
addl num2+4,%eax #eax=eax+num2[1]

#no carry flag jump to noCarry
#else add 1 to upper sum
jnc noCarry
addl $1,%edx


noCarry:
addl num2,%edx #edx=edx+num2[0]

done:
	nop

