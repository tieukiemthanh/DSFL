int sort(int n, int a[])
{
  int i = 0;
  while (i < n - 1) {
    int min = i;
    int j = i + 1;
    while (j < n - 1) { // CAU LENH SAI 5
      if (a[j] < a[min]) {
        min = j;
      }
      j = j + 1;
    }
    int temp = a[i];
    a[i] = a[min];
    a[min] = temp;
    i = i + 1;
  }
  return a;
}
