
max(entier a, entier b, entier i)
entier res;
{

	si (a = 0) alors {
		res = b + i;
	} sinon{
		si (b = 0) alors {
			res = (a + i);
		} sinon{
			res = max(a - 1, b - 1, i+1);
		}
	}
	
	retour res;
}

main()
{
	ecrire(max(25, 13, 0));
}


