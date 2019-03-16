initial(1).
% add the final state or states here
final(1).

% we can loop on the first state using any symbol except plur, or '^'. eps:eps is also not allowed.
transition(1, S, 1, S) :-
    S \= eps,
    S \= plur,
    S \= '^'.

% this transition models how to use phonetic features to restrict the symbols allowed in a transition
transition(1, S, 2, S) :-
    sibilant(S).

% here you need to add additional transitions to complete the machine
transition(2,'^',3,ah).
transition(3,plur,4,z).
transition(4,'#',1,'#').
%transition(5,eps,1,eps).
transition(1,V,6,V):-
    voiced(V).
transition(6,'^',7,z).
transition(7,plur,4,eps).

transition(1,V,8,V):-
    unvoiced(V).
transition(8,'^',7,s).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% the following are some potentially useful features from the IPA
% voiced=s
voiced(S):-
    S == aa;
    S == ae;
    % you need to add more conditions to account for the full set of voiced phonemes
    % this list is NOT complete
    S == aw;
    S == ay;
    S == b;
    S == d;
    S == dh;
    S == ih;
    S == iy;
    S == m;
    S == n;
    S == uw;
    S == w;
    S==ey;
    S==eh;
    S==er;
    S==uh;
    S==aa;
    S==ae;
    S==ao;
    S==ah;
    S==ow;
    S==g;
    S==v;
    S==nx;
    S==ng;
    S==l;
    S==r;
    S==oy.


%sibilant =ah,z
sibilant(S) :-
    S == s;
    % as above, this list of phonemes is NOT complete
    S == z;
    S==sh;
    S==zh;
    S==ch;
    S==jh.

% the following predicate is complete as written
% unvoiced and unsibilant=s
unvoiced(S) :-
    S \= eps,
    S \= '^',
    S \= '#',
    S \= plur,
    % the following line introduces a new predicate, not/1, which takes a predicate as an arguement and is true if and only if that predicate is false
    not(voiced(S)),
    not(sibilant(S)).





