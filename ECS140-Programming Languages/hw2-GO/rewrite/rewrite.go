package rewrite

import (
	"bytes"
	"go/ast"
	"go/format"
	"go/parser"
	"go/token"
	"hw2/simplify"
	"hw2/expr"
	"strconv"
)

// rewriteCalls should modify the passed AST
func rewriteCalls(node ast.Node) {
	ast.Inspect(node, func(node ast.Node)bool{
		switch x:=node.(type){
		case *ast.CallExpr:
			switch a:= x.Fun.(type){
			case *ast.SelectorExpr:
				if a.Sel.Name == "ParseAndEval"{
					if len(x.Args)==2{
						//fmt.Println(x.Args[0])
						switch b:=x.Args[0].(type){
						case *ast.BasicLit :
							s := string(b.Value)
							s,_=strconv.Unquote(s)
							e,error:=expr.Parse(s)
							if (error==nil){
								sim:=simplify.Simplify(e,expr.Env{})
								afterFormat := expr.Format(sim)
								afterFormat=strconv.Quote(afterFormat)
								b.Value = afterFormat
							}
						}
					}
				}
			}
		}
		return true
	})
	//panic("TODO: implement this!")
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
