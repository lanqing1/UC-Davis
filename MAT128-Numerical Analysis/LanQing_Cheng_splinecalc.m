function[matrix] = LanQing_Cheng_splinecalc(x,y)

n = length(x);

for i=1:n-1
    h(i)=x(i+1)-x(i);
end
for j=2:n-1
    a(j)=3/h(j)*(y(j+1)-y(j))-3/h(j-1)*(y(j)-y(j-1));

end

l(1)=1;
mu(1)=0;
z(1)=0;

for i=2:n-1
    l(i)=2*(x(i+1)-x(i-1))-h(i-1)*mu(i-1);
    mu(i)=h(i)/l(i);
    z(i)=(a(i)-h(i-1)*z(i-1))/l(i);
end
l(n)=1;
z(n)=0;
c(n)=0;

for j= n-1:-1:1
    c(j)=z(j)-mu(j)*c(j+1);
    b(j)=(y(j+1)-y(j))/h(j)-h(j)*(c(j+1)+2*c(j))/3;
    d(j)=(c(j+1)-c(j))/(3*h(j));
end
    matrix=[];
for i=1:n-1  
    col=[y(i);b(i);c(i);d(i)];
    matrix=[matrix,col];
end
    

