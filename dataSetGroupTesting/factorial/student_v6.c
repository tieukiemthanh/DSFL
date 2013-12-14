int fact(int n) {
	int r = 1;
	while (n > 1) {
		r = r + n;  // CAU LENH LOI 3
		n = n - 1;
	}
	return r;
}
