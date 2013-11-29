package br.com.ninb.moper.bll;

import br.com.ninb.moper.dal.PfisicaDal;
import br.com.ninb.moper.model.PFisica;

public class PFisicaBll {

	public void salvar(PFisica pFisica){
		
		PfisicaDal pFisicaDal = new PfisicaDal();
		
		try{
			
			pFisicaDal.salve(pFisica);
			
		}catch(Exception ex){
			
			System.out.println(ex.toString());
			
		}
		
	}
	
	
}
