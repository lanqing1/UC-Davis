/*
unsigned int max(unsigned int a, unsigned int b){
  //computes the max of a and b
  return a > b ? a : b;
}
unsigned int knapsack(int* weights, unsigned int* values, unsigned int num_items,int capacity, unsigned int cur_value){
  unsigned int i;
  unsigned int best_value = cur_value; 
  for(i = 0; i < num_items; i++){
    if(capacity - weights[i] >= 0 ){
      best_value = max(best_value, knapsack(weights + i + 1, values + i + 1, num_items - i - 1,capacity - weights[i], cur_value + values[i]));
    }
  }
  return best_value;
}
*/

.global knapsack
.equ ws, 4

knapsack:
	prologue:
		push %ebp
        	movl %esp, %ebp
		subl $2*ws, %esp #make space
		push %ebx #save regs

		#cur_value
		#capacity
		#num_items
		#values
		#weights
		#red addr
		#ebp->old ebp
		# i
		# best_value
		# ebx

		.equ weights, 2*ws
		.equ values, 3*ws
		.equ num_items, 4*ws
		.equ capacity, 5*ws
		.equ cur_value, 6*ws
		.equ i,-1*ws
		.equ best_value, -2*ws

		#eax = i
		#ecx = best_value
		#edx = temp
		#ebx = temp
		movl $0, %eax
		movl cur_value(%ebp),%ecx 
		movl %ecx,best_value(%ebp) #best_value=cur_value

		start_for:
		#for(i = 0; i < num_items; i++)
		#neg:i-num_items>=0
		cmpl num_items(%ebp),%eax
		jae end_for

			start_if:
			#if(capacity - weights[i] >= 0 )
			#neg: capacity - weights[i]<0
			movl weights(%ebp),%ebx #ebx=weights
			movl (%ebx,%eax,ws),%ebx #ebx=weights[i]
			cmpl %ebx ,capacity(%ebp)

			jl end_if #??? gb???

			movl values(%ebp), %edx #edx=values
			movl (%edx,%eax,ws),%edx #edx=values[i]
			addl cur_value(%ebp) ,%edx #edx=cur_value + values[i]
			push %edx

			movl capacity(%ebp),%edx #edx=capacity
			subl %ebx,%edx#edx=capacity-weights[i]
			push %edx
			
			movl num_items(%ebp),%edx #edx=num_items
			subl %eax,%edx #edx=num_items-i
			subl $1,%edx #edx=num_items-i-1
			#leal -4(%edx,%eax,-1*ws), %edx #edx=num_items-i-1 #eax=i=unsigned
			push %edx

			movl values(%ebp),%edx#edx=values
			leal 4(%edx,%eax,ws),%edx #edx=values+i+1
			push %edx

			movl weights(%ebp),%edx #edx=weights
			leal 4(%edx,%eax,ws),%edx #edx=weights+i+1
			push %edx

			movl %eax, i(%ebp) #save i
			call knapsack
			addl $5*ws, %esp #clear args
			
			push %eax #eax has the return best_val
			push best_value(%ebp) #best_value
			call max
			addl $2*ws, %esp #clear args
			
			movl %eax, best_value(%ebp)#%best_value=eax
			movl i(%ebp),%eax# restore i
			end_if:

		incl %eax
		jmp start_for
		end_for:
		movl best_value(%ebp), %eax #eax will have the return values

	epilogue:
		pop %ebx
		movl %ebp,%esp
		pop %ebp
		ret



#unsigned int max(unsigned int a, unsigned int b)
#  return a > b ? a : b;
max:
	prolgue_max:
		push %ebp
		movl %esp, %ebp
		#b
		#a
		#ret address
		#ebp->old ebp
		.equ a, 2*ws
		.equ b, 3*ws

		_if:
			movl a(%ebp),%eax #eax=a
			cmpl b(%ebp),%eax #a-b
			#neg:a-b<=0
			jbe _else
			jmp epilogue_max
		_else:
			movl b(%ebp),%eax #eax=b

	epilogue_max:
		movl %ebp,%esp
		pop %ebp
		ret

