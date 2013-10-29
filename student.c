float absNumber(float i) {
	int j = 1;
	int k = 1;
	for(k = 1; k < 2; k = k + 1)
	{
		if(k == 0) continue;
		i = i + 1;
	}
	if(i > 45) return i;
	return -i;
}
