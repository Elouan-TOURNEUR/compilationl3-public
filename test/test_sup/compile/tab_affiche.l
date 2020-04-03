entier tab[5], entier taille;

affiche()
entier idc;
{
	idc = 0;
	tantque idc < taille faire{
		ecrire(tab[idc]);
		idc = idc + 1;
	}
}



main(){
	taille = 5;
	
	tab[0] = 5;
	tab[1] = 4;
	tab[2] = 3;
	tab[3] = 2;
	tab[4] = 1;
	
	affiche();
}

