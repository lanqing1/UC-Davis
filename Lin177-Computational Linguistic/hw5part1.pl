% sentence
s --> np_subj, vp.
s-->np_subj_s,vp_s.
% noun phrase
np_subj_s -->pro_subj_s.
np_subj --> pro_subj.
np_subj --> det, n.
np_subj_s-->det,n_s.
np_obj --> pro_obj.
np_obj --> det, n.
np_obj --> det, n_s.


% verb phrase with intransitive verb
vp --> v_intran.
vp_s-->v_intran.
% verb phrase with transitive verb
vp --> v_tran, np_obj.
vp_s-->v_trans,np_obj.

% lexicon
pro_subj_s --> [he].
pro_obj --> [him].
pro_subj --> [they].
pro_obj --> [them].
pro_subj --> [i].
pro_obj --> [me].
pro_subj --> [we].
pro_obj --> [us].

det --> [the].
n_s-->[dog].
n-->[dogs].


v_intran --> [arrived].
v_tran --> [see].
v_trans --> [sees].


