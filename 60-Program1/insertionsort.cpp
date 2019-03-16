// Insertion Sort
// 
// Author: Rob Gysel
// ECS60, UC Davis
// Adapted from: Lysecky & Vahid "Data Structures Essentials", zyBooks

#include "insertionsort.h"
#include<iostream>
using namespace std;


void InsertionSort(std::vector<int>* numbers, int* numOfComp, int* numOfMem) {
   unsigned int i = 0;
   int j = 0;
   int temp = 0;  // Temporary variable for swap

   for ( i = 1; i < numbers->size(); ++i) {
      j = i;
       
      while (j > 0 && (*numbers)[j] < (*numbers)[j - 1]) {
          
         temp = (*numbers)[j];
         (*numbers)[j] = (*numbers)[j - 1];
         (*numbers)[j - 1] = temp;
         --j;
         
         (*numOfComp)++;
         (*numOfMem)+=6;
         
          //cout<<"i="<<i<<", j= "<<j<<"comp= ";
      }
       (*numOfComp)++;
		(*numOfMem)+=2;
		
		
	  //cout<<"i="<<i<<", j= "<<j<<"comp= ";       
      // cout<<*numOfComp<<"   "<<*numOfMem<<endl;
       
   }
    
    //cout<<"total="<<(*numOfComp)<<"   "<<*numOfMem<<endl;
    //cout<<"numsize="<<numbers->size()<<endl;
    
   
   return;
}


