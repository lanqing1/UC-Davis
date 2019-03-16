year_1953_1996_novels(Book) :-
    %% remove fail and add body/other cases for this predicate
   novel(Book,Y),(Y=1953;Y=1996).

period_1800_1900_novels(Book) :-
    %% remove fail and add body/other cases for this predicate
   novel(Book,Year),1800<Year,Year<1900.

mymember(X,[X|_]).
mymember(X,[_|Y]):- mymember(X,Y).

lotr_fans(Fan) :-
   fan(Fan,Listbook),mymember(the_lord_of_the_rings,Listbook).

check([X|A],B):-mymember(X,B);check(A,B).

checkauthor(A,B):- author(A,Alist), check(Alist,B).

author_names(Author) :-
   fan(chandler,B),checkauthor(Author,B).

checkfan(B,F):-
   fan(F,Fanbook),check(Fanbook,B).

fans_names(Fan) :-
   author(brandon_sanderson,B),checkfan(B,Fan).


mutual_novels(Book) :-
    %% remove fail and add body/other cases for this predicate
    fan(ross,B1),fan(phoebe,B2),fan(monica,B3),checknovel(Book,B1,B2,B3).

checknovel(Book,B1,B2,B3):-
      mymember(Book,B1),mymember(Book,B2);
      mymember(Book,B1),mymember(Book,B3);
      mymember(Book,B2),mymember(Book,B3).
