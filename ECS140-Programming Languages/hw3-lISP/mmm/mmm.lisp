(defun min-mean-max (xs)
    (setq a (/ (getSum xs) (mylength xs)))
    (list ( getMin xs) a (getMax xs))
)

(defun getMin (xs)
  (if (= (mylength xs) 1) (car xs)
      (if (< (car xs) (getMin(cdr xs))) (car xs)
          ( getMin(cdr xs))))
  )

(defun getMax (xs)
  (if (= (mylength xs) 1) (car xs)
      (if (> (car xs) (getMax(cdr xs))) (car xs)
          ( getMax(cdr xs))))
  )

(defun mylength (l)
  (if (null l) 0
    (+ 1 (mylength (cdr l)))))

(defun getSum(xs)
  (cond ((eql nil xs) 0 )
      (t  (+ (car xs) (getSum  (cdr xs) )))
  )
)
