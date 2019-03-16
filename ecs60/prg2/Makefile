CC=g++
DEV=-Wall -g -std=c++14
OPT=-O3 -std=c++14

JSON=json.hpp

HEAPSORT_SRC=heapsort.cxx priorityqueue.cpp
HEAPSORT_EXE=heapsort.exe

BUILDHEAP_SRC=buildheap.cxx priorityqueue.cpp priorityqueue.h
BUILDHEAP_EXE=buildheap.exe

CREATE_DATA_SRC=createsortingdata.cxx createheapoperationdata.cxx 
CREATE_DATA_EXE=$(CREATE_DATA_SRC:.cxx=.exe)

.PHONY: all
# do we need heapsort.exe?
all: $(CREATE_DATA_EXE) $(BUILDHEAP_EXE) $(HEAPSORT_EXE)

$(HEAPSORT_EXE): %.exe: %.cxx 
	$(CC) $(OPT) $(HEAPSORT_SRC) -o $@


$(BUILDHEAP_EXE): %.exe: %.cxx 
	$(CC) $(OPT) $(BUILDHEAP_SRC) -o $@

$(CREATE_DATA_EXE): %.exe: %.cxx $(JSON)
	$(CC) $(OPT) $< -o $@

# Build
.PHONY: clean
clean:
	rm -f *.o
	rm -f *.o3
	rm -f *.exe
	rm -rf *.exe.dSYM

.PHONY: update
update:
	make clean
	make all
