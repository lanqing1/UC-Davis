function LanQing_Cheng_splineplot(x,y)
% get the length of x
n=length(x);
m=LanQing_Cheng_splinecalc(x,y);
%set n-1 equations 
%plot the equation for n-1 times
for i=1:n-1
    syms z s
    s=m(1,i)+m(2,i)*(z-x(i))+m(3,i)*(z-x(i))^2+m(4,i)*(z-x(i))^3;

    fplot(s,[x(i),x(i+1)],'color','b');
    axis auto;
    hold on;
    
end
hold off;