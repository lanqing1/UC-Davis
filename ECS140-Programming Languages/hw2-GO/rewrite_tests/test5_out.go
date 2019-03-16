package main

import (
	"fmt"
	"hw2/expr"
)

func main() {
	var result float64

	result = expr.ParseAndEval("3", expr.Env{})
	fmt.Printf("%d\n", result)

}
