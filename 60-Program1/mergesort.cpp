// Merge Sort
// 
// Author: Rob Gysel
// ECS60, UC Davis
// Adapted from: Lysecky & Vahid "Data Structures Essentials", zyBooks

#include "mergesort.h"
#include<iostream>
using namespace std;


void MergeSort(std::vector<int>* numbers, int* numOfComp, int* numOfMem) {
   MergeSortRecurse(numbers, 0, numbers->size() - 1,numOfComp,numOfMem);
   (*numOfMem)-=2;
}


void MergeSortRecurse(std::vector<int>* numbers, int i, int k,int* numOfComp,int* numOfMem) {
   int j = 0;
   
   if (i < k) {
      j = (i + k) / 2;  // Find the midpoint in the partition
      
      // Recursively sort left and right partitions
      MergeSortRecurse(numbers, i, j,numOfComp,numOfMem);
      MergeSortRecurse(numbers, j + 1, k,numOfComp,numOfMem);
      
      // Merge left and right partition in sorted order
      Merge(numbers, i, j, k,numOfComp,numOfMem);
      (*numOfMem)+=3;
      
   }
   
}

void Merge(std::vector<int>* numbers, int i, int j, int k,int* numOfComp,int* numOfMem) {
   int mergedSize = k - i + 1;                // Size of merged partition
   int mergePos = 0;                          // Position to insert merged number
   int leftPos = 0;                           // Position of elements in left partition
   int rightPos = 0;                          // Position of elements in right partition
   std::vector<int> mergedNumbers;
   mergedNumbers.resize(mergedSize);          // Dynamically allocates temporary array
   (*numOfMem)++;
                                              // for merged numbers
   
   leftPos = i;                               // Initialize left partition position
   rightPos = j + 1;                          // Initialize right partition position
   
   // Add smallest element from left or right partition to merged numbers
   while (leftPos <= j && rightPos <= k) {
	   
      if ((*numbers)[leftPos] < (*numbers)[rightPos]) {
         mergedNumbers[mergePos] = (*numbers)[leftPos];
         ++leftPos;
         
         (*numOfMem)++;
         

      }

      else {
         mergedNumbers[mergePos] = (*numbers)[rightPos];
         ++rightPos;
         (*numOfMem)++;
         
      }
      ++mergePos;
      (*numOfComp)++;
      (*numOfMem)+=2;
   }
   // If left partition is not empty, add remaining elements to merged numbers
   while (leftPos <= j) {
      mergedNumbers[mergePos] = (*numbers)[leftPos];
      ++leftPos;
      ++mergePos;
      (*numOfMem)++;
   }
   
   // If right partition is not empty, add remaining elements to merged numbers
   while (rightPos <= k) {
      mergedNumbers[mergePos] = (*numbers)[rightPos];
      ++rightPos;
      ++mergePos;
      (*numOfMem)++;
   }
   
   // Copy merge number back to numbers
   for (mergePos = 0; mergePos < mergedSize; ++mergePos) {
      (*numbers)[i + mergePos] = mergedNumbers[mergePos];
      (*numOfMem)+=2;
   }
}

