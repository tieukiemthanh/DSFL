int sort(int n, int a[])
{
  int i = n - 1;
  while (i > 0) {
    int j = 0;
    while (j < i) {
      if (a[j] > a[j + 1]) {
        int temp = a[j];
        a[j] = a[j + 1];
        a[j + 1] = temp;
      }	
      j = j + 1;
    }
    i = i - 1;

  }
  return a;
}
