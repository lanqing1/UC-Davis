#include<iostream>
#include "mergesort.h"
#include "insertionsort.h"
#include "quicksort.h"
#include "json.hpp"
#include<fstream>
#include<ctime>
#include<vector>

using namespace std;

int main(int argc, char** argv) {
	
	ifstream file;
	file.open(argv[1]);
	nlohmann::json jsonObject;
	unsigned int arraySize;
	clock_t start,end,timeTaken;
	
  	
	if (file.is_open()) {

		file >> jsonObject;

		arraySize = jsonObject["metadata"]["arraySize"];
		cout<<"Sample,InsertionSortTime,InsertionSortCompares,InsertionSortMemaccess,MergeSortTime,MergeSortCompares,MergeSortMemaccess,QuickSortTime,QuickSortCompares,QuickSortMemaccess"<<endl;

		for (auto itr=jsonObject.begin();itr!=jsonObject.end();++itr){
			
			if (itr.key()!="metadata"){
				vector<int> list_1,list_2,list_3;
				for(unsigned int i=0; i<arraySize;i++){
		
					int a=jsonObject[itr.key()][i];
					list_1.push_back(a);
					list_2.push_back(a);
					list_3.push_back(a);
				 }
				
				
				int numOfComp_1=0,numOfMem_1=0;
				start=clock();
				InsertionSort(&list_1,&numOfComp_1,&numOfMem_1);
				end=clock();
				timeTaken=end-start;
				cout<<itr.key()<<",";
				cout<<(timeTaken/(double) CLOCKS_PER_SEC)<<",";
				cout<<numOfComp_1<<",";
				cout<<numOfMem_1<<",";
				
				
				  
				int numOfComp_2=0,numOfMem_2=0;
				start=clock();
				MergeSort(&list_2,&numOfComp_2,&numOfMem_2);
				end=clock();			
				timeTaken=end-start;
				cout<<(timeTaken/(double) CLOCKS_PER_SEC)<<",";
				cout<<numOfComp_2<<",";
				cout<<numOfMem_2<<",";
				
				   
					
				int numOfComp_3=0,numOfMem_3=0;
				start=clock();
				QuickSort(&list_3,&numOfComp_3,&numOfMem_3);
				end=clock();			
				timeTaken=end-start;
				cout<<(timeTaken/(double) CLOCKS_PER_SEC)<<",";
				cout<<numOfComp_3<<",";
				cout<<numOfMem_3<<endl;
				
                
		  }
		}
	}
	file.close();
}
