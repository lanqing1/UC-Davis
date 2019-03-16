package simplify
import "hw2/expr"
import (_ "fmt")

func Simplify(e expr.Expr, env expr.Env) expr.Expr {

	switch a:=e.(type){
	case expr.Var:
		v,ok:=env[a]
		if ok{
			return expr.Literal(v)
		}
		return e

	case expr.Literal:
		return e //expr.Literal(e.Eval(env))

	case expr.Unary:
		switch a.Op {

		case '+':
			v,ok:=env["X"]
			_=v
			if ok{
				return expr.Literal(a.Eval(env))
			}
			return a.X//expr.Unary{Op:'+',X:a.X}

		case '-':
			v,ok:=env["X"]
			_=v
			if ok{
				return expr.Literal(a.Eval(env))
			}
			return expr.Unary{Op:'-',X:a.X}
			}

	case expr.Binary:
		newX:=Simplify(a.X,env)
		newY:=Simplify(a.Y,env)
		typeX,ok1:=newX.(expr.Literal)
		typeY,ok2:=newY.(expr.Literal)
		_,_=typeX, typeY
		if ok1&&ok2{
			return expr.Literal(a.Eval(env))
		}
		switch a.Op{
		case '+':
			if newX==expr.Literal(0){
				return newY
			 }
			if newY==expr.Literal(0){
 				return newX
 		 }
		 return expr.Binary{Op:a.Op, X:newX, Y:newY}
		case '-':
			if newX==expr.Literal(0){
				return expr.Unary{Op:a.Op,X:newY}
			 }
			if newY==expr.Literal(0){
 				return newX
 		 }
		 return expr.Binary{Op:a.Op,X:newX, Y:newY}

		case '/':
			if newX==expr.Literal(0){
				return expr.Literal(0)
			 }
			return  expr.Binary{Op:a.Op,X:newX, Y:newY}

		case '*':
			if newX==expr.Literal(0)||newY==expr.Literal(0){
				return expr.Literal(0)
			}
			if newX==expr.Literal(1){
				return newY
			}
			if newY==expr.Literal(1){
				return newX
			}
			return expr.Binary{Op:a.Op , X:newX, Y:newY}
		}
}
panic("unknown expression")
}
