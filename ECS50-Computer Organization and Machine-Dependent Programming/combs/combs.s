/*
int** get_combs(int *items, int k, int len){
    int n = num_combs(len, k);
    int** combs = (int**) malloc( n * sizeof(int*));
	int* data = (int*) malloc( k * sizeof(int*));
	int i;
	int current = 0;

    for(i = 0; i < n; i++){
        combs[i] = (int*)malloc( k * sizeof(int));
    }
    cal_comb(items, len, k, 0, data, 0, &current, combs);
    return combs;
}

void cal_comb(int* items, int len, int k, int index, int* data, int i, int *current, int **combs){
    if (index == k){
        int j;
        for (j = 0; j < k; j++){
            int tempi = *current / k;
            int tempj = *current%k;
            combs[tempi][tempj]=(int)data[j];
            *current++;
        }
        return;
    }
    if (i >= len){
        return;
    }
    data[index] = items[i];
    cal_comb(items, len, k, index+1, data, i+1, current, combs);
    cal_comb(items, len, k, index, data, i+1, current, combs);
}
*/

.global get_combs
.equ ws, 4

get_combs:
	prologue:
		push %ebp
		movl %esp, %ebp
		subl $5*ws, %esp #make space
		 #save regs
		push %esi

		#len
		#k
		#items-----
		#ret addr
		#ebp->old ebp
		#n
		#combs
		#data
		#i
		#current----
		#esi
		.equ items, 2*ws
		.equ k, 3*ws
		.equ len, 4*ws
		.equ n,-1*ws
		.equ combs, -2*ws
		.equ data,-3*ws
		.equ i, -4*ws
		.equ current, -5*ws

#ecx = combs
#edx = data
#esi = i
#eax = temp
		push k(%ebp)
		push len(%ebp)
		call num_combs #int n = num_combs(len, k);		
		addl $2*ws, %esp # clear args
		movl %eax,n(%ebp) #save n to memory

		#   int** combs = (int**) malloc( n * sizeof(int*));
		shll $2,%eax #eax=n*sizeof(int*)
		push %eax
		call malloc
		addl $1*ws,%esp #eax = (int**) malloc(n * sizeof(int*));
		movl %eax, combs(%ebp)

		#	int* data = (int*) malloc( k * sizeof(int*));
		movl k(%ebp),%edx #edx=k
		shll $2,%edx #edx= k*sizeof(int*)
		push %edx
		call malloc #edx = (int*) malloc(k * sizeof(int*));
		addl $1*ws,%esp 
		movl %eax, data(%ebp)
		movl $0,current(%ebp)#current=0
		movl $0,%esi #i=0
		start_for_loop:
			#for(i = 0; i < n; i++)
			#neg: i-n>=0
			
			cmpl n(%ebp),%esi
			jge end_for_loop
			movl k(%ebp),%ecx #ecx=k
			shll $2,%ecx
			push %ecx
			movl %esi , i(%ebp)#save i
			call malloc
			addl $1*ws,%esp #eax = (int*)malloc( k * sizeof(int));
			#movl %eax,%ecx #ecx= (int*)malloc( k * sizeof(int));
			movl i(%ebp),%esi
			movl combs(%ebp),%ecx #ecx=combs
		
			movl %eax, (%ecx, %esi, ws) #combs[i]=eax

			incl %esi#i++
			jmp start_for_loop
		end_for_loop:
		#cal_comb(items, len, k, 0, data, 0, &current, combs);
		
		push combs(%ebp)
		leal current(%ebp),%eax
		push %eax #push current's adress
		push $0
		push data(%ebp)
		push $0
		push k(%ebp)
		push len(%ebp)
		push items(%ebp)
		call cal_comb
		addl $8*ws ,%esp#clear args

	epilogue:
		movl combs(%ebp),%eax
		pop %esi
		movl %ebp,%esp
		pop %ebp
		ret

cal_comb:
	prologue_cal:
		push %ebp
		movl %esp,%ebp
		subl $3*ws,%esp
		push %esi
		push %edi
		push %ebx

	
		#combs
		#current
		#i
		#data
		#index
		#k
		#len
		#items-----
		#ret addr
		#ebp->old ebp
		#j
		#tempi
		#tempj-----
		#esi 
		#edi
		#ebx
		
		.equ items, 2*ws
		.equ len, 3*ws
		.equ k, 4*ws
		.equ index,5*ws
		.equ data, 6*ws
		.equ i,7*ws
		.equ current, 8*ws
		.equ combs, 9*ws
		.equ j, -1*ws
		.equ tempi, -2*ws
		.equ tempj, -3*ws

		#ecx = j	
		#esi = tempi
		#edi =tempj		
		#eax = temp
		#edx = combs
		#ebx = data

		start_if_cal:
		#index == k ;neg: index-k!=0
		movl k(%ebp),%ecx
		cmpl index(%ebp),%ecx
		jnz end_if_cal
			movl $0,%ecx #ecx=j=0
			start_for_cal:
				#j < k ; neg: j-k>=0
				cmpl k(%ebp) ,%ecx
				jge end_for_cal
				movl current(%ebp),%eax # this is address of current
				movl (%eax),%eax #%eax is *current
				movl $0,%edx

				divl k(%ebp)
				movl %eax,%esi #esi=tempi
				movl %edx,%edi #edi =tempj
				movl data(%ebp),%ebx #ebx=data
				movl (%ebx,%ecx,ws),%ebx #ebx=data[j];

				movl combs(%ebp),%edx #edx=combs
				movl (%edx,%esi,ws),%edx #edx=combs[tempi]
				movl %ebx,(%edx,%edi,ws)#combs[tempi][tempj]=data[j];

				movl current(%ebp),%eax #eax has the address
				incl (%eax) # *current++
				incl %ecx
				jmp start_for_cal
			end_for_cal:

			jmp epilogue_cal
		end_if_cal:

		start2_if_cal:
			movl i(%ebp),%ecx #ecx=i

			#(i >= len) ;neg: i-len<0

			cmpl  len(%ebp),%ecx
			jl end2_if_cal
			jmp epilogue_cal
			
		end2_if_cal:
		movl i(%ebp),%esi #esi = i
		movl items(%ebp),%edi 
		movl (%edi,%esi,ws),%edi #edi = items[i]

		movl index(%ebp),%eax  #eax=index
		movl data(%ebp),%ebx #ebx=data
		movl %edi, (%ebx,%eax,ws) #data[index]=items[i]

		#cal_comb(items, len, k, index+1, data, i+1, current, combs);
		
		push combs(%ebp)
		push current(%ebp) #current(%ebp) is &current
		incl %esi
		push %esi #push i+1
		push data(%ebp)
		incl %eax
		push %eax
		push k(%ebp)
		push len(%ebp)
		push items(%ebp)
		call cal_comb
		addl $8*ws,%esp#clear args

		#cal_comb(items, len, k, index, data, i+1, current, combs);
		push combs(%ebp)
		push current(%ebp)
		movl i(%ebp),%esi
		incl %esi
		push %esi #push i+1
		push data(%ebp)
		push index(%ebp)
		push k(%ebp)
		push len(%ebp)
		push items(%ebp)
		call cal_comb
		addl $8*ws,%esp#clear args
		
	epilogue_cal:
	movl combs(%ebp),%eax
	pop %ebx
	pop %edi
	pop %esi
	movl %ebp,%esp
	pop %ebp
	ret
	

