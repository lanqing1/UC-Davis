CC=g++
DEV=-Wall -g -std=c++14
OPT=-O3 -std=c++14



.PHONY: all

all: BSTSanityCheck CreateData AVLCommands P3Test

AVLCommands: BST.cpp BST.h AVLCommands.cpp
	$(CC) $(DEV) BST.cpp AVLCommands.cpp -o AVLCommands

P3Test: BST.cpp BST.h BSTSanityCheck.cxx
	$(CC) $(DEV) BST.cpp BSTSanityCheck.cxx -o P3Test

CreateData: CreateData.cxx json.hpp
	$(CC) $(OPT) CreateData.cxx -o CreateData

BSTSanityCheck: BSTSanityCheck.cxx BST.o
	$(CC) $(DEV) BSTSanityCheck.cxx BST.o -o BSTSanityCheck

BST.o: BST.cpp BST.h
	$(CC) $(DEV) -c BST.cpp

# Build
.PHONY: clean
clean:
	rm -f *.o
	rm -f *.exe
	rm -rf *dSYM

.PHONY: update
update:
	make clean
	make all
