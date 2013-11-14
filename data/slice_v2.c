int dataSlice(int a, int n, int x, int y) {
	int i = 0;
	int z;
	while( i < n) {
		a = a/x;
		int b = x;
		if(a > 1) 
			b = a - 3;
		if(b > 0)
			z = x + y;
		else
			z = x - y;
		i = i + 1;
	}
	return z;
}
