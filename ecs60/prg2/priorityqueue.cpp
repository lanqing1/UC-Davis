#include <iostream>
#include "priorityqueue.h"
using namespace std;

priorityqueue::priorityqueue(int max_size) {
  this->max_size=max_size;
  arr = new int[max_size+1];
}

void priorityqueue::insert(int k){

  if(curSize>=max_size){
    cout<<"priorityQueue::insert called on full priority queue"<<endl;
  }else if(curSize<max_size+1){
    curSize++;
    arr[curSize]=k;   
    heapifyUp(curSize);

  }
  if(maxHeapSize<curSize){
    maxHeapSize=curSize;
  }

  return;
}


int priorityqueue::removeMax(){
  int max;
  if(curSize==0){//when the array is null
    cout<<"priorityQueue::removeMax called on an empty priority queue"<<endl;
    return 0;
  }else if(curSize==1){
    max=arr[1];
    arr[1]=0;
    curSize=0;
  }else{
    max=arr[1];
    arr[1]=arr[curSize];
    arr[curSize]=0;
    curSize--;   
    heapifyDown(1,curSize);
    
  }

  return max;
}

int priorityqueue:: IsExist(int k){
  int index=-1;
  int i=1;
  while(i<=curSize){
    if(arr[i]==k){
      index=i;
    }
    i++;
  }
  return index;
}


void priorityqueue::removeKey(int k){
  int i=IsExist(k);
  if(i ==-1){
    cout<<"priorityQueue::removeKey key "<<k<<" not found"<<endl;
  }else{
    arr[i]=arr[curSize];
    arr[curSize]=0;
    curSize--;
    if(arr[i]<arr[2*i] || (arr[i]<arr[2*i+1] && (2*i+1<=curSize))  ){
       heapifyDown(i,curSize);
    }else{
        heapifyUp(i); 
     }
    
  }
  return;
}


void priorityqueue::change(int k, int newK) {
  int i=IsExist(k);

  if(i ==-1){
    cout<<"priorityQueue::change key "<<k<<" not found"<<endl;
  }else {
    arr[i] = newK;
    if(i==1){
    	heapifyDown(1,curSize);	
    }else if(arr[i]>arr[i/2]){  
      heapifyUp(i);
    }else{
      heapifyDown(i,curSize);
    }
  }
  return;
}

void priorityqueue::heapifyUp(int index) {
  
  for (int i = index; i >= 1; i/=2) {
    if ((arr[i]>arr[i/2])&& i>=2 ) {
      swap(arr[i], arr[i/2]);
    }
    
  }
  return;
}


void priorityqueue::heapifyDown(int i, int curSize){

  while(i<=curSize/2){ 
    if( (arr[i]<arr[2*i]) || ( (arr[i]<arr[2*i+1])&& (2*i+1<=curSize)) ){
      
      if(2*i+1> curSize){
      	swap(arr[i],arr[2*i]);
      	i=2*i;
      }else if(arr[2*i]>arr[2*i+1]){
  	 swap(arr[i],arr[2*i]);
  	 i=2*i;
      }else if(arr[2*i]<arr[2*i+1]){
  	 swap(arr[i],arr[2*i+1]);
  	 i=2*i+1;     
     }
  }
    else{
      break;
 }      
}    

return;
}

/**
void priorityqueue::disPlay(){
  for(int i=1;i<=curSize;i++){
    cout<<arr[i]<<" ";
  }
  cout<<endl;
  
return;
}

**/

int priorityqueue::getHeapMaxSize(){
  return maxHeapSize;
}




