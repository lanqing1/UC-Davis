#include<iostream>
#include<string>
#include "json.hpp"
#include<fstream>
#include<vector>
#include "priorityqueue.h"
using namespace std;

int main(int argc, char** argv) {
  ifstream file;
  file.open(argv[1]);
  nlohmann::json jsonObject,jsonOutput;
  int arraySize=0;
  int curSize=0;

  if (file.is_open()) {
    file >> jsonObject;
    arraySize = jsonObject["metadata"]["maxHeapSize"];
    priorityqueue q(arraySize);
    for (auto itr = jsonObject.begin(); itr != jsonObject.end(); ++itr) {
      if (itr.key() != "metadata") {
        if(jsonObject[itr.key()]["operation"]=="insert"){
          q.insert(jsonObject[itr.key()]["key"]);
          
        }
        else if(jsonObject[itr.key()]["operation"]=="removeMax"){
          q.removeMax();					
        }
        else if(jsonObject[itr.key()]["operation"]=="change"){
          q.change(jsonObject[itr.key()]["key"],jsonObject[itr.key()]["newKey"]);
	}          
        else if(jsonObject[itr.key()]["operation"]=="removeKey"){				
          q.removeKey(jsonObject[itr.key()]["key"]);          
        }
      }
    }
    
    curSize=q.curSize;
    //int maxHeapSize=q.getHeapMaxSize();max_size??

    for(int i=1;i<=curSize;i++){
      string a = to_string(i);
      jsonOutput[a]["key"]=q.arr[i];
      if(i/2>0){
        jsonOutput[a]["parent"]=to_string(i/2);
      }
      if(2*i<=curSize){
        jsonOutput[a]["leftChild"]=to_string(2*i);
      }
      if(2*i+1<=curSize) {
        jsonOutput[a]["rightChild"] = to_string(2 * i + 1);
      }
    }
    jsonOutput["metadata"]["maxHeapSize"] = arraySize;
    jsonOutput["metadata"]["max_size"] = arraySize;
    jsonOutput["metadata"]["numOperations"] = jsonObject["metadata"]["numOperations"];
    jsonOutput["metadata"]["size"] = curSize;
    cout << jsonOutput.dump(2) << endl;
    file.close();
  }

    

  return 0;
}

