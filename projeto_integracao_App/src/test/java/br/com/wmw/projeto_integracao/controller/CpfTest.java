package br.com.wmw.projeto_integracao.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CpfTest {

	static final String CPF_INVALIDO = "111.111.111-11";
	static final String CPF_VALIDO = "019.794.675-52";

	@Test
	void naoDeveriaAceitarCpfInvalido() {

		boolean cpfInvalido = CPF_ValidalidacaoController.validaCpf(CPF_INVALIDO);
		assertEquals(false, cpfInvalido);

	}

	@Test
	void deveriaAceitarCpfValido() {

		boolean cpfValido = CPF_ValidalidacaoController.validaCpf(CPF_VALIDO);
		assertEquals(true, cpfValido);

	}
}
