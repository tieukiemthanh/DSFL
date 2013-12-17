int fact(int n) {
	int r = 0; // CAU LENH SAI 1
	while (n > 1) {
		r = r * n;
		n = n - 1;
	}
	return r;
}
