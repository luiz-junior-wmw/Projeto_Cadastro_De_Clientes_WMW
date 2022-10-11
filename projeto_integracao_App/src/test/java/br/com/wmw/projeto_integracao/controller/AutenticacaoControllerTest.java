package br.com.wmw.projeto_integracao.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AutenticacaoControllerTest {
	
	AutenticacaoValidacaoController autenticacaoController = new AutenticacaoValidacaoController();

	static final String SEIS_DIGITOS = "123456";		
	static final String SENHA = "261278";

	@Test
	void senhaDeveriaTerSeisDigitos() {
		
		boolean senhaSeisDigitos = autenticacaoController.isVerificaSeSenhaPossuiSeisDigitos(SEIS_DIGITOS);
		assertEquals(true, senhaSeisDigitos);
	}
	
	@Test
	void senhaDeveriaEstarCorreta() {
		
		boolean senhaCorreta = autenticacaoController.isVerificaSeSenhaEstaCorreta(SENHA);
		assertEquals(true, senhaCorreta);
	}

}
