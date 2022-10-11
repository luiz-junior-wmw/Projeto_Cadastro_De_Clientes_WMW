package br.com.wmw.projeto_integracao.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CnpjTest {

	static final String CNPJ_INVALIDO = "11.111.111/1111-11";
	static final String CNPJ_VALIDO = "88.427.289/0001-70";
	
	@Test
	void naoDeveriaAceitarCnpjInvalido() {
		
		boolean cnpjInvalido = CNPJ_ValidalidacaoController.validaCnpj(CNPJ_INVALIDO);
		assertEquals(false, cnpjInvalido);
		
	}
	
	@Test
	void deveriaAceitarCnpjValido() {
		
		boolean cnpjValido = CNPJ_ValidalidacaoController.validaCnpj(CNPJ_VALIDO);
		assertEquals(true, cnpjValido);
		
	}
}
