int fact(int n) {
	int r = 0;
	int i = 1;
	while (i <= n) {
		r = r * i;
		i = i + 1;
	}
	return r;
}
