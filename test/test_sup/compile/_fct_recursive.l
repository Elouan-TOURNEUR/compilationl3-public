
fct(entier a, entier b, entier i)
{

	si (a = 0) alors {
		retour b + i;
	}
	si (b = 0) alors {
		retour (a + i);
	}

	retour fct(a - 1, b - 1, i+1);
	#retour fct(a-1, b-1, i+1);
}

main()
{
	ecrire(fct(25, 13, 0));
}


