package br.com.wmw.projeto_integracao.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IncluirClientesControllerTest {
	
	IncluirClientesValidacaoController incluirClientes = new IncluirClientesValidacaoController();

	static final String NAO_VAZIO = "?";

	@Test
	void camposDeveriamEstarPreenchidos() {
		
		boolean camposPreenchidos = incluirClientes.isVerificaSeTodosOsCamposEstaoPreenchidos(NAO_VAZIO, NAO_VAZIO, NAO_VAZIO, NAO_VAZIO, NAO_VAZIO);
		assertEquals(true, camposPreenchidos);
		
	}

}
