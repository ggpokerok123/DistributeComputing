package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

var Matrix [][]int

func worker(wg *sync.WaitGroup, id int, jobs <-chan []int, completeChannel chan bool, complete *bool) {
	for j := range jobs {
		if *complete {
			wg.Done()
			return
		}

		select {
		case <-completeChannel:
			wg.Done()
			return
		default:
			fmt.Println("bee group", id, "started job", j)
			time.Sleep(time.Second)
			for i := range j {
				if j[i] == 1 {
					completeChannel <- true
					*complete = true
					fmt.Println("Bee group", id, "found bear")
				}
			}
			fmt.Println("bee group", id, "finished job", j)
		}
	}
}

func fillingMatrix(n, m int) [][]int {
	matrix := make([][]int, n, m)
	for i := range matrix {
		matrix[i] = make([]int, m)
	}

	rand.Seed(time.Now().UnixNano())
	x := rand.Intn(n - 1)
	y := rand.Intn(m - 1)
	matrix[x][y] = 1

	fmt.Println("Forest created ", matrix)

	return matrix
}

func main() {
	var (
		n  = 10
		m  = 10
		wg sync.WaitGroup
	)

	Matrix = fillingMatrix(n, m)
	completeChannel := make(chan bool)
	jobs := make(chan []int, 100)
	var completeFlag bool = false
	for w := 1; w <= 3; w++ {
		go worker(&wg, w, jobs, completeChannelChannel, &completeFlag)
		wg.Add(1)
	}

	for j := 0; j < n; j++ {
		jobs <- Matrix[j]
	}
	close(jobs)
	wg.Wait()
}