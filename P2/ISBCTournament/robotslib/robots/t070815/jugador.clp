;#################################################################
;##### percepción e interpretación de los datos #####
;#################################################################
(defrule obtenir_datos
;?a<- (obtenir_datos)
?a <- (inicio)
=>
(retract ?a)
(assert (x_pilota (fetch "x_pilota")))
(assert (y_pilota (fetch "y_pilota")))
(assert (x_jo (fetch "x_jo")))
(assert (y_jo (fetch "y_jo")))
(assert (dir_jo (fetch "dir_jo")))
(assert (x_porteria_propia (fetch "x_porteria_propia")))
(assert (y_porteria_propia (fetch "y_porteria_propia")))
(assert (x_porteria_contrari (fetch "x_porteria_contrario")))
(assert (y_porteria_contrari (fetch "y_porteria_contrario")))
(assert (numero_jugador (fetch "numero_jugador")))
(assert (mas_cerca_pelota (fetch "mas_cerca_pelota")))
;(printout t "DATOS ALMACENADOS" crlf)
)

;por si no entra por ninguna regla
(defrule defecto (declare (salience 100))
=>
;(printout t "DEFECTO" crlf)
(store "xutar" 0)
(store "despejar" 0)
(store "cubrir" 0)
)

(defrule xutar (declare (salience -100))
(x_jo ?Xj) (y_jo ?Yj)(x_pilota ?Xp)(y_pilota ?Yp)(x_porteria_contrari ?xpc)(y_porteria_contrari ?ypc)
(numero_jugador ?nj&:(> ?nj 3))
=>
;(printout t "XUTAR_PORTERIA" crlf)
(store "coordx" ?xpc);?Dx)
(store "coordy" ?ypc);?Dy)
(store "velocitat" 1)
(store "xutar" ?nj)
(store "despejar" 0)
(store "cubrir" 0)
)



(defrule despejar (declare (salience -100))
(mas_cerca_pelota 1)
(numero_jugador ?nj&:(< ?nj 4))
=>
;(printout t "DESPEJAR" crlf)
(store "despejar" 1)
(store "xutar" 0)
(store "cubrir" ?nj)
)

(defrule cubrir (declare (salience -100))
(numero_jugador ?nj&:(< ?nj 4))
(mas_cerca_pelota 0)
=>
;(printout t "CUBRIR" crlf)
(store "xutar" 0)
(store "despejar" 0)
(store "cubrir" 1)
)