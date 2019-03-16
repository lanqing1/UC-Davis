package nfa
type state uint
type TransitionFunction func(st state, act rune) []state

func Reachable(transitions TransitionFunction,start, final state,input []rune,) bool {
	if len(input)==0 {return start==final }
	ch := make(chan bool)

	next:=transitions(start,input[0])

	for i:=0;i<len(next);i++ {
		go helper (transitions,next[i],final,input[1:],ch)
		if <-ch {return true}
	}

	close(ch)
	return false
}

func helper(t TransitionFunction, s state,f state, i []rune,ch chan bool) {
  ch <- Reachable(t,s,f,i)
}
