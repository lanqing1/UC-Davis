
opp(left,right).
opp(right,left).

safe(state(F,W,G,C)):-
    F=W,F=G,G=C;
    F=G;
    F=W,F=C;
    F=W,F=G.


take(state(X,X,G,C),state(Y,Y,G,C)) :- opp(X,Y).
take(state(X,W,X,C),state(Y,W,Y,C)) :- opp(X,Y).
take(state(X,W,G,X),state(Y,W,G,Y)) :- opp(X,Y).
take(state(X,W,G,C),state(Y,W,G,C)) :- opp(X,Y).

solve(F1, W1, G1, C1, F2, W2, G2, C2) :-
    safe(state(F1,W1,G1,C1)),helper(state(F1,W1,G1,C1),state(F1,W1,G1,C1),state(F2,W2,G2,C2)).

helper(state(F1,W1,G1,C1),V,state(F2,W2,G2,C2)):-
    state(F1,W1,G1,C1)=state(F2,W2,G2,C2);
    take(state(F1,W1,G1,C1),S),
    safe(S),
    not(member(S,V)),
    helper(S,[S|V],state(F2,W2,G2,C2)).


