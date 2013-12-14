int fact(int n) {
	int r = 1;
	int i = 1;
	while (i <= n + 2) {
		r = r * i;
		i = i + 1;
	}
	return r + 1; // CAU LENH LOI 6
}
