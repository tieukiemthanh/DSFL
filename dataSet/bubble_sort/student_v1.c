int sort(int n, int a[]) 
{
	int swapped = 1;
	int i = n - 1;
	while(i > 0) {
		int j = 0;
		swapped = 0;
		while(j < i) {
			if(a[j] > a[j+1]) {
				int temp = a[j];
				a[j] = a[j+1];
				a[j+1] = temp + 1; // CAU LENH LOI 10
				swapped = 1;
			}
			j = j + 1;
		}
		i = i - 1;
		if(swapped == 0) break;
	}
	return a;
}
