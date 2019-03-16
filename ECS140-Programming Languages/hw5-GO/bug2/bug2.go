package bug2

import (
	"sync"
)

func bug2(n int, foo func(int) int, out chan int) {
	var wg sync.WaitGroup
	for i := 0; i < n; i++ {
		wg.Add(1)
		go func(wg *sync.WaitGroup, id int) {
			defer wg.Done()
			out <- foo(id)

		}(&wg,i)
	}
	wg.Wait()
	close(out)
}
