function [derivs] =Cheng_LanQing_numdiff(a,h,f)

f_val=f([a-2*h a-h a a+h a+2*h]);
derivs=[];
%3point midpoint with h
derivs(1)=1/2/h*(f_val(4)-f_val(2));
%3point endpoint with h
derivs(2)=1/2/h*(-3*f_val(3)+4*f_val(4)-f_val(5));
%3point endpoint with -h
derivs(3)=1/2/h*(3*f_val(3)-4*f_val(2)+f_val(1));
%5point midpoint with h
derivs(4)=1/12/h*(f_val(1)-8*f_val(2)+8*f_val(4)-f_val(5));
