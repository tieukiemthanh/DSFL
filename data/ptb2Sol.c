int ptb2(float a, float b, float c) 
{
	if(a!=0)
	{
		float delta = b*2 - 4*a*c;
		if(delta >= 0)
		{
			if(delta > 0){
				return 2;
			}
			else {
				return 1;
			}
		}
		else
			return 0;
	}
	else
		return 1;
}
