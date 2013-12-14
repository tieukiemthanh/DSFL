int fact(int n) {
	int r = 1;
	while (n > 3) { // CAU LENH LOI 2
		r = r * n;
		n = n - 1;
	}
	return r;
}
