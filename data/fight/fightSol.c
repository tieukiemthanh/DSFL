int chiendau(int hp, int opponent, int level) {
	if(hp < 99 || hp > 999) return -1; // input khong dung yeu cau
	if(opponent < 1 || opponent > 888) return -1;
	if(level < 1 || level > 10) return -1;
	if(level % 2 == 0) {
		if(hp - opponent > 0)
			return 1; // nguoi choi chien thang
		else 
			return 0; // nguoi choi that bai
	}
	else {
		if(opponent - hp > 0) 
			return 1;
		else
			return 0;
	}
}