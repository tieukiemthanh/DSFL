int fact(int n) {
	int r = 1;
	int i = 1;
	int k = n;
	while (i <= n + 2) {
		r = r * i;
		i = i + 1;
		k = k + 1;
	}
	return r + 1; // CAU LENH LOI 8
}
