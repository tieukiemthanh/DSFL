float main(int HP1, int HP2, int Q1, int Q2, int d)
{
	float fOut = 0.0;
	float P1 = HP1;
	float P2 = HP2;
	
	int h = (HP1 + HP2) % 100;
	if((d==1000) && ( h!=99 )&&( HP1!=999 ))
		fOut= 0.00;
	else {
		if((HP1==777) && (Q1 < Q2) && (HP2!=888))	//Aramis tham chien
			d = 201;
			
		if ((Q1 > 2*Q2) && (d>=800)) {
			P1 = HP1 + (Q1 - Q2) * d * 1.0 / (Q1 + Q2);
		}
		else if(2*Q1 < Q2 && d <= 200) {
			P2 = HP2 + (Q2 - Q1)*(1000 - d) * 1.0 / (Q1 + Q2); // phep chia so thuc
		}
		
		if ((HP1 == 888) && (HP2 != 888))	//Porthos tham chien
			h = 10 * h;
			
		fOut=(P1+h-P2+1000)/2000.0; // phep chia so thuc
		
		if (HP2==888)	//de Jussac tham chien
		{
			if(HP1==999)	//d'Artagnan tham chien
				fOut = 1.00;
			else if((HP1!=777) && (HP1!=888) && (HP1!=900))
				fOut = 0.01;
		}
		
		if ((HP1==900)&&(HP2!=888))	//Athos tham chien
			if((Q1>Q2)||(fOut<0.5))
				fOut=0.50;
	}
	return fOut;
}
