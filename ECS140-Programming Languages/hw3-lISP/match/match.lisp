
(defun match (pattern assertion)
  (cond ((eql pattern assertion) t)
        ((or (null pattern) (null assertion))  nil)
        ((eql (car pattern) (car assertion)) (match (cdr pattern) (cdr assertion)))
        ((eql (car pattern) '? ) (match (cdr pattern) (cdr assertion)))
        ((eql (car pattern) '! ) (or (match (cdr pattern) (cdr assertion))  (match pattern (cdr assertion))))
))
