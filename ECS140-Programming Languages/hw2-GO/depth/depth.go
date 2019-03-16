package depth

import "hw2/expr"
import "math"
// Depth returns the maximum number of AST nodes between the
// root of the tree and any leaf (literal or variable) in the tree.
func Depth(e expr.Expr) uint {
	switch a:=e.(type){
		case expr.Var:
			return uint(1)
		case expr.Literal:
			return uint(1)
		case expr.Unary:
			return uint(1)+Depth(a.X)
		case expr.Binary:
			return 1+uint( math.Max( float64(Depth(a.X)),float64(Depth(a.Y))))
		default:
			panic("unknown expresion")
		}

}
