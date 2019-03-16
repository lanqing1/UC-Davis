#include<fstream>
#include <iostream>
#include "BST.h"
#include "json.hpp"
using namespace std;

int main(int argc, char** argv) {
  ifstream file;
  file.open(argv[1]);
  nlohmann::json jsonObject,jsonOutput;
  if (file.is_open()) {
	  file >> jsonObject;
	  BST T;
	  for (auto itr = jsonObject.begin(); itr != jsonObject.end(); ++itr) {
		  if (itr.key() != "metadata") {
			  if (jsonObject[itr.key()]["operation"] == "Insert") {
				  T.Insert(jsonObject[itr.key()]["key"]);
			  }
			  else if (jsonObject[itr.key()]["operation"] == "Delete") {
				  T.Delete(jsonObject[itr.key()]["key"]);
			  }
			  else if (jsonObject[itr.key()]["operation"] == "DeleteMin") {
				  T.DeleteMin();
			  }
		  }
	  }
	  if(!T.empty())
		T.PreOrder();//check height and bf
	  cout << T.JSON();
	  file.close();

  }
  return 0;
}

