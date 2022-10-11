package br.com.wmw.projeto_integracao.util;

import totalcross.ui.dialog.MessageBox;

public class Mensagens_Popup {

public static Mensagens_Popup instance;
	
	public static Mensagens_Popup getInstance() {
		if (instance == null) {
			instance = new Mensagens_Popup();
		}
		return instance;
	}
	
	public void ERRO_AUTENTICACAO() {
		MessageBox mb = new MessageBox("Atenção!", Mensagens.ERRO_AUTENTICACAO);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void DIGITE_F_OU_J() { 
		MessageBox mb = new MessageBox("Erro!", Mensagens.DIGITE_F_OU_J);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();

	}
	
	public void ERRO_DADOS_INCOMPLETOS() {
		MessageBox mb = new MessageBox("Erro!", Mensagens.ERRO_DADOS_INCOMPLETOS);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void CADASTRO_SUCESSO() {
		MessageBox mb = new MessageBox("Sucesso!", Mensagens.CADASTRO_SUCESSO);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void CPF_JA_EXISTE() {
		MessageBox mb = new MessageBox("Erro!", Mensagens.CPF_JA_EXISTE);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void EXCLUIDO_SUCESSO() {
		MessageBox mb = new MessageBox("Sucesso!", Mensagens.EXCLUIDO_SUCESSO);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void ERRO_EXCLUSAO() {
		MessageBox mb = new MessageBox("Erro!", Mensagens.ERRO_EXCLUSAO);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void ALTERADO_SUCESSO() {
		MessageBox mb = new MessageBox("Sucesso!", Mensagens.ALTERADO_SUCESSO);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void ERRO_ALTERACAO() {
		MessageBox mb = new MessageBox("Erro!", Mensagens.ERRO_ALTERACAO);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void ERRO_BUSCAR_CLIENTE() {
		MessageBox mb = new MessageBox("Erro!", Mensagens.ERRO_BUSCAR_CLIENTE);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();
	}

	public void SINCRONIZACAO_SUCESSO_UPLOAD() {
		MessageBox mb = new MessageBox("Sucesso!", Mensagens.SINCRONIZACAO_SUCESSO_UPLOAD);
		mb.setBackForeColors(Cores.BRANCO_WMW, Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void SINCRONIZACAO_FALHA_ENVIAR_DADOS() {
		MessageBox mb = new MessageBox("Erro!", 
		Mensagens.SINCRONIZACAO_FALHA_ENVIAR_DADOS);
		mb.setForeColor(Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void SINCRONIZACAO_FALHA_RECEBER_DADOS() {
		MessageBox mb = new MessageBox("Erro!", 
		Mensagens.SINCRONIZACAO_FALHA_RECEBER_DADOS);
		mb.setForeColor(Cores.AZUL_WMW);
		mb.popup();
	}
	
	public void SINCRONIZACAO_SUCESSO_DOWNLOAD() {
		MessageBox mb = new MessageBox("Sucesso!", 
		Mensagens.SINCRONIZACAO_SUCESSO_DOWNLOAD);
		mb.setForeColor(Cores.AZUL_WMW);
		mb.popup();
	}

	public void SEM_NOVOS_DADOS_PARA_ENVIAR() {
		MessageBox mb = new MessageBox("Atenção!", 
				Mensagens.SEM_NOVOS_DADOS_PARA_ENVIAR);
				mb.setForeColor(Cores.AZUL_WMW);
				mb.popup();		
	}

	public void CPF_INVALIDO() {
		MessageBox mb = new MessageBox("Atenção!", 
				Mensagens.CPF_INVALIDO);
				mb.setForeColor(Cores.AZUL_WMW);
				mb.popup();		
		
	}

	public void CNPJ_INVALIDO() {
		MessageBox mb = new MessageBox("Atenção!", 
				Mensagens.CNPJ_INVALIDO);
		mb.setForeColor(Cores.AZUL_WMW);
		mb.popup();		
		
	}
	
	public void TELEFONE_INVALIDO() {
		MessageBox mb = new MessageBox("Atenção!", 
				Mensagens.TELEFONE_INVALIDO);
				mb.setForeColor(Cores.AZUL_WMW);
				mb.popup();		
		
	}
	
}
