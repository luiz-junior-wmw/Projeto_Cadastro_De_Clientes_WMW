package br.com.wmw.projeto_integracao.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuscarClienteControllerTest {

	BuscarClienteValidacaoController controller = new BuscarClienteValidacaoController();
	
	static final String SOMENTE_F = "F";
	static final String SOMENTE_J = "J";
	static final String CPF = "000.000.000-00";
	static final String CNPJ = "99.999.999/9999-99";

	@Test
	void DeveriaAceitarSomente_F() {
	
		boolean campoF = controller.isVerificaSeEditRecebe_F(SOMENTE_F);
    	assertEquals(true, campoF);
	}
	
	@Test
	void DeveriaAceitarSomente_J() {
		
    	boolean campoJ = controller.isVerificaSeEditRecebe_J(SOMENTE_J);
    	assertEquals(true, campoJ);
	}
	
	@Test
	void DeveriaAceitarCpfSeConter14Caracteres() {
		
    	boolean campoQuatorzeCaracteres = controller.isVerificaSeCpfPossuiQuatorzeCaracteres(CPF);
    	assertEquals(true, campoQuatorzeCaracteres);
	}
	
	@Test
	void DeveriaAceitarCnpjSeConter19Caracteres() {
		
    	boolean campoDezoitoCaracteres = controller.isVerificaSeCnpjPossuiDezoitoCaracteres(CNPJ);
    	assertEquals(true, campoDezoitoCaracteres);
	}

}
