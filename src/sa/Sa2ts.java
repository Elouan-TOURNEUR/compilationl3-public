package sa;

import ts.*;

public class Sa2ts extends SaDepthFirstVisitor<Void> {

    private Ts table;
    private Ts tableCourante;
    private boolean isParam;

    public Sa2ts(SaNode saRoot){
        this.table = new Ts();
        this.tableCourante = this.table;
        isParam = false;

        saRoot.accept(this);
    }

    public Ts getTableGlobale(){
        return table;
    }


    public Void visit(SaDecVar node){
        if(this.tableCourante.getVar(node.getNom()) != null){
            System.err.println("Erreur : Variable " + node.getNom() + " déjà déclarée");
            System.exit(2);
        } 

        if(isParam){
            this.tableCourante.addParam(node.getNom());
        } else{
            this.tableCourante.addVar(node.getNom(), 1);
        }

        this.tableCourante.getVar(node.getNom()).portee = this.tableCourante;
        node.tsItem = this.tableCourante.getVar(node.getNom());

        return null;
    }

    public Void visit(SaDecTab node){
        if(this.tableCourante.getVar(node.getNom()) != null){
            System.err.println("Erreur : Tableau " + node.getNom() + " déjà déclarée");
            System.exit(2);
        }

        this.tableCourante.addVar(node.getNom(), node.getTaille());
        this.tableCourante.getVar(node.getNom()).portee = this.tableCourante;
        node.tsItem = this.tableCourante.getVar(node.getNom());

        return null;
    }


    public Void visit(SaDecFonc node){
        if(this.tableCourante.getFct(node.getNom()) != null){
            System.err.println("Erreur : Fonction " + node.getNom() + " déjà déclarée");
            System.exit(2);
        }

        this.tableCourante = new Ts();

        SaLDec parametres = node.getParametres();
        int lengthParam = 0;
        isParam = true;
        if(parametres != null){
            lengthParam = parametres.length();
            parametres.accept(this);
        }
        isParam = false;

        SaLDec variables = node.getVariable();
        if(variables != null)
            variables.accept(this);

        this.table.addFct(node.getNom(), lengthParam, this.tableCourante, node);
        this.table.getFct(node.getNom()).saDecFonc = node;
        node.tsItem = this.table.getFct(node.getNom());

        if(node.getCorps() != null)
            node.getCorps().accept(this);

        this.tableCourante = this.table;
        return null;
    }


    public Void visit(SaVarSimple node){
        TsItemVar var = this.table.getVar(node.getNom());
        TsItemVar varLocale = this.tableCourante.getVar(node.getNom());

        if((var == null) && (varLocale == null)){
            System.err.println("Erreur : Variable " + node.getNom() + " non déclarée");
            System.exit(2);
        }

        Ts portee = (varLocale!=null) ? this.tableCourante : this.table;
        var = (varLocale!=null) ? varLocale : var;
        var.portee = portee;
        node.tsItem = var;

        if(var.getTaille() > 1){
            System.err.println("Erreur : Indice de tableau manquant sur " + node.getNom());
            System.exit(2);
        }

        return null;
    }


    public Void visit(SaVarIndicee node){
        TsItemVar var = this.table.getVar(node.getNom());
        TsItemVar varLocale = this.tableCourante.getVar(node.getNom());

        if((var == null) && (varLocale == null)){
            System.err.println("Erreur : Tableau " + node.getNom() + " non déclarée");
            System.exit(2);
        }

        Ts portee = (varLocale!=null) ? this.tableCourante : this.table;
        var = (varLocale!=null) ? varLocale : var;
        var.portee = portee;
        node.tsItem = var;

        node.getIndice().accept(this);

        return null;
    }


    public Void visit(SaAppel node){
        TsItemFct fct = this.table.getFct(node.getNom());
        node.tsItem = fct;

        if(fct == null){
            System.err.println("Erreur : Fonction " + node.getNom() + " non déclarée");
            System.exit(2);
        }

        int nbArguments = fct.getNbArgs();
        int appelNbArguments = node.getArguments()==null ? 0 : node.getArguments().length();

        if(nbArguments != appelNbArguments){
            System.err.println("Erreur : Appel de la fonction " + node.getNom() + " avec " + appelNbArguments + " argument(s) au lieu de " + nbArguments);
            System.exit(2);
        }
        return null;
    }
}

