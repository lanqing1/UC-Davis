.global _start

.data
	string1:
		.space 100 
	string2:
		.space 100 
	string1_len:
		.long 0
	string2_len:
		.long 0
	oldDist:
		.space 404
	curDist:
		.space 404

.text

editDist:
#int word1_len = strlen(word1);
	movl $string1, %eax
	call strlen # edx(i) will have the string1_len
	addl $1,%edx
	movl %edx,string1_len #string1_len=str1_len+1

#int word2_len = strlen(word2);
	movl $string2, %eax
	call strlen
	addl $1, %edx
	movl %edx,string2_len #string2_len=str2_len+1

	movl $0,%esi #esi=i=0
	movl $oldDist,%edx#edx= $oldDist
	movl $curDist,%edi#edi = $curDist
	
 #for(i = 0; i < word2_len + 1; i++)
  #neg: i-edx>=0
	start_ini_dis_for:
		cmpl string2_len, %esi 
		jge end_ini_dis_for
		#oldDist[i] = i; (edx+esi)=esi
		#curDist[i] = i; (edi+esi)=esi
		movl %esi,(%edx,%esi,4)
		movl %esi,(%edi,%esi,4)
		incl %esi #i++
		jmp start_ini_dis_for
	end_ini_dis_for:

	#esi=i=1
	#ebx=j=1
	#string2_len
	#string1_len
	#edi=$curDist
	#edx=$oldDist
	movl $1,%esi
	start_outer_for:
		#(i = 1; i < word1_len + 1; i++)
		#neg:i-string1_len>=0
		cmpl string1_len, %esi
		jge end_outer_for
		#curDist[0] = i;(edi+0)=esi
		movl %esi,(%edi)

		movl $1,%ebx
		start_inner_for:
		#for(j = 1; j < word2_len + 1; j++); neg: j-string2_len>=0; 
			cmpl string2_len, %ebx
			jge end_inner_for
				start_if:
					movl $string1,%eax
					movl $string2,%ecx
					#(str1[i-1] == str2[j-1])
					movb -1(%eax,%esi,1),%al #??? use a as temp
					cmpb %al,-1(%ecx,%ebx,1) #string,ws=1 byte
					jnz else_if
					# curDist[j] = oldDist[j - 1]; (curDist+j)= (oldDist+j-1)
					movl -4(%edx,%ebx,4),%eax #??? use edi as temp
					movl %eax,(%edi,%ebx,4)
					jmp end_if_else

				else_if:
					#curDist[j] = min(min(oldDist[j], curDist[j-1]),oldDist[j-1]) + 1;
					#min(oldDist[j], curDist[j-1])
					movl (%edx,%ebx,4) ,%eax
					movl -4(%edi,%ebx,4),%ecx
					call min #result: min would be in %eax
					#min(eax,oldDist[j-1]) 
					movl -4(%edx,%ebx,4) ,%ecx
					call min #result: min would be in %eax
					#curDist[j]=a ->(curDist+j)=a
					#a= min(min(oldDist[j], curDist[j-1]),oldDist[j-1])+1
					incl %eax
					movl %eax,(%edi,%ebx,4) 

				end_if_else:

			incl %ebx#j++
			jmp start_inner_for
		end_inner_for:

		#swap(&oldDist, &curDist);
		call swap #swap(%edx,%edi) 

		incl %esi #i++
		jmp start_outer_for
	end_outer_for:
	#dist = oldDist[str2_len];
	movl string2_len,%ecx
	decl %ecx #string2_len=str2_len+1; ecx=string2_len-1
	movl (%edx,%ecx,4),%eax#eax=(oldDist+esp)
	ret


min:
#eax will have min
	min_if:
	#if a<b, return a;neg: a-b>=0
		cmpl %ecx, %eax
		jge min_else
		#return a(a=a)
		ret
	min_else:
		movl %ecx, %eax #return b(a=b)
		ret

#swap(%edx,%edi) 
swap:
	movl %edx,%eax 
	movl %edi, %edx
	movl %eax, %edi
	ret

strlen:
	movl $0,%edx #i=edx
	start_strlen_for:
		cmpb $0,(%eax,%edx) #string1[i]!='\0'
		jz end_strlen_for
		incl %edx #++i
		jmp start_strlen_for
	end_strlen_for:
		ret#return %edx

_start:
	call editDist
	#movl dist to eax

done:
	nop

