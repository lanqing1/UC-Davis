#include <stdio.h>
#include <stdlib.h>

int** matMult(int **a, int num_rows_a, int num_cols_a, int** b, int num_rows_b, int num_cols_b);
void displayMat(int** mat, int num_rows, int num_cols);
void readMat(const char* file_name, int*** mat, int* num_rows, int* num_cols);


int** matMult(int **a, int num_rows_a, int num_cols_a, int** b, int num_rows_b, int num_cols_b) {
	//num_cols_a=num_rows_b
	int** mat_c;
	mat_c = (int**)malloc(*num_rows_a * sizeof(int*));//???
	for (i = 0; i < *num_rows_a; i++) {
		mat_c[i] = (int*)malloc(*num_rows_a * sizeof(int));
	}

	for (int i = 0; i<num_rows_a; i++) {
		for (int j = 0; j<num_cols_a; j++) {
			int result = 0;
			for (int k = 0; k<num_rows_b; k++) {
				result += a[i][k] * b[k][j];
			}
			mat_c[i][j] = result;

		}
	}

	return mat_c;
}




void displayMat(int** mat, int num_rows, int num_cols){

	int i,j;
	
	for(i = 0; i < num_rows; i++){//for each row
		for(j = 0; j < num_cols; j++){ //for each column
			printf("%d ", mat[i][j]); //display the value
		}
		printf("\n");
	}

}//displayMat

void readMat(const char* file_name, int*** mat, int* num_rows, int* num_cols){
	//read in a file that contains a matrix
	//format of file is
		//number of rows
		//number of columns
		//values separated by white space

	int i,j;
	FILE *fptr;
	fptr = fopen(file_name, "r"); //open the file
	
	//read in the dimensions
	fscanf(fptr, "%d", num_rows); 
	fscanf(fptr, "%d", num_cols);
	
	//make space for mat
	*mat = (int**)malloc(*num_rows * sizeof(int*));
	
	for(i = 0; i < *num_rows; i++){
		(*mat)[i] = (int*)malloc(*num_cols * sizeof(int));
	}
	
	//initialize mat
	for(i = 0; i < *num_rows; i++){
		for(j = 0; j < *num_cols; j++){
			fscanf(fptr, "%d", &((*mat)[i][j]));
		}//for each row
	}//for each column
	
	fclose(fptr);// close the file
}//readMat

int main(int argc, char **argv){
	int** mat_a;
	int** mat_b;
	int** mat_c;
	int rows_mat_a, cols_mat_a, rows_mat_b, cols_mat_b;
	int i;
	
	if(argc < 3){
		printf("matmult.out matrix_A_File matrix_B_File\n");
		printf("Not enough arguments given.n");
		return(1);
	}
	
	//read in matrices
	readMat(argv[1], &mat_a, &rows_mat_a, &cols_mat_a);
	readMat(argv[2], &mat_b, &rows_mat_b, &cols_mat_b);
	
	//do the multiplication
	mat_c = matMult(mat_a, rows_mat_a, cols_mat_a, mat_b, rows_mat_b, cols_mat_b);
	
	//display solution
	displayMat(mat_c, rows_mat_a, cols_mat_b);
	
	//free up malloced space
	for(i = 0; i < rows_mat_a; i++){
		free(mat_a[i]);
	}
	
	for(i = 0; i < rows_mat_b; i++){
		free(mat_b[i]);
	}
	
	for(i = 0; i < rows_mat_a; i++){
		free(mat_c[i]);
	}

	free(mat_a);
	free(mat_b);
	free(mat_c);
	
	return 0;
}//main


