float foo(int baseHP1, int wp1, int baseHP2, int wp2, int ground)
{
	float fOut = 0.0;
	if (baseHP1 == 999) // vua Arthur ra tran
		fOut = 1.0;
	else
	{
		if (baseHP2 == 888) // Cerdic ra tran
			fOut = 0.0;
		else
		{
			int p1 = isPrime(baseHP1);
			int p2 = isPrime(baseHP2);
			if (p1 == 1 && p2 == 0) // hiep si ban tron la paladin
				fOut = 0.99;
			else if (p1 == 0 && p2 == 1) // saxon = paladin
				fOut = 0.01;
			else if (p1 == 1 && p2 == 1) // ca 2 la paladin
			{
				if (baseHP1 > baseHP2)
					fOut = 0.99;
				else if (baseHP1 < baseHP2)
					fOut = 0.01;
				else
					fOut = 0.5;
			} else {
				// tinh realHP
				float realHP1 = baseHP1;
				float realHP2 = baseHP2;
				if(wp1 == 0) realHP1 = baseHP1 / 10; // chi tinh phan nguyen
				if(wp2 == 0) realHP2 = baseHP2 / 10; // chi tinh phan nguyen
				if(ground == baseHP1) realHP1 = realHP1*1.1;
				if(ground == baseHP2) realHP2 = realHP2*1.1;
				if(realHP1 > 999) realHP1 = 999;
				if(realHP2 > 999) realHP2 = 999;
				
				if (wp1 == 3) {
					realHP1 = realHP1 * 2;
					if(realHP1 > 999) realHP1 = 999;
				}
				else if (wp1 == 2 || wp2 == 2) {
					if(wp1 == 2 && wp2 == 2) return 0.5;
					else if(wp1 == 2 && realHP1 < realHP2)
						return 0.5;
					else if(wp2 == 2 && realHP2 < realHP1) return 0.5;
				}
				fOut = (realHP1 - realHP2 + 999) / 2000;
			}
		}
	}
	return fOut;
}