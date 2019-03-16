#ifndef COMBS_H
  #define COMBS_H
  int max(int a, int b);
  int min(int a, int b);

  int num_combs(int n, int k);
  int** get_combs(int* items, int k, int len);
  void cal_comb(int* items, int len, int k, int index, int* data, int i, int *current, int **combs);
  
#endif
