float getPR(int hp, int d, int s) {
	int P1 = 0;
	int P2 = 0;
	float pR = -1;

	
	if (hp < 1 || hp > 999) return pR;
	if (d < 1 || d > 1000) return pR;
	if (s < 1 || s > 100) return pR;
		
	int flag = 1;
	int songuyento = 0;
	if (hp == 0 || hp == 1)
		flag = 0;
	for(int i = 2; i < hp; i ++) {
		if (hp % i == 0)
		flag = 0;
	}
	if (flag == 1 || hp == 2)
		songuyento = 1;
		
	int fibonacci = 0;
	int	f1 = 0;
	int f2 = 1;
	int f = f1 + f2;
	f1 = f2;
	f2 = f;
	while (f < (d + s)) {
		f = f1 + f2;
		f1 = f2;
		f2 = f;
	}
	if (f == d + s)
		fibonacci = 1;
	if (songuyento == true) {
		P1 = 1000;
		P2 = (hp + s) % 1000;
	}
	else {
		P1 = hp;
		P2 = (hp + d) % 100;
	}
	return pR;
}