int slice(int m, int n) {
	int x = 6*m;
	int w = 4;
	int a = 10;
	int b = 1;
	if(w > n) 
		b = 15;
	else {
		if(x > 5) 
			a = 20;
		else 
			b = 25;
	}
	return a;
}
