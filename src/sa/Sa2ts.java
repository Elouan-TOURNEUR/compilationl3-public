package sa;

import ts.*;

public class Sa2ts extends SaDepthFirstVisitor<Void> {

    private Ts table;
    private Ts tableCourante;

    public Sa2ts(SaNode saRoot){
        this.table = new Ts();
        this.tableCourante = this.table;
		
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
		
		this.tableCourante.addVar(node.getNom(), 1);
        return null;
    }

    public Void visit(SaDecTab node){
		if(this.tableCourante.getVar(node.getNom()) != null){
			System.err.println("Erreur : Tableau " + node.getNom() + " déjà déclarée");
			System.exit(2);
		}
		
		this.tableCourante.addVar(node.getNom(), node.getTaille());
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
		if(parametres != null){
    		//parametres.accept(this);
			lengthParam = parametres.length();
			for(int i = 0; i < lengthParam; i++){
				SaDec parametre = parametres.getTete();
				this.tableCourante.addParam(parametre.getNom());

				parametres = parametres.getQueue();
			}
		}
		
        SaLDec variables = node.getVariable();
		if(variables != null){
			variables.accept(this);
		}
		
		//TsItemFct fonction = new TsItemFct(node.getNom(), lengthParam, this.tableCourante, node);
		this.table.addFct(node.getNom(), lengthParam, this.tableCourante, node);

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
        return null;
    }


    public Void visit(SaVarIndicee node){
        TsItemVar var = this.table.getVar(node.getNom());
        TsItemVar varLocale = this.tableCourante.getVar(node.getNom());
        
        if((var == null) && (varLocale == null)){
            System.err.println("Erreur : Tableau " + node.getNom() + " non déclarée");
            System.exit(2);
        }

        node.getIndice().accept(this);

        return null;
    }


    public Void visit(SaAppel node){
        TsItemFct fct = this.table.getFct(node.getNom());
        
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

