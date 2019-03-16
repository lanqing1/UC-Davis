package bonus
import (
	"bytes"
	"go/ast"
	"go/format"
	"go/parser"
	"go/token"
	"strconv"
	"hw2/expr"
	"hw2/simplify"
)
func getResult(node ast.Node) string{
	var s string
	ast.Inspect(node, func(node ast.Node)bool{
		switch x:=node.(type){
		case *ast.CallExpr:
			switch a:= x.Fun.(type){
			case *ast.SelectorExpr:
				if a.Sel.Name == "ParseAndEval"{
					if len(x.Args)==2{
						switch b:=x.Args[0].(type){
						case *ast.BasicLit :
							s= b.Value
							s,_=strconv.Unquote(s)
							e,error:=expr.Parse(s)
							if (error==nil){
								sim:=simplify.Simplify(e,expr.Env{})
								s = expr.Format(sim)
							}
						}
					}
				}
			}
		}
		return true
	})
	return s
}

func rewriteCalls(node ast.Node) {
	ast.Inspect(node, func(node ast.Node)bool{
		switch x:=node.(type){
		case  *ast.BlockStmt:
			for _,element:=range x.List{
				switch c:=element.(type){
				case *ast.AssignStmt:
					if d,ok:= c.Lhs[0].(*ast.Ident);ok{
						if d.Name == "result"{
							switch e:=c.Rhs[0].(type){
							case *ast.BinaryExpr:
								yIsCallExpr,ok2:=e.Y.(*ast.CallExpr)
								xIsNum,ok1:=e.X.(*ast.BasicLit)
								if ok1&&ok2{
									yVal:=getResult(yIsCallExpr)
									if yVal!=""{
										a:=xIsNum.ValuePos
										c:=int(a)+4
										positionY := token.Pos(c)
										newStruct:= ast.BasicLit{ValuePos:positionY,Kind:xIsNum.Kind,Value:yVal}
										e.Y=&newStruct

									}

								}
								xIsCallExpr,ok2:=e.X.(*ast.CallExpr)
								yIsNum,ok1:=e.Y.(*ast.BasicLit)
								if ok1 &&ok2{
									xVal:=getResult(xIsCallExpr)
									//fmt.Println("x.Val=",xVal,"i=",i)
									if xVal!=""{
										a:=yIsNum.ValuePos
										c:=int(a)-4
										positionX := token.Pos(c)
										newStruct:= ast.BasicLit{ValuePos:positionX,Kind:yIsNum.Kind,Value:xVal}
										e.X=&newStruct
									//	fmt.Println(e.X)

									}

								}
							}
						}
					}
				}
			}
		}
		return true
	})
}

func SimplifyParseAndEval(src string) string {
	fset := token.NewFileSet()
	f, err := parser.ParseFile(fset, "src.go", src, 0)
	if err != nil {
		panic(err)
	}

	rewriteCalls(f)

	var buf bytes.Buffer
	format.Node(&buf, fset, f)
	return buf.String()
}
