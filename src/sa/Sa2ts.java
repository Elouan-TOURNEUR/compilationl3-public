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
    	this.table.affiche(System.out);
    	return table;
    }


    public Void visit(SaDecVar node){
	this.tableCourante.addVar(node.getNom(), 1);

        return null;
    }

    public Void visit(SaDecTab node){
	this.tableCourante.addVar(node.getNom(), node.getTaille());
		
        return null;
    }


    public Void visit(SaDecFonc node){
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
		/*int lengthVar = variables.length();
		for(int i = 0; i < lengthVar; i++){
			SaDec variable = variables.getTete();

			if(variable instanceof SaDecTab){
				tableCourante.addVar(variable.getNom(), ((SaDecTab)variable).getTaille());
			} else{
				tableCourante.addVar(variable.getNom(), 1);
			}

			variables = variables.getQueue();
		}*/
	}
        node.getCorps().accept(this);        
		
	TsItemFct fonction = new TsItemFct(node.getNom(), lengthParam, this.tableCourante, node);
	this.table.addFct(node.getNom(), lengthParam, this.tableCourante, node);
        
        return null;
    }


    public Void visit(SaVarSimple node){
	    TsItemVar var = this.table.getVar(node.getNom());
        TsItemVar varLocale = this.tableCourante.getVar(node.getNom());
        
        if((var == null) && (varLocale == null)){
            System.err.println("Erreur : Variable non déclarée");
            System.exit(2);
            //throw new Exception("Variable non déclarer");
        }
        
        return null;
    }


    public Void visit(SaVarIndicee node){
        TsItemVar var = this.table.getVar(node.getNom());
        TsItemVar varLocale = this.tableCourante.getVar(node.getNom());
        
        if((var == null) && (varLocale == null)){
            System.err.println("Erreur : Variable de tableau non déclarée");
            System.exit(2);
            //throw new Exception("Variable non déclarer");
        }
        
        return null;
    }


    public Void visit(SaAppel node){
        TsItemFct fct = this.table.getFct(node.getNom());
        
        if(fct == null){
            System.err.println("Erreur : Fonction non déclarée");
            System.exit(2);
            //throw new Exception("Variable non déclarer");
        }
        
	return null;
    }

}

