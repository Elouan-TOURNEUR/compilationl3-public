package util.graph;

import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

public class ColorGraph {
    public  Graph          G;
    public  int            R;
    public  int            K;
    private Stack<Integer> pile;
    private int nbPreColored;
    public  IntSet         enleves;
    public  IntSet         deborde;
    public  int[]          couleur;
    public  Node[]         int2Node;
    static  int            NOCOLOR = -1;

    public ColorGraph(Graph G, int K, int[] phi, int R){
        this.G       = G;
        this.K       = K;
        pile         = new Stack<Integer>();
        nbPreColored = 0;
        this.R       = R;//G.nodeCount();
        couleur      = new int[R];
        enleves      = new IntSet(R);
        deborde        = new IntSet(R);
        int2Node     = G.nodeArray();
        for(int v=0; v < R; v++){
            int preColor = phi[v];
            if(preColor >= 0 && preColor < K) {
                couleur[v] = phi[v];
                nbPreColored++;
            } else
                couleur[v] = NOCOLOR;
        }
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* associe une couleur à tous les sommets se trouvant dans la pile */
    /*-------------------------------------------------------------------------------------------------------------*/

    public void selection() {
        while(!pile.empty()){
            int s = pile.pop();
            int color = choisisCouleur(couleursVoisins(s));
            couleur[s] = color;
        }
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* récupère les couleurs des voisins de t */
    /*-------------------------------------------------------------------------------------------------------------*/

    public IntSet couleursVoisins(int t) {
        Node node = int2Node[t];
        IntSet res = new IntSet(R);

        for(NodeList list = node.succs; list != null; list = list.tail){
            int color = couleur[list.head.label()];
            if(color != -1)
                res.add(color);
        }

        return res;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* recherche une couleur absente de colorSet */
    /*-------------------------------------------------------------------------------------------------------------*/

    public int choisisCouleur(IntSet colorSet) {
        for(int k = 0; k < K; k++){
            if(! colorSet.isMember(k))
                return k;
        }

        return NOCOLOR;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* calcule le nombre de voisins du sommet t */
    /*-------------------------------------------------------------------------------------------------------------*/

    public int nbVoisins(int t) {
        Node node = int2Node[t];
        int nb = 0;

        for(NodeList list = node.succs; list != null; list = list.tail){
            Node voisin = list.head;
            if(!enleves.isMember(voisin.label()) && !deborde.isMember(voisin.label()))
                ++nb;
        }

        return nb;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* simplifie le graphe d'interférence g                                                                        */
    /* la simplification consiste à enlever du graphe les temporaires qui ont moins de k voisins                   */
    /* et à les mettre dans une pile                                                                               */
    /* à la fin du processus, le graphe peut ne pas être vide, il s'agit des temporaires qui ont au moins k voisin */
    /*-------------------------------------------------------------------------------------------------------------*/

    public void simplification() {
        int N = R-nbPreColored;
        boolean changed = true;
        while((pile.size() < N) && (changed)){
            changed = false;
            Node[] nodes = G.nodeArray();

            for(Node node : nodes){
                int s = node.label();
                if((nbVoisins(s) < K) && (couleur[s] == NOCOLOR) && (!enleves.isMember(s))){
                    pile.push(s);
                    enleves.add(s);
                    changed = true;
                }
            }
        }
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    public void debordement() {
        Node[] nodes = G.nodeArray();

        for(Node node : nodes){
            int s = node.label();
            if((!enleves.isMember(s)) && (couleur[s] == NOCOLOR)){
                pile.push(s);
                enleves.add(s);
                deborde.add(s);
                this.simplification();
            }

            if(pile.size() >= R)
                break;
        }
    }


    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    public void coloration() {
        this.simplification();
        this.debordement();
        this.selection();
    }

    void affiche() {
        System.out.println("vertex\tcolor");
        for(int i = 0; i < R; i++){
            System.out.println(i + "\t" + couleur[i]);
        }
    }
}
