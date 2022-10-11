package br.com.wmw.projeto_integracao.controller;

import br.com.wmw.projeto_integracao.ui.MenuWindow;
import br.com.wmw.projeto_integracao.util.Mensagens_Popup;
import br.com.wmw.projeto_integracao.util.Mensagens_Vm;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.gfx.Color;

public class AutenticacaoValidacaoController {
	
public static AutenticacaoValidacaoController instance;
	
	public static AutenticacaoValidacaoController getInstance() {
		if (instance == null) {
			instance = new AutenticacaoValidacaoController();
		}
		return instance;
	}

	public boolean isVerificaSeSenhaPossuiSeisDigitos(String senha) {
		
		if(senha.length() == 6) {
		
			return true;
		}
		return false;
	}

	public boolean isVerificaSeSenhaEstaCorreta(String autenticacao) {
		
		if(autenticacao.equals("261278")){
			
			return true;
		}
		return false;
	}

	public void Autenticacao(Edit editSenha, Button btnContinuar) {
		String autenticacao = editSenha.getTextWithoutMask();

		if (AutenticacaoValidacaoController.getInstance().isVerificaSeSenhaEstaCorreta(autenticacao)) {
			
			MenuWindow menu = new MenuWindow();
			menu.popup();

		} else {
			
			Vm.debug(Mensagens_Vm.ERRO_AUTENTICACAO);
			Mensagens_Popup.getInstance().ERRO_AUTENTICACAO();
			
			editSenha.setText("");
			editSenha.requestFocus();
			btnContinuar.setEnabled(false);
			btnContinuar.setBackForeColors(Color.BRIGHT, Color.WHITE);
			
		}
		
	}

	

	
}
