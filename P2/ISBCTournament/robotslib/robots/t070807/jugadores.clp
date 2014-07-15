(defrule recoge
	?regla <- (inicio)
	=>
	(retract ?regla)
	(assert (Yox (fetch "Yox")))
	(assert (Yoy (fetch "Yoy")))
	(assert (jugador (fetch "jugador")))
	(assert (bolax (fetch "bolax")))
	(assert (bolay (fetch "bolay")))
	(assert (bolaabsoluto (fetch "bolaabsoluto")))
	(assert (lado (fetch "lado")))
)


(defrule portero
	(not (portero))
	(not (fin))
	(jugador 0)
	=>
	(assert (portero))
	
	(store jugador 0)
	
)

(defrule defensa
	(not (defensa))
	(not (fin))
	(jugador ?n)
	(or (test (= ?n 1)) (test (= ?n 2)))
	=>
	(assert (defensa))
	
	(store jugador ?n)
)

(defrule delantero
	(not (delantero))
	(not (fin))
	(jugador ?n)
	(or (test (= ?n 3)) (test (= ?n 4)))
	=>
	(assert (delantero))
	
	(store jugador ?n)

)

(defrule porterovuelveaporteriaoeste;;va a la porteria
?regla<-(portero)
(not (fin))
(lado -1)
(bolax ?xbo)
(bolay ?ybo)
(bolaabsoluto ?ba)
(Yox ?x)
(Yoy ?y)
(test (>= ?ba -1.0));;


	=>
	(retract ?regla)
	(assert (fin))
	(store Nx 0);;si no no recoge el valor bien
	(store Ny 0)
	(store jugada 2)
)


(defrule porterovuelveaporteriaeste;;va a la porteria
?regla<-(portero)
(not (fin))
(lado 1)
(bolax ?xbo)
(bolay ?ybo)
(bolaabsoluto ?ba)
(Yox ?x)
(Yoy ?y)
(test (<= ?ba 1.0));;


	=>
	(retract ?regla)
	(assert (fin))
		(store Nx 0);;si no no recoge el valor bien
	(store Ny 0)
	(store jugada 2)
)



(defrule porteroaporpelotaoeste ;;va a por la pelota
	?regla<-(portero)
	(not (fin))
	(lado -1)
	(bolaabsoluto ?ba)
	(bolax ?bx)
	(bolay ?by)
	(test (< ?ba -1.0))
	=>
	(retract ?regla)
	(assert (fin))
	(store Nx ?bx);;si no no recoge el valor bien
	(store Ny ?by)
	(store jugada 1)

	
)

(defrule porteroaporpelotaeste ;;va a por la pelota
	?regla<-(portero)
	(not (fin))
	(lado 1)
	(bolax ?bx)
	(bolay ?by)
	(bolaabsoluto ?xpa)
	
	(test (> ?xpa 1.0))
	=>
	(retract ?regla)
	(assert (fin))
	(store Nx ?bx);;si no no recoge el valor bien
	(store Ny ?by)
	(store jugada 1)	
)
;;;;;;;;;;;;;;;;;;;fin portero


;;;;;reglas para defensa

(defrule defensaaporbolaoeste  ;despeje dos
	?regla<-(defensa)
	(not (fin))
	(lado -1)
	(bolaabsoluto ?ba)
	(bolax ?bx)
	(bolay ?by)
	(test (< ?ba 0))
	=>
	(retract ?regla)
	(assert (fin))
	(store Nx ?bx)
	(store Ny ?by)
	(store jugada 1)

	
)

(defrule defensaaporbolaeste  ;despeje dos
	?regla<-(defensa)
	(not (fin))
	(lado 1)
	(bolaabsoluto ?ba)
	(bolax ?bx)
	(bolay ?by)
	(test (> ?ba 0))
	=>
	(retract ?regla)
	(assert (fin))
	(store Nx ?bx)
	(store Ny ?by)
	(store jugada 1)

	
)

(defrule defensabuscasitioeste ;;despeje uno
	?regla<-(defensa)
	(not (fin))
	(lado 1)
	(bolaabsoluto ?ba)
	(bolay ?by)
	(test (<= ?ba 0))
	=>
	(retract ?regla)
	(assert (fin))
	(store Nx 0.3)
	(store Ny ?by)
	(store jugada 2)	
)

(defrule defensabuscasitiooeste ;;despeje uno
	?regla<-(defensa)
	(not (fin))
	(lado -1)
	(bolaabsoluto ?ba)
	(bolay ?by)
	(test (>= ?ba 0))
	=>
	(retract ?regla)
	(assert (fin))
	(store Nx -0.3)
	(store Ny ?by)
	(store jugada 2)	
)


(defrule defensaensusitiooeste;;se queda en su sitio
	?regla<-(defensa)
	(Yox ?x)
	(not (fin))
	(bolaabsoluto ?ba)
	(lado -1)
	(test (>= ?ba 0))
	(test (<= ?x -0.3))
	(test (<= ?x -0.3))
	
	=>
	(retract ?regla)
	(assert (fin))
	(store jugada 3)
	(store Nx 0)
	(store Ny 0)
	
)

(defrule defensaensusitioeste;;se keda en su sitio
	?regla<-(defensa)
	(not (fin))
	(Yox ?x)
	(lado 1)
	(bolaabsoluto ?ba)
	(bolay ?by)
	(test (<= ?ba 0))
	(test (>= ?x 0.3))
	(test (>= ?x 0.3))

	=>
	(retract ?regla)
	(assert (fin))
	(store jugada 3)
	(store Nx 0)
	(store Ny 0)
	
)

;;;;fin jugadas defensa

;;;;;;;;;;;;;jugadas delantero

	
	

	
	(defrule delanteroabuscaparaeste
	?regla<-(delantero)
	(lado 1)
	(bolax ?xbo)
	(bolay ?ybo)
	(not (fin))
	(bolaabsoluto ?ba)
	(test (>= ?ba 0))
	(Yoy ?y)

	=>
	(retract ?regla)
	(assert (fin))
	(store Nx -0.1)
	(store Ny ?ybo)
	(store jugada 2)
	)
	
		
	(defrule delanteroparaeste
	?regla<-(delantero)
	(lado 1)
	(bolax ?xbo)
	(bolay ?ybo)
	(not (fin))
	(bolaabsoluto ?ba)
	
	(Yoy ?y)
	(Yox ?x)
	(test (>= ?ba 0))
		(test (< ?x -0.1))

	=>
	(retract ?regla)
	(assert (fin))
	(store Nx -0.1)
	(store Ny ?ybo)
	(store jugada 3)
	)
	
	
	
	(defrule delanteroavaaporpelitaeste
	?regla<-(delantero)
	(lado 1)
	(bolax ?xbo)
	(bolay ?ybo)
	(Yox ?x)
	(Yoy ?y)
	(not (fin))
	

	=>
	(retract ?regla)
	(assert (fin))
	(store Nx ?xbo)
	(store Ny ?ybo)
	(store jugada 1)
	)
	
	

		(defrule delanteroabuscaparaoeste
	?regla<-(delantero)
	
	(lado -1)
	(bolax ?xbo)
	(bolay ?ybo)
	(not (fin))
	(bolaabsoluto ?ba)
	(test (<= ?ba 0))
	(test (<= ?ba 0))
	(test (<= ?ba 0))
	(Yoy ?y)

	=>
	(retract ?regla)
	(assert (fin))
	(store Nx 0)
	(store Ny ?ybo)
	(store jugada 2)
	)
	
		
	(defrule delanteroparaoeste

	?regla<-(delantero)
	(lado -1)
	(bolax ?xbo)
	(bolay ?ybo)
	(not (fin))
	(bolaabsoluto ?ba)
	
	(Yoy ?y)
	(Yox ?x)
	(test (<= ?ba 0))
		(test (< ?x 0))
		(test (< ?x 0))

	=>
	(retract ?regla)
	(assert (fin))
	(store Nx 0.1)
	(store Ny ?ybo)
	(store jugada 3)
	)
	
	
	
	(defrule delanteroavaaporpelitaoeste
	?regla<-(delantero)
	(lado -1)
	(bolax ?xbo)
	(bolay ?ybo)
	(Yox ?x)
	(Yoy ?y)
	(not (fin))
	

	=>
	(retract ?regla)
	(assert (fin))
	(store Nx ?xbo)
	(store Ny ?ybo)
	(store jugada 1)
	)