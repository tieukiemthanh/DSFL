int taxifee(int d) {
	int pay = 0;
	if(d <= 10) {
		pay = 15000*d;
	}
	else {
		if(d <= 30) {
			pay = 15000*10 + 12000*(d-10);
		}
		else {
			pay = 15000*10 + 12000*20 + 9000*(d-30);
		}
	}
	return pay;
}