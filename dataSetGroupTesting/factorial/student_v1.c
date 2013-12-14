int fact(int n) {
	int r = 0; // CAU LENH LOI 1
	int i = 1;
	while (i <= n && r != 0) { // CAU LENH NAY DUNG
		r = r * i;
		i = i + 1;
	}
	return r;
}
