/*
int** matMult(int **a, int num_rows_a, int num_cols_a, int** b, int num_rows_b, int num_cols_b) {
	//num_cols_a=num_rows_b

	int i,j,k;
	#local variables: i j k mat_c
	int** mat_c = (int**) malloc(num_rows_a * sizeof(int*));

    for(int i=0;i<num_rows_a;i++) {
	    C[i] = (int*)malloc( num_cols_b * sizeof(int));
        for(int j=0; j<num_cols_b;j++){
           int result=0;
           for(int k=0; k<num_rows_b;k++){
               result+=a[i][k]*b[k][j];
           }
           mat_c[i][j]=result;
        }
    }
	return mat_c;
}
*/

.global matMult
.equ ws, 4

matMult:
	prologue:
	push %ebp
	movl %esp, %ebp	
	subl $4*ws,%esp#make space for locals	
	push %ebx #save regs
	push %edi 
	push %esi
	#stack:
	#  num_cols_b
	#  num_rows_b
	#     b
	#  num_cols_a
	#  num_rows_a
	#     a
	#  ret address
	#ebp:old ebp
	#	  i
	#	  j
	#	  k
	#    mat_c
	#    ebx
	#    edi
	#esp:esi

	.equ a,    (2*ws) #(%ebp)
	.equ rowA, (3*ws) #(%ebp)
	.equ colA, (4*ws) #(%ebp)
	.equ b,    (5*ws) #(%ebp)
	.equ rowB, (6*ws) #(%ebp)
	.equ colB, (7*ws) #(%ebp)
	.equ i,    (-1*ws) #%ebp
	.equ j,    (-2*ws) #(%ebp)
	.equ k,    (-3*ws) #(%ebp)
	.equ matC, (-4*ws) #(%ebp)

	#int** mat_c = (int**) malloc(num_rows_a * sizeof(int*));
	movl rowA(%ebp), %eax #eax = num_rows_a
	shll $2, %eax  #eax = num_rows * sizeof(int*)) 
	push %eax #place malloc's arguement onto the stack
	call malloc
	addl $1*ws, %esp #clear malloc's arguement 
	#eax = (int**) malloc(num_rows * sizeof(int*));
	movl %eax, matC(%ebp) #mat_c = (int**) malloc(num_rows * sizeof(int*));
	
	#eax = i
	#ebx = j
	#ecx = k
	#edx = C[i] /temp
	#edi = temp
	#esi =temp

	movl $0, %eax #i=0
	start_outer_loop:
	#for ( i = 0; i<num_rows_a; i++)
	#neg: i-num_rows_a>=0
		cmpl rowA(%ebp),%eax
		jge end_outer_loop

		#mat_c[i] = (int*)malloc(num_cols_b * sizeof(int));
		movl colB(%ebp),%edi
		shll $2, %edi # edi = num_cols_b * sizeof(int)
		push %edi #set arguement for malloc
		movl %eax, i(%ebp) #save i 
		call malloc
		addl $1*ws, %esp #clear arguement for malloc

		#eax = (int*)malloc(num_cols_b * sizeof(int));
		movl %eax, %edx  #edx= (int*)malloc( num_cols_b * sizeof(int));
		movl i(%ebp), %eax  #restore i to eax
		movl matC(%ebp), %ecx #ecx = matC
		movl %edx,(%ecx, %eax, ws) #C[i] = edx 
		#movl %edx, C(%ebp, %eax, ws) == (&C)[i] = %edx

		movl $0,%ebx #ebx =j=0
		start_mid_loop:
		#for ( j = 0; j<num_cols_b; j++) {
		#neg: j-num_cols_a>=0
			cmpl colB(%ebp),%ebx
			jge end_mid_loop
			movl $0, %ecx #k=ecx=0
			movl $0, %edi

			start_inner_loop:
				cmpl rowB(%ebp),%ecx
				jge end_inner_loop
				#result += a[i][k] * b[k][j];
				movl a(%ebp), %esi #esi=a
				movl (%esi,%eax,ws),%esi #esi=a[i]
				movl (%esi,%ecx,ws),%esi #esi=a[i][k]

				push %eax #store i	
				push %edx
				movl $0, %edx #???
				movl %esi,%eax #eax=a[i][k] 
				
				movl b(%ebp), %esi #esi=b
				movl (%esi,%ecx,ws),%esi #esi=b[k]
				movl (%esi,%ebx,ws),%esi #esi=b[k][j]

				mull %esi #edx:eax=eax*esi = b[k][j]*a[i][k]
				addl %eax, %edi #edi+=edx #edi will be result???

				#restore edx and i
				pop %edx 
				pop %eax 

				incl %ecx#k++
				jmp start_inner_loop
			end_inner_loop:
			#c[i][j]=result
			movl %edi, (%edx,%ebx,ws) #mat_c[i][j]=edi

			incl %ebx #j++
			jmp start_mid_loop
		end_mid_loop:

		incl %eax #i++
		jmp start_outer_loop
	end_outer_loop:
	movl matC(%ebp),%eax

	epilogue:
		addl $4*ws,%esp#make space for locals	
		pop %esi
		pop %edi
		pop %ebx
		movl %ebp, %esp
		pop %ebp
		ret
