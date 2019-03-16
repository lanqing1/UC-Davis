
transition(1, b, 2, p).
transition(1, p, 2, b).
transition(1, t, 2, d).
transition(1, d, 2, t).
transition(1, s, 2, z).
transition(1, z, 2, s).

transition(1, b, 2, b).
transition(1, p, 2, p).
transition(1, t, 2, t).
transition(1, d, 2, d).
transition(1, s, 2, s).
transition(1, z, 2, z).
transition(1, Else, 1, Else).
transition(2, Else, 2, Else).


final(2).
final(1).
initial(1).

