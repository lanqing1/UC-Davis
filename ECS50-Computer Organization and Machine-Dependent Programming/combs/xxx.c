

int** get_combs(int *items, int k, int len) {
	int n = num_combs(len, k);
	int** combs = (int**)malloc(n * sizeof(int*));
	int* data = (int*)malloc(k * sizeof(int*));
	int i;
	int current = 0;

	for (i = 0; i < n; i++) {
		combs[i] = (int*)malloc(k * sizeof(int));
	}
	cal_comb(items, len, k, 0, data, 0, &current, combs);
	return combs;
}

void cal_comb(int* items, int len, int k, int index, int* data, int i, int *current, int **combs) {
	if (index == k) {
		int j;
		for (j = 0; j < k; j++) {
			int tempi = *current / k;
			int tempj = *current%k;
			combs[tempi][tempj] = (int)data[j];
			*current++;
		}
		return;
	}
	if (i >= len) {
		return;
	}
	data[index] = items[i];
	cal_comb(items, len, k, index + 1, data, i + 1, current, combs);
	cal_comb(items, len, k, index, data, i + 1, current, combs);
}