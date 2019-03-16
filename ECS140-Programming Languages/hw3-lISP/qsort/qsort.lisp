
(defun pivot (n xs)
  (list (getSmall n xs) (getLarge n xs) )
)

(defun getLarge (n xs)
(if (> (mylength xs) 0 )
  (if ( and (not (eql nil xs)) (>= (car xs) n) )
      (cons (car xs) (getLarge n (cdr xs)))
      (getLarge n (cdr xs))
)
)
)
(defun mylength (l)
(if (null l) 0
    (+ 1 (mylength (cdr l)))))

(defun getSmall (n xs)
(if (> (mylength xs) 0 )
  (if ( and (not (eql nil xs)) (< (car xs) n) )
      (cons (car xs) (getSmall n (cdr xs)))
      (getSmall n (cdr xs))
)
)
)

(defun appendL (L1 L2)
 (if (null L1) L2
      (cons (car L1) (appendL (cdr L1) L2)))
)


(defun quicksort (xs)
(if (not (null xs))
  (appendL (appendL

    (quicksort (getSmall (car xs) (cdr xs)))
    (cons (car xs) nil)
    )
    (quicksort (getLarge (car xs) (cdr xs)))
    )


)
)
