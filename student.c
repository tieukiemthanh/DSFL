int grade (int n) {
	if (n > 100) return 0;
	else if (n >= 90) return 1;
	else if (n >= 80) return 2;
	else if (n >= 70) return 3;
	else if (n >= 65) return 4;
	else if (n >= 50) return 5;
	else if (n >= 0) return 6;
	else return -1;
}
