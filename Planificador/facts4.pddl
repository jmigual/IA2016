
(define (problem exercices-example1)
	
  (:domain exercices)

  (:objects
    exP exA exPr exP2 exP3 exP4 exP5 - exercice
    d1 d2 d3 d4 d5 - day ; d6 d7 d8 d9 d10 d11 d12 d13 d14 d15 - day
  )

  (:init
  	(currentDay d1)

  	(before d1 d2)
  	(before d2 d3)
  	(before d3 d4)
  	(before d4 d5)

  	(= (exLevel exA) 5)
  	(= (exLevel exP) 1)
  	(= (exLevel exP2) 1)
  	(= (exLevel exP3) 1)
  	(= (exLevel exP4) 1)
  	(= (exLevel exP5) 1)
  	(= (exLevel exPr) 1)
  	
  	(= (exTime exA) 10)
  	(= (exTime exP) 20)
  	(= (exTime exP2) 10)
  	(= (exTime exP3) 10)
  	(= (exTime exP4) 15)
  	(= (exTime exP5) 10)
  	(= (exTime exPr) 15)
  	
  	(= (timeDay) 0)


        (precursor exPr exA) 
        (precursor exP5 exP2) 
  	(preparer exP exA)
  	(preparer exP2 exA)
  	(preparer exP3 exA)
  	(preparer exP4 exA)
  	(preparer exP5 exA)
  ) 
  
  (:goal (and (currentDay d5) (= (exLevel exA) 10))

  ))