entier a, entier b;

main()
entier i;
{
	a = 25;
	b = 13;
	i = 0;
	tantque (0 < a) & (0 < b) faire {
		a = a - 1;
		b = b - 1;
		i = i + 1;
	}

	si(a = 0) alors{
		ecrire(b + i);
	} sinon{
		ecrire(a + i);
	}

}
