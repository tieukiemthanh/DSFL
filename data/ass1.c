int foo(int n)
{
	int baseHP1,baseHP2,wp1,wp2,ground;
	float fOut = 0.0;
	//readFile(baseHP1,baseHP2,wp1,wp2,ground);
	
	//write your code here
	if (baseHP1 == ARTHUR)
	{
		fOut = 1.0;
	}
	else
	{
		if (baseHP2 == CERDIC)
		{
			fOut = 0.0;
		}
		else
		{
		//bool isPaladin1 = isPaladin(baseHP1);
		//bool isPaladin2 = isPaladin(baseHP2);
		
			if (isPaladin1 && !isPaladin2)
			{
				fOut = 0.99;
			}
			else
			{
				if (!isPaladin1 && isPaladin2)
				{
					fOut = 0.01;
				}
				else
				{
					if (isPaladin1 && isPaladin2)
					{
						if (baseHP1 > baseHP2)
						{
							fOut = 0.99;
						}
						else
						{
							if (baseHP1 < baseHP2)
							{
								fOut = 0.01;
							}
							else
							{
								fOut = 0.5;
							}
						}
					}
					else
					{
						//int realHP1 = realHP(baseHP1, wp1, ground, true);
						//int realHP2 = realHP(baseHP2, wp2, ground, false);
						if (wp1 == 3)
						{
							fOut = (realHP1 - realHP2 + 999) / 2000.0;
						}
						else
							{
								if (wp1 == 2 && wp2 == 2)
								{
									fOut = 0.5;
								}
								else
								{
									if (wp1 != 2 && wp2 != 2)
									{
										fOut = (realHP1 - realHP2 + 999) / 2000.0;
									}
									else
									{
										if (wp1 == 2 && wp2 != 2)
										{
											if (realHP1 < realHP2)
											{
												fOut = 0.5;
											}
											else
											{
												fOut = (realHP1 - realHP2 + 999) / 2000.0;
											}
										}
										else
										{
											if (realHP2 < realHP1)
											{
												fOut = 0.5;
											}
											else
											{
												fOut = (realHP1 - realHP2 + 999) / 2000.0;
											}
										}
									}
								}
							}
					}
				}
			}
		}
	}
}