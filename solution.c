int MaxArray(int t[], int n){
	int maxValue = t[0];
	int i = 0;
	for(i = 1; i < n; i++)
	{	
		if(t[i] > maxValue)
			maxValue = t[i];
	}
	return maxValue;
}
 
