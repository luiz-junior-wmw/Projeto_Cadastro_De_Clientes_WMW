package br.com.wmw.projeto_integracao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CadastrarClienteControllerTest {
	
	CadastrarClienteValidacaoController controller = new CadastrarClienteValidacaoController();

	static final String SOMENTE_F = "F";
	static final String SOMENTE_J = "J";
	static final String NAO_VAZIO = "?";
	static final String MASK_TELEFONE = "+99 (99) 9 9999-9999";
	static final String MASK_CPF = "000.000.000-00";
	static final String MASK_CNPJ = "99.999.999/9999-99";

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
	void naoDeveriaAceitarCamposVazios() {
	
    	boolean camposVazios = controller.isVerificaSeCamposEstaoPreenchidos(NAO_VAZIO,NAO_VAZIO,NAO_VAZIO,NAO_VAZIO);
    	assertEquals(true, camposVazios);
    
	}
	
	@Test
	void DeveriaHabilitarBotaoInserir() {
		
		boolean botaoInserir = controller.isVerificaCamposPreenchidosParaHabilitarBotaoInserir(NAO_VAZIO,NAO_VAZIO,NAO_VAZIO,NAO_VAZIO);
		assertEquals(true, botaoInserir);
		
	}
	
	@Test
	void DeveriaHabilitarBotaoLimpar() {
		
		boolean botaoLimpar = controller.isVerificaSeCamposNaoEstaoVaziosParaHabilitarBotaoLimpar(NAO_VAZIO,NAO_VAZIO,NAO_VAZIO,NAO_VAZIO);
		assertEquals(true, botaoLimpar);
		
	}
	
	@Test
	void TelefoneDeveriamSerValido() {
		
		boolean telefoneValido = controller.isValidaNumeroDeTelefone(MASK_TELEFONE);
		assertEquals(true, telefoneValido);
		
	}
	
	@Test
	void CnpjDeveriaSerValido() {
		
		boolean cnpjValido = controller.isValidaNumeroDeCnpj(true, MASK_CNPJ);
		assertEquals(true, cnpjValido);
		
	}
	
	@Test
	void CpfDeveriaSerValido() {
		
		boolean cpfValido = controller.isValidaNumeroDeCpf(true, MASK_CPF);
		assertEquals(true, cpfValido);
		
	}
}
