
reachable(S,S,[]).

reachable(S, F, [X|I]):- transition(S,X,Next),helper(Next,F,I).

helper([S|Next],F,I):- reachable(S,F,I);helper(Next,F,I).

