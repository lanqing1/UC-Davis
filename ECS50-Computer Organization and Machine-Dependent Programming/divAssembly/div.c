#include <stdio.h>
#include <math.h>
#include <ctype.h>
#include <stdlib.h>
int main(int argc, char* argv[]) {
  long divident=atol(argv[1]);
  int divisor=atol(argv[2]);
  int quotient=0;
  long remainder=divident;
  int shiftNum=31;

  while( remainder>=divisor){
    long a=(remainder>>(shiftNum))/divisor;
    quotient+=a<<(shiftNum);
    remainder-=(a<<(shiftNum))*divisor;
    shiftNum--;
  }
  printf("%ld / %d = %d R %ld",divident,divisor,quotient,remainder);

  return 0;
}