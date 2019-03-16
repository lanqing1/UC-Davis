package nfa
// A state in the NFA is labeled by a single integer.
type state uint

// TransitionFunction tells us, given a current state and some symbol, which
// other states the NFA can move to.
//
// Deterministic automata have only one possible destination state,
// but we're working with non-deterministic automata.
type TransitionFunction func(st state, act rune) []state

func Reachable(
	transitions TransitionFunction,
	start, final state,
	input []rune,
) bool {
	if  len(input)==0 {return start==final}

		//Possible_state:=transitions(start,input[0])
		// if len(Possible_state)==1{
		// 	return Reachable(transitions,Possible_state[0],final,input[1:])
		// 	}
		// if len(Possible_state)==2{
		// 	return Reachable(transitions,Possible_state[0],final,input[1:])|| Reachable(transitions,Possible_state[1],final,input[1:])
		// 	}
		for _,next:=range transitions(start,input[0]){
			if Reachable(transitions,next,final,input[1:]){return true}
		}

	return false
}
