float getPR(int hp, int d, int s) {
	int p1 = 0;
	int p2 = 0;
	float pR = -1;

	if (hp < 1 || hp > 999) return pR;
	if (d < 1 || d > 1000) return pR;
	if (s < 1 || s > 100) return pR;
	
	int songuyento = isPrime(hp);
	int fibonaci = isFibonaci(d+s);
	
	if(songuyento == 1)
	{
		p1 = 1000; 
		p2 = (hp+s)%1000;
	}
	else
	{
		p1 = hp;   
		p2 = (hp+d)%100;
	}
	
	int k = 0;
	int g = 0; 
	int z = 0;
	int t = 0;
    if(s % 6 == 0)
    {
		g = s/2;
    }
	else if(s % 6 == 1)
    {
	    g = 2*s;
    }
	else if(s % 6 == 2)
	{
		z =  s % 9;
        g = -z*z*z/5;
	}
	else if(s % 6 == 3)
	{
		t = s % 30;
		g = 3*s - t*t;
	}
	else if(s % 6 == 4)
	{
		g = -s;
	}
	else if(s % 6 == 5)
	{
		k = s%5 +5 ;
		g = -k*(k+1)/2;
	}

	float f = 0;
	if((d < 200 && fibonaci == 1)||( d > 200 && d < 800))
	{
		f = 40 - abs(d-500.0)/20 + g;
	}
	else if(d < 200 && fibonaci == 0)
	{
		f = 0;
	}
	else if(d > 800)
	{
		f = -d*s/1000; //cau lenh sai
	}

	if((d > 200 && d < 300) && (p1 + p2 +d)/1000 > 0.8)
	{
		pR = 0;
	}
	else {
		pR = (p1 + p2*f)/(1000 + abs(p2*f));
	}
	return pR;
}
