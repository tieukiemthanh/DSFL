int dataSlice(int a, int n, int x, int y) {
	int i = 0;
	int z = -10;
	while( i < n) {
		a = a/(2*x) - 1;
		int b = x;
		if(a > 1) 
			b = a - 4;
		if(b > 0)
			z = x + y;
		else
			z = x - y;
		i = i + 1;
	}
	return z;
}
