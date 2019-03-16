:- use_module(library(tabling)).
:- table s/3.
:- table np/3.
:- table vp/3.
:- table pp/3.

% enter and expand your non-terminal rules from p1 here
s(s(NP, VP)) --> np(NP), vp(VP).
np(np(DET,N)) -->det(DET),n(N).
np(np(NP,PP))-->np(NP),pp(PP).
vp(vp(V,NP)) -->v(V),np(NP).
pp(pp(P,NP))-->p(P),np(NP).
vp(vp(VP,PP))-->vp(VP),pp(PP).

v(v(chased)) --> [chased].
det(det(the))-->[the].
n(n(dogs)) --> [dogs].
n(n(house))-->[house].
n(n(cats)) --> [cats].
n(n(garden))-->[garden].
p(p(in)) --> [in].

