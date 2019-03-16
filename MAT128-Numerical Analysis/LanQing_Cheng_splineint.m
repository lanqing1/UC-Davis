function [intval] =LanQing_Cheng_splineint(x,y,a,b)
%get length of vector 
n=length(x);
m=LanQing_Cheng_splinecalc(x,y);
intval=0;
%use loop to calculate each integral and sum them
for i=1:n-1
    
    syms z
    S=m(1,i)+m(2,i)*(z-x(i))+m(3,i)*(z-x(i))^2+m(4,i)*(z-x(i))^3;
    if(i==1)
        intval=intval+int(S,[a,x(i+1)]);    
    elseif(i==n-1)
        intval=intval+int(S,[x(i),b]);        
    else
        intval=intval+int(S,[x(i),x(i+1)]);
    end
end
