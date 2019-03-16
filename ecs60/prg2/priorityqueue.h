#include<iostream>

using namespace std;

class priorityqueue{
 public:
  priorityqueue(int max_size) ;

  void insert(int k);
  int removeMax();

  void removeKey(int k);
  void change(int k,int newK);
  int IsExist(int k);

  int getHeapMaxSize();// delete
  void heapifyDown(int index, int curSize);
  void heapifyUp(int index);

  //void disPlay();

 
public:
  int k;
  int newK;
  int max_size;
  int* arr ;
  int curSize=0;
  int maxHeapSize=0;
};
