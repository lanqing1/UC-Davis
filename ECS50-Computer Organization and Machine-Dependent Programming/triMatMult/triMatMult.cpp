#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
int main(int argc, char* argv[]) {
	ifstream file_1(argv[1]);
	ifstream file_2(argv[2]);
	int fileSize;
	int result;

	if (file_1.is_open()&file_2.is_open()) {
		file_1 >> fileSize;
		//initialize to all 0
		int list_1[fileSize][fileSize] = { 0 };
		int list_2[fileSize][fileSize] = { 0 };

		//put file data into array
		for (int i = 0; i<fileSize; i++) {
			for (int j = 0; j<fileSize; j++) {
				if (j >= i) {
					file_1 >> list_1[i][j];
					file_2 >> list_2[i][j];
				}
				cout << list_1[i][j] << "  ";
			}
			cout << endl;
		}

		for (int i = 0; i<fileSize; i++) {
			for (int j = 0; j<fileSize; j++) {
				for (int k = 0; k<fileSize; k++) {
					result += list_1[i][k] * list_2[k][j];
				}
				if (result != 0) {
					cout << list_1[i][j] << " ";
				}
				result = 0;
			}
		}
	}
	file_1.close();
	file_2.close();
	return 0;
}