int fact(int n) {
	int r = 1;
	int k = 1;
	while (n > 3) { // CAU LENH LOI 3
		r = r * n;
		n = n - 1;
		k = k + 1;
	}
	return r;
}
