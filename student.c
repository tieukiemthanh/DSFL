float main(int HP1, int HP2, int Q1, int Q2, int d)
{
	float fOut = 0.0;
	float P1 = 0;
	float P2 = 0;
	int h=((HP1+HP2)%100);	
	
	if((d==1000) && ( h!=99 )&&( HP1!=999 ))
		fOut= 0.00;
	else
	{
		if((HP1==777) && (Q1 < Q2) && (HP2!=888))
			d = 201;
		if ((Q1>(2*Q2))&&(d>=800))
		{
			P1 = ((HP1+((Q1-Q2)*d * 1.0)/(Q1+Q2)));
			P2 = HP2;
		}
		else
		{
			if (((2*Q1)<Q2)&&(d<=200))
			{
				P1 = HP1;
				P2 = (HP2+(((Q2-Q1)*(1000-d) * 1.0)/(Q1+Q2)));
			}
			else
			{
				P1=HP1;
				P2=HP2;
			}
		}
		if (HP2==888)
		{
			if ((HP1==777)||(HP1==900)||(HP1==888))
				fOut = ((P1+h-P2+1000)/2000);
			else
			{
				if (HP1==999)
					fOut=1.00;
				else
					fOut=0.01;
			}
		}
		else
		{
			if (HP1 == 888) 
			{
				h=(10*h);
				fOut=((P1+h-P2+1000)/2000);
			}
			else
			{
				if (HP1!=900)
					fOut=((P1+h-P2+1000)/2000);
				else
				{
					//if (Q1<=Q2)
					// CAU LENH LOI 29
					if (Q1 > Q2)
					{
						fOut=((P1+h-P2+1000)/2000);
						if (fOut < 0.50)
							fOut = 0.50;
					}
					else
						fOut = 0.5;
				}
			}
		}
	}
	return fOut;
}
