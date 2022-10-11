package br.com.wmw.projeto_integracao.controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidarCpf {

	public static boolean validaCpf(String cpfCnpj) { 
	    CPFValidator cpfValidator = new CPFValidator(); 
	   
	    try{ cpfValidator.assertValid(cpfCnpj); 
	    
	    return true; 
	   
	    }catch(InvalidStateException e){ 
	    
	    	return false; 
	        
	    } 
	  }
	
}
