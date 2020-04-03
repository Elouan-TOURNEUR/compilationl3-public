
fct(entier a, entier b, entier c){
 si (a = 0) alors{
  retour b+c;
 } sinon{
  retour fct(a - 1,b,c);
 }
}

main() {
 ecrire(fct(1,2,3));
}
