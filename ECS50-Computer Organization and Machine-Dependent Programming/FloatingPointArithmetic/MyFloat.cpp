#include "MyFloat.h"
#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <cmath>
using namespace std;
MyFloat::MyFloat(){
  sign = 0;
  exponent = 0;
  mantissa = 0;
}

MyFloat::MyFloat(float f){
  unpackFloat(f);
}

MyFloat::MyFloat(const MyFloat & rhs){
	sign = rhs.sign;
	exponent = rhs.exponent;
	mantissa = rhs.mantissa;
}

ostream& operator<<(std::ostream &strm, const MyFloat &f){
	//this function is complete. No need to modify it.
	strm << f.packFloat();
	return strm;
}


MyFloat MyFloat::operator+(const MyFloat& rhs) const{
	MyFloat sum(rhs);	
	MyFloat larger, smaller;
	if (exponent > sum.exponent) {
		smaller = sum;
		larger.exponent = exponent;
		larger.sign = sign;
		larger.mantissa = mantissa;
	}else {
		larger = sum;
		smaller.exponent = exponent;
		smaller.sign = sign;
		smaller.mantissa = mantissa;
	}
	sum.sign = larger.sign;
	int diffExp = abs((int)larger.exponent - (int)smaller.exponent);
	int tempSmallerM = 1 << 23;
	int tempLargerM = 1 << 23; //1000000000...
	int carry = 1 << 24;// has 25 bits:100000...
	int checkB = 1<<23;//100000000
	int mask = ~tempLargerM; // 011111111111...

	tempLargerM |= larger.mantissa;// restore leading one to mantissa
	tempSmallerM |= smaller.mantissa;// 1.mantissa

	if (larger.sign != smaller.sign && larger.mantissa == smaller.mantissa && diffExp == 0) {//subtract same number 
		sum.exponent = 0;
		sum.mantissa = 0;
		sum.sign = 0;
		return sum;
	}
	if (larger.sign == smaller.sign) {//do addition	
		tempSmallerM = tempSmallerM >> diffExp;//shift smaller
		//tempSmallerM + tempLargerM has 24bits with leading 1
		//use mask to flip the leading 1 to 0
		int sumM = tempSmallerM + tempLargerM;
		int i=0;
		if(sumM<carry) {// no carry happens
			sum.mantissa = (tempSmallerM + tempLargerM) & mask;
			sum.exponent = larger.exponent;
			return sum;
		}else{
			while (sumM >= carry) {//carry happens
				sumM=sumM>>1;
				i++;		
			}			
			sum.mantissa = sumM & mask;
			sum.exponent = larger.exponent+i;
			return sum;
		}
		
	}else {// do subtraction		
		int borrow = (1<<(diffExp-1))& tempSmallerM;//check borrow 
		tempSmallerM = tempSmallerM >> diffExp;//shift smaller
		int sumM= (borrow==0)? (tempLargerM - tempSmallerM ):(tempLargerM - tempSmallerM-1);
		unsigned int i=0;
		while((checkB&sumM) ==0 ){// the leading bit is 0
			sumM = sumM<<1;
			i++;		
		}
		sum.mantissa =sumM & mask;
		sum.exponent = larger.exponent-i;
	}	
	return sum; 
}

MyFloat MyFloat::operator-(const MyFloat& rhs) const{
	MyFloat f(rhs);
	f.sign = f.sign == 1 ? 0 : 1;	
	return operator+(f);
}

bool MyFloat::operator==(const float rhs) const{
	MyFloat f(rhs);
	if (sign ==f.sign && mantissa==f.mantissa && exponent == f.exponent);
		return true;
	
	return false;		
}


void MyFloat::unpackFloat(float f) {
	//this function must be written in inline assembly
	//extracts the fields of f into sign, exponent, and mantissa
	//unsigned int float_int = *((unsigned int*)&f);//???
	//sign = float_int >> 31; 
	//exponent = (float_int << 1) >> 24;
	//mantissa= (float_int << 9) >> 9;

	__asm__(
	" movl %[f],%[sign];"
	"shrl $31, %[sign];"
	"movl %[f],%[exponent];"
	"shll $1, %[exponent];"
	"shrl $24, %[exponent];"
	"movl %[f], %[mantissa];"
	"shll $9, %[mantissa];"
	"shrl $9, %[mantissa]" :// code
	[sign] "=&r"(sign),[exponent] "=&r"(exponent),[mantissa] "=&r"(mantissa) :// outputs
	[f] "r" (f):// input f into random reg
	"cc"	
	);
	

}//unpackFloat

float MyFloat::packFloat() const{
	//this function must be written in inline assembly
  //returns the floating point number represented by this
  float f = 0;
  //sign exponent mantissa
  //sign = sign << 31; 
  //exponent = exponent<< 24;
  //exponent = exponent + 127;???
  //mantissa= mantissa;

  __asm__(
	  "movl %[mantissa], %[f];"
	  "shll $23, %[exponent];"
	  "shll $31, %[sign];"
	  "orl %[exponent], %[f];"
	  "orl %[sign], %[f]"://code
	[f] "=&r"(f)://input
	[sign]"r" (sign), [exponent] "r"(exponent), [mantissa] "r"(mantissa)://output
  "cc"
	  );
	
  
  return f;
}//packFloat
//



