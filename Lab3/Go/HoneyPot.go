package main

import (
	"fmt"
	"math/rand"
	"time"
)

type Pot struct {
	volume    int
	collected int
	receive   chan byte
	complete chan bool
	wake      chan byte
}

func (pot *Pot) Start() {
	for i := range pot.receive {
		pot.collected += int(i)
		fmt.Printf("Pot collected %v honey\n", pot.collected)
		if pot.collected == pot.volume {
			pot.complete <- true
			close(pot.receive)
			close(pot.complete)
			fmt.Println("Pot is full.")
			pot.wake <- 1
			pot.collected = 0
			close(pot.wake)
		}
	}
}

type Bee struct {
	name      string
	bring     chan byte
	complete  chan bool
}

func (bee *Bee) Start() {
	for {
		select {
		case <-bee.complete:
			return
		default:
			fmt.Printf("Bee %s brought honey.\n", bee.name)
			bee.bring <- 1
			var t uint32 = rand.Uint32() % 500
			time.Sleep((100 + time.Duration(t)) * time.Millisecond)
		}
	}
}

type Bear struct {
	wake chan byte
}

func (bear *Bear) Start() {
	for range bear.wake {
		fmt.Println("Bear waked up!")
	}
}

func main() {
	amountOfBees := 10
	volumeOfPot := 100

	fromBeeToPot := make(chan byte, 10)
	complete := make(chan bool)
	wake := make(chan byte)

	pot := Pot{volumeOfPot, 0, fromBeeToPot, complete, wake}

	bees := make([]Bee, amountOfBees, amountOfBees)
	for i := 0; i < amountOfBees; i++ {
		bees[i].name = "Bee #" + fmt.Sprintf("%v", i)
		bees[i].bring = fromBeeToPot
		bees[i].complete = complete
	}

	bear := Bear{wake}

	for i := 0; i < amountOfBees; i++ {
		go bees[i].Start()
	}
	go pot.Start()
	bear.Start()
}