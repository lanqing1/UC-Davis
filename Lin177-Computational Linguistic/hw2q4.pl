transition(1, t, 2, t_h).
transition(1, k, 2, k_h).
transition(1, p, 2, p_h).

transition(1,s,3,s).
transition(3,A,2,A).
transition(2, V, 1, V):-
    V==ey1;
    V==eh1;
    V==ih1;
    V==uh1;
    V==ay1;
    V==ow1;
    V==oy1;
    V==iy1;
    V==aw1;
    V==uw1;
    V==aa1;
    V==ae1;
    V==ao1;
    V==ah1.

transition(1, Else, 1, Else):- Else\=s.
final(1).
initial(1).

