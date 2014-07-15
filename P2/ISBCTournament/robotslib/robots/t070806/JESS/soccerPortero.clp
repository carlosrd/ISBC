
(defrule inicial
=>
(assert (x_ego_ourgoal (fetch "x_ego_ourgoal")))
(assert (y_ego_ourgoal (fetch "y_ego_ourgoal")))
(assert (radio (fetch "radio")))
(assert (x_ball (fetch "x_ball")))
(assert (y_ball (fetch "y_ball")))
(assert (y_ego_ball (fetch "y_ego_ball")))
(assert (y_me (fetch "y_me")))
)




(defrule salida_portero
(declare (salience 6))
?0<-(x_ball ?xb)
(test (> -1.0 ?xb ))
=>
(assert (salida_portero))
(retract ?0)
(store "op" 0)
)

(defrule a_porteria
(declare (salience 3))
(not (salida_portero))
(not (pelota_superior))
(not (pelota_inferior))
?0<-(x_ego_ourgoal ?xo)
(test (> 0.3 ?xo))
=>
(assert (a_porteria))
(retract ?0)
(store "op" 5)
)

(defrule pelota_superior
(declare (salience 3))
(not (salida_portero))
(not (pelota_inferior))
(not (fuera_porteria_superior))
(not (fuera_porteria_inferior))
?0<-(y_ball ?yb)
?1<-(y_me ?ym)
(test (and(>= ?yb 0.0)(> 0.25 ?ym)) )
=>
(assert (pelota_superior))
(retract ?0 ?1)
(store "op" 1)
)



(defrule pelota_inferior
(declare (salience 3))
(not (salida_portero))
(not (pelota_superior))
(not (fuera_porteria_superior))
(not (fuera_porteria_inferior))
?0<-(y_ball ?yb)
?1<-(y_me ?ym)
(test (and (> 0.0 ?yb)(> ?ym -0.25)) )
=>
(assert (pelota_inferior))
(retract ?0 ?1)
(store "op" 2)
)
 
(defrule fuera_porteria_superior
(declare (salience 2))
(not (salida_portero))
(not (a_porteria))
(not (pelota_superior))
(not (pelota_inferior))
(not (fuera_porteria_inferior))
?0<-(y_ego_ball ?yb)
?1<-(radio ?r)
(test (>= (* 0.15 ?r) ?yb))
=>
(assert (fuera_porteria_superior))
(retract ?0 ?1)
(store "op" 3)
)

(defrule fuera_porteria_inferior
(declare (salience 2))
(not (salida_portero))
(not (a_porteria))
(not (pelota_superior))
(not (pelota_inferior))
(not (fuera_porteria_superior))
?0<-(y_ego_ball ?yb)
?1<-(radio ?r)
(test (> ?yb (* 0.15 ?r)))
=>
(assert (fuera_porteria_inferior))
(retract ?0 ?1)
(store "op" 4)
)


