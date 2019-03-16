#include<iostream>
#include<string>
#include "json.hpp"
#include<fstream>
#include<vector>
using namespace std;

int main(int argc, char** argv) {
  ifstream file;
  file.open(argv[1]);

  nlohmann::json jsonObject;
  nlohmann::json jsonOutput;

  unsigned int arraySize;
  unsigned int numInversion = 0;

  if (file.is_open()) {

    file >> jsonObject;

    arraySize = jsonObject["metadata"]["arraySize"];


    for (auto itr = jsonObject.begin(); itr != jsonObject.end(); ++itr) {

      if (itr.key() != "metadata") {

        unsigned int counter = 0;


        for (auto arrayItr=jsonObject[itr.key()].begin();(arrayItr + 1)!=jsonObject[itr.key()].end();++arrayItr){

			

          if (*arrayItr > *(arrayItr + 1)) {

       

            string a = to_string(counter);

            

            jsonOutput[itr.key()]["ConsecutiveInversions"][a].push_back(*arrayItr);

            jsonOutput[itr.key()]["ConsecutiveInversions"][a].push_back(*(arrayItr+1));

            jsonOutput[itr.key()]["sample"] = jsonObject[itr.key()];

            		

          }

          counter++;

        }

      }

    }

    for (auto itr = jsonOutput.begin(); itr != jsonOutput.end(); ++itr) {

		if (itr.key() != "metadata") {

			numInversion++;

	}		

	}

    jsonOutput["metadata"]["arraySize"] = arraySize;

    jsonOutput["metadata"]["file"] = argv[1];

    jsonOutput["metadata"]["numSamples"] = jsonObject["metadata"]["numSamples"];

    jsonOutput["metadata"]["samplesWithInversions"] = numInversion;

    cout << jsonOutput << endl;

    file.close();

  }

}
