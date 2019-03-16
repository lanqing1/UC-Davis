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

  nlohmann::json jsonObject;
  nlohmann::json jsonOutput;
  int arraySize;

  if (file.is_open()) {
    file >> jsonObject;
    arraySize = jsonObject["metadata"]["arraySize"];

    for (auto itr = jsonObject.begin(); itr != jsonObject.end(); ++itr) {
      if (itr.key() != "metadata") {
        priorityqueue q(arraySize);
        for (auto arrayItr=jsonObject[itr.key()].begin();(arrayItr)!=jsonObject[itr.key()].end();++arrayItr){
          q.insert(*arrayItr);
        }

        for(int i=arraySize-1;i>=0;i--){
          jsonOutput[itr.key()][i] = q.removeMax();
        }
      }
    }
    jsonOutput["metadata"]["arraySize"] = arraySize;
    jsonOutput["metadata"]["numSamples"] = jsonObject["metadata"]["numSamples"];
    cout << jsonOutput.dump(2) << endl;
    file.close();
  }
  return 0;
}
