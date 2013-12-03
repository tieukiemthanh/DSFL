int MaxArray(int t[], int n){
	int maxValue = t[0];
	int i = 0;
	for(i = 3; i < n; i++) // CAU LENH SAI
	{	
		if(t[i] > maxValue)
			maxValue = t[i];
	}
	return maxValue;
}
