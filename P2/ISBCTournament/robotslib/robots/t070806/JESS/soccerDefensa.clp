
(defrule inicial
=>
(assert (x_ego_ourgoal (fetch "x_ego_ourgoal")))
(assert (x_ego_ball (fetch "x_ego_ball")))
)


(defrule a_porteria
(declare (salience 3))
(not (avanza_despeja))
?0<-(x_ego_ball ?xo)
(test (> 0.0 ?xo))
=>
(assert (a_porteria))
(retract ?0)
(store "op" 0)
)

(defrule avanza
(declare (salience 5))
?0<-(x_ego_ourgoal ?xg)
(test (> 1.3 ?xg ))
=>
(assert (avanza))
(retract ?0)
(store "op" 1)
)

(defrule avanza_peligro
(declare (salience 3))
?0<-(x_ego_ourgoal ?xg)
(test (and(> 1.5 ?xg )(>= ?xg 1.3)))
=>
(assert (avanza_peligro))
(retract ?0)
(store "op" 2)
)
