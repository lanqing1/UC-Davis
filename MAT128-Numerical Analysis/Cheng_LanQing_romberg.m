function [intapprox] =Cheng_LanQing_romberg(a,b,n,f)
a=0;
b=pi;
n=6;
f=@(x) sin(x);
format long
h=(b-a);

R_1=[];
matrix=[];
R_2=[];
%calculate R1,1
R_1(1)=h/2*(f(a)+f(b));
%put R1,1 into matrix
matrix=[matrix,R_1(1)];

%use loop to calculate 2 row each time, save all results in matrix[]
for i=2:n
    syms k;
    R_2(1)=0.5*(R_1(1)+ h*symsum(f(a+(k-0.5)*h),k,1,2^(i-2)));
    for j=2:i
        R_2(j)=R_2(j-1)+1/(4.^(j-1)-1)*(R_2(j-1)-R_1(j-1));
    end
    for j=1:i
        matrix=[matrix,R_2(j)];
    end
    h=h/2;
    for j=1:i
        R_1(j)=R_2(j);
    end
       
end
syms x;
%get the last num in matrix which is Rn,n0
intapprox=matrix(symsum(x,x,1,n));


 


