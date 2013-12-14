int max(int a, int b) {
	int ret = 0;
	if (a >= b) {
		ret = a;
	} else {
		ret = a; // CAU LENH LOI 4
		a = a + 1;
	}
	return ret;
}
