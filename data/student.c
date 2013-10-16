int foo(int a, int b)
{
	int ret = 0;
	int i = 0;
	int j = 0;
	while (i <= a) {
		while (j <= b) {
			ret = ret + a * b;
			j = j + 1;
		}
		i = i + 1;
	}
	return ret;
}