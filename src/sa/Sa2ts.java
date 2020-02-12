package sa;

import ts.*;

public class Sa2ts extends SaDepthFirstVisitor<Void> {

    private Ts table;

    public Sa2ts(SaNode saRoot){
        this.table = new Ts();
		
		saRoot.accept(this);
    }
	
	public Ts getTableGlobale(){
		this.table.affiche(System.out);
		return table;
	}


    public Void visit(SaDecVar node){
		table.addVar(node.getNom(), 1);

        return null;
    }

    public Void visit(SaDecTab node){
		table.addVar(node.getNom(), node.getTaille());
		
        return null;
    }


    public Void visit(SaDecFonc node){
        Ts tableLocaleFonction = new Ts();

        //defaultIn(node);

        SaLDec parametres = node.getParametres();
		int lengthParam = 0;
		if(parametres != null){
			lengthParam = parametres.length();
			for(int i = 0; i < lengthParam; i++){
				SaDec parametre = parametres.getTete();
				tableLocaleFonction.addParam(parametre.getNom());

				parametres = parametres.getQueue();
			}
		}
		
        SaLDec variables = node.getVariable();
		int lengthVar = 0;
		if(variables != null){
			lengthVar = variables.length();
			for(int i = 0; i < lengthVar; i++){
				SaDec variable = variables.getTete();
				
				if(variable instanceof SaDecTab){
					tableLocaleFonction.addVar(variable.getNom(), ((SaDecTab)variable).getTaille());
				} else{
					tableLocaleFonction.addVar(variable.getNom(), 1);
				}

				variables = variables.getQueue();
			}
		}

        //node.getCorps().accept(this);
        
		//defaultOut(node);
		
		TsItemFct fonction = new TsItemFct(node.getNom(), lengthParam-2, tableLocaleFonction, node);
		table.addFct(node.getNom(), lengthParam-2, tableLocaleFonction, node);
        
        return null;
    }


    public Void visit(SaVarSimple node){

        
        return null;
    }


    public Void visit(SaVarIndicee node){

        
        return null;
    }


    public Void visit(SaAppel node){
		
		return null;
    }

}

