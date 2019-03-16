:- use_module(library(tabling)).
:- table s/2.
:- table np/2.
:- table vp/2.
:- table pp/2.

% complete the non-terminal --> non-terminal rules below
s --> np, vp.
np--> det, n.
np--> np,pp.
vp--> v, np.
pp--> p,np.
vp--> vp, pp.


% complete the non-terminal --> [terminal] rules here
v --> [chased].
det-->[the].
n-->[garden].
n --> [cats].
n --> [dogs].
p --> [in].




