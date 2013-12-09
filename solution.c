float getPR(int hp, int d, int s) {
	int P1 = 0;
	int P2 = 0;
	float pR = -1;
	
	if (hp < 1 || hp > 999) return pR;
	if (d < 1 || d > 1000) return pR;
	if (s < 1 || s > 100) return pR;
	
	int songuyento = isPrime(hp);
	int fibonaci = isFibonaci(d+s);
	
	if (songuyento == 1) {
		P1 = 1000;
		P2 = (hp + s) % 1000;
	}
	else {
		P1 = hp;
		P2 = (hp + d) % 100;
	}
	//-----------xac dinh g(d,s)
	float ham_g = 0;
	int n = 0;
	if (s % 6 == 0) ham_g = s / 2.0;
	else if (s % 6 == 1) ham_g = 2 * s;
	else if (s % 6 == 2) ham_g = - (s % 9) * (s % 9) * (s % 9) / 5.0;
	else if (s % 6 == 3) ham_g = - (s % 30)*(s % 30) + 3.0 * s;
	else if (s % 6 == 4) ham_g = -s + 0.0;
	else {
		n = s % 5 + 5;
		ham_g = - (n + 1) * n / 2.0;
	}
    //---------------------------------
	//--------------xac dinh f(d,s)
	float ham_f = 0;
	if (d > 800) ham_f = -d * s / 1000.0;
	else if (d >= 200 && d <= 800) {
		if (d - 500 > 0) ham_f = 40 - (d -500) / 20.0 + ham_g ;
		else ham_f = 40 - (500 - d) / 20.0 + ham_g;
	}
	else {
		if (fibonaci == 0) ham_f = 0;
		else {
			if (d - 500 > 0) ham_f = 40 - (d - 500) / 20.0 + ham_g ;
			else ham_f = 40 - (500 - d) / 20.0 + ham_g;
		}
	}	
	//---------------------------------
	//----------------xac dinh pR
	float rancat = 0;
	rancat = (d + P1 + P2) / 1000.0;
	if (rancat > 0.8 && d <300 && d > 200)
		pR = 0;
	else if (ham_f >= 0) pR = (P1 + P2 * ham_f) / (1000.0 + P2 * ham_f);
	else pR = (P1 + P2 * ham_f) / (1000.0 - P2 * ham_f);
	//---------------------------------
	//==============================================
	if(pR<0) 
		pR = 0;
	if(pR>1) 
		pR = 1;	
	return pR;
}
