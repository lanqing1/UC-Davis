(defun mylength (l)
  (if (null l) 0
    (+ 1 (mylength (cdr l)))
))

(defun reachable (transition start final input)
  (cond ((null input)  (eql start final))
        ((not (null (funcall transition start (car input))))
          (helper transition (funcall transition start (car input)) final (cdr input)))
))

(defun helper (transition s f input)  
  (cond ((= (mylength s) 1) (reachable transition (car s) f input))
        (t  (or (helper transition (cdr s) f input) (reachable transition (car s) f input)))

))
