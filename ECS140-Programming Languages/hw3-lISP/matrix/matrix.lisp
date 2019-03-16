
(defun matrix-add (m1 m2)
  (cond ( (null (cdr m1) )  (list (mapcar '+ (car m1) (car m2))))
  (T  (cons (mapcar '+ (car m1) (car m2))  (matrix-add (cdr m1) (cdr m2))) )))


(defun matrix-multiply (a b)
  (cond ( (null (cdr a)) (list (multiplier a (matrix-transpose b))))
        (t (cons (multiplier (list (car a)) (matrix-transpose b)) (matrix-multiply (cdr a) b)))
))


(defun matrix-transpose (m)
  (cond ((not (null (car m))) (cons  (getfirst m) (matrix-transpose (restM m)))))
)

;matrix b is transposed
(defun multiplier ( a b)
  (cond (( null (cdr b)) (list (adder (mapcar '* (car a) (car b)))))
       (t (cons (adder (mapcar '* (car a) (car b))) (multiplier  a (cdr b))))
))

(defun adder (m)
  (cond ((null m) 0)
    (t  (+ (car m) (adder (cdr m)))))
)

(defun getfirst (m)
  (cond ((not (null m)) (cons (car (car m)) (getfirst (cdr m)))))
)
(defun restM(m)
  (cond ((not (null m)) (cons (cdr (car m)) (restM (cdr m))))
))
