:- use_module(library(tabling)).
:- table go/3.

% This is what we use to run the transducer
fst(Input, Output) :-
  initial(State),
  go(State, Input, Output).

% This is how we know that we've reached the end of the run
go(CurrentState, [], []) :-
  final(CurrentState).

% Use a transition with no eps on either side
go(CurrentState, [A|InString], [B|OutString]) :-
  transition(CurrentState, A, NextState, B),
  A \= eps,
  B \= eps,
  go(NextState, InString, OutString).

% use a transition with eps on the input side
go(CurrentState, InString, [B|OutString]) :-
  transition(CurrentState, eps, NextState, B),
  B \= eps,
  go(NextState, InString, OutString).

% use a transition with eps on the output side
go(CurrentState, [A|InString], OutString) :-
  transition(CurrentState, A, NextState, eps),
  A \= eps,
  go(NextState, InString, OutString).

% We need one more go/3 to handle epsilon-epsilon transitions
% ENTER YOUR CODE HERE)

