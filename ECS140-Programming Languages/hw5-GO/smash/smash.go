package smash
import (
	"io"
	"strings"
	"bytes"
	"sync"
)
type word string
type S struct{
	m map[uint32]uint
	sync.Mutex
}

func Smash(r io.Reader, smasher func(word) uint32) map[uint32]uint {
	m := make(map[uint32]uint)
	c:= make(chan uint32)
	buf := new(bytes.Buffer)
	buf.ReadFrom(r)
	str:=strings.Fields(buf.String())
	var wg sync.WaitGroup

	for i:=0;i<len(str);i++ {
		wg.Add(1)
		go func (m map[uint32]uint, i int){
			defer wg.Done()
			c <- smasher( word(str[i]))
		}(m,i)
	}

go func(){
	wg.Wait()
	close(c)
}()

 for key := range c{
	 m[key] += 1
 }
	return m
}
