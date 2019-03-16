#include<iostream>

#include<string>

#include "json.hpp"

#include<fstream>

#include<vector>

using namespace std;

int main(int argc, char** argv) {

    

    ifstream file_1, file_2;

    nlohmann::json jsonObject_1;

    nlohmann::json jsonObject_2;

    nlohmann::json jsonOutput;

    unsigned int arraySize;

    unsigned int numOfMis = 0;

    

    file_1.open(argv[1]);

    file_2.open(argv[2]);

    

    if (file_1.is_open() && file_2.is_open()) {

        file_1 >> jsonObject_1;

        file_2 >> jsonObject_2;

    }

    arraySize = jsonObject_1["metadata"]["arraySize"];

    for (auto itr = jsonObject_1.begin(); itr != jsonObject_1.end(); ++itr) {

        if (itr.key() != "metadata") {

            unsigned int counter = 0;

            

            for(unsigned int i=0;i<arraySize;i++){

                

                if(jsonObject_1[itr.key()][i]!=jsonObject_2[itr.key()][i]){

                    string a = to_string(counter);

                    jsonOutput[itr.key()]["Mismatches"][a].push_back(jsonObject_1[itr.key()][i]);

                    jsonOutput[itr.key()]["Mismatches"][a].push_back(jsonObject_2[itr.key()][i]);

                    

                    jsonOutput[itr.key()]["sample1"] = jsonObject_1[itr.key()];

                    jsonOutput[itr.key()]["sample2"] = jsonObject_2[itr.key()];

                    

                }

                counter++;

            }

            

        }

    }

    

    for (auto itr = jsonOutput.begin(); itr != jsonOutput.end(); ++itr) {

        numOfMis++;

    }

    jsonOutput["metadata"]["samplesWithConflictingResults"] = numOfMis;

    jsonOutput["sample1"]["metadata"]["arraySize"] = jsonObject_1["metadata"]["arraySize"];

    jsonOutput["sample1"]["metadata"]["file"] = argv[1];

    jsonOutput["sample1"]["metadata"]["numSamples"] = jsonObject_1["metadata"]["numSamples"];

    jsonOutput["sample2"]["metadata"]["arraySize"] = jsonObject_2["metadata"]["arraySize"];

    jsonOutput["sample2"]["metadata"]["file"] = argv[2];

    jsonOutput["sample2"]["metadata"]["numSamples"] = jsonObject_2["metadata"]["numSamples"];

    

    cout << jsonOutput<<endl;

    file_1.close();

    file_2.close(); 

    

}
