% sentence
s --> np(subj,Case), vp(Case).

% noun phrase
np(subj,other) --> pro(subj).
np(subj,other) --> det, n(other).
np(subj,singular)-->pro(singular).
np(subj,singular)-->det,n(singular).
np(obj) --> pro(obj).
np(obj) --> det, n(_).


% verb phrase with intransitive verb
vp(_) --> v_intran(_).

% verb phrase with transitive verb
vp(other) --> v_tran(other), np(obj).
vp(singular)-->v_tran(singular),np(obj).


% lexicon
pro(singular) --> [he].
pro(obj) --> [him].
pro(subj) --> [they].
pro(obj) --> [them].
pro(subj) --> [i].
pro(obj) --> [me].
pro(subj) --> [we].
pro(obj) --> [us].

det --> [the].
n(singular)-->[dog].
n(other)-->[dogs].
%n(_) --> [dog].
%n(_) --> [dogs].

v_intran(_) --> [arrived].
v_tran(other) --> [see].
v_tran(singular) --> [sees].







