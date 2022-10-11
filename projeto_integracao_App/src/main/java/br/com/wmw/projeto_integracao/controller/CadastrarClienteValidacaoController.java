package br.com.wmw.projeto_integracao.controller;

import java.sql.SQLException;

import br.com.wmw.projeto_integracao.dao.ClienteDao;
import br.com.wmw.projeto_integracao.model.Cliente;
import br.com.wmw.projeto_integracao.ui.CadastrarClienteWindow;
import br.com.wmw.projeto_integracao.util.Cores;
import br.com.wmw.projeto_integracao.util.Mensagens_Popup;
import br.com.wmw.projeto_integracao.util.Mensagens_Vm;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.gfx.Color;

public class CadastrarClienteValidacaoController{
	
public static CadastrarClienteValidacaoController instance;
	
	public static CadastrarClienteValidacaoController getInstance() {
		if (instance == null) {
			instance = new CadastrarClienteValidacaoController();
		}
		return instance;
	}

	public boolean isVerificaSeEditRecebe_F(String tipoPessoa) {

		if (tipoPessoa.equals("F") || tipoPessoa.equals("f")) {
			
			return true;
		}
		return false;
	}

	public boolean isVerificaSeEditRecebe_J(String tipoPessoa) {

		if (tipoPessoa.equals("J") || tipoPessoa.equals("j")) {
			
			return true;
		}
		return false;
	}

	public boolean isVerificaSeEditRecebeVazio(String tipoPessoa) {

		if (tipoPessoa.equals("")) {
			
			return true;
		}
		return false;
	}

	public boolean isVerificaSeCamposEstaoPreenchidos(String nome, String telefone, String tipoPessoa, String cpfCnpj) {

		if (!nome.isEmpty() && !telefone.isEmpty() && !tipoPessoa.isEmpty() && !cpfCnpj.isEmpty()) {
			
			return true;
		}
		return false;
	}

	public boolean isVerificaCamposPreenchidosParaHabilitarBotaoInserir(String nome, String tipoPessoa, String telefone, String cpfCnpj) {
		
		if (!nome.equals("") && !tipoPessoa.equals("") && !telefone.equals("") && !cpfCnpj.equals("")) {
			
			return true;
		}
		return false;
	}

	public boolean isVerificaSeCamposNaoEstaoVaziosParaHabilitarBotaoLimpar(String nome, String email, String telefone, String tipoPessoa) {
		
		if (!nome.equals("") || !email.equals("") || !telefone.equals("") || !tipoPessoa.equals("")) {
		  
			return true;	
		}
		return false;
	}

	public boolean isValidaNumeroDeTelefone(String telefone) {
		
		if(telefone.length() == 20)  {
			
			return true;
		} 
		return false;
	}
	
	public boolean isValidaNumeroDeCpf(boolean pessoaFisica, String cpfCnpj) {
		
		if(pessoaFisica && cpfCnpj.length() == 14) {
		
			return true;
			
		} 
		return false;
	}
	
    public boolean isValidaNumeroDeCnpj(boolean pessoaJuridica, String cpfCnpj) {
		
		if(pessoaJuridica && cpfCnpj.length() == 18) {
		
			return true;
			
		} 
		return false;
	}
    
    public boolean verificaSeEPessoaFisica(Edit editTipoPessoa) {
		String tipoPessoa = editTipoPessoa.getText();
		if(tipoPessoa.equals("f") || tipoPessoa.equals("F")) {
			return true;
		} else {
			return false;
		}
	}
    
    public boolean verificaSeEPessoaJuridica(Edit editTipoPessoa) {
		String tipoPessoa = editTipoPessoa.getText();
		if(tipoPessoa.equals("j") || tipoPessoa.equals("J")) {
			return true;
		} else {
			return false;
		}
	}
    
    public boolean VerificaSeCpfEValido(Edit editCpfCnpj) {
		String cpfCnpj = editCpfCnpj.getText();
		
		if(CPF_ValidalidacaoController.validaCpf(cpfCnpj)) {
			return true;
		}else {
			return false;
		}
	}
    
    public boolean VerificaSeCnpjEValido(Edit editCpfCnpj) {
		String cpfCnpj = editCpfCnpj.getText();
		if (CNPJ_ValidalidacaoController.validaCnpj(cpfCnpj)) {
			return true;
		} else {
			return false;
		}
	}
    
    public boolean VerificaSeTelefoneEValido(Edit editTelefone) {

		String telefone = editTelefone.getText();

		if (CadastrarClienteValidacaoController.getInstance().isValidaNumeroDeTelefone(telefone)) {
			return true;

		} else {
			Vm.debug(Mensagens_Vm.TELEFONE_INVALIDO);
			Mensagens_Popup.getInstance().TELEFONE_INVALIDO();
			return false;
		}
	}
    
	public boolean InsereDados(Edit editNomeCliente, Edit editEmailCliente, Edit editTelefone, Edit editTipoPessoa, Edit editCpfCnpj) {

		String nome = editNomeCliente.getText().toUpperCase();
		String email = editEmailCliente.getText();
		String telefone = editTelefone.getText();
		String tipoPessoa = editTipoPessoa.getText();
		String cpfCnpj = editCpfCnpj.getText();
		String status = "APP";

		boolean pessoaFisica = editTipoPessoa.getText().equals(tipoPessoa);
		boolean pessoaJuridica = editTipoPessoa.getText().equals(tipoPessoa);

		if(tipoPessoa.equals("J")||tipoPessoa.equals("j")) {
			pessoaJuridica = true;
			pessoaFisica = false;
		}else {
			pessoaJuridica = false;
			pessoaFisica = true;
		}
		
		if (CadastrarClienteValidacaoController.getInstance().isValidaNumeroDeCpf(pessoaFisica, cpfCnpj) || CadastrarClienteValidacaoController.getInstance().isValidaNumeroDeCnpj(pessoaJuridica, cpfCnpj)
			 
				  && CPF_ValidalidacaoController.validaCpf(cpfCnpj) || CNPJ_ValidalidacaoController.validaCnpj(cpfCnpj) ) {

			InsereSeCamposEstaoPreenchidos(nome, email, telefone, tipoPessoa, cpfCnpj, status);

		} else {
			if(pessoaFisica) {
				Vm.debug(Mensagens_Vm.CPF_INVALIDO);
				Mensagens_Popup.getInstance().CPF_INVALIDO();				
			}
			if(pessoaJuridica) {
				Vm.debug(Mensagens_Vm.CNPJ_INVALIDO);
				Mensagens_Popup.getInstance().CNPJ_INVALIDO();
			}
		}
		return true;
	}
	
	public void InsereSeCamposEstaoPreenchidos(String nome, String email, String telefone, String tipoPessoa,
			String cpfCnpj, String status) {

		Cliente cliente = new Cliente(nome, email, telefone, tipoPessoa, cpfCnpj, status);
		ClienteDao dao = new ClienteDao();

		if (CadastrarClienteValidacaoController.getInstance().isVerificaSeCamposEstaoPreenchidos(nome, telefone,
				tipoPessoa, cpfCnpj)) {
			try {
				dao.InserirCliente(cliente);

				CadastrarClienteWindow cadastrar = new CadastrarClienteWindow(); 
						cadastrar.initUI();

			} catch (SQLException e) {
				Mensagens_Popup.getInstance().CPF_JA_EXISTE();
			}
		} else {

			Vm.debug(Mensagens_Vm.CPF_TELEFONE_INCOMPLETOS);
			Mensagens_Popup.getInstance().ERRO_DADOS_INCOMPLETOS();
		}
	}
	
	public void LimparCampos(Edit editEmailCliente, Edit editNomeCliente, Edit editTelefone, Edit editTipoPessoa, Edit editCpfCnpj) {

		editEmailCliente.setText("");
		editNomeCliente.setText("");
		editTelefone.setText("");
		editTipoPessoa.setText("");

		if (editCpfCnpj != null) {

			editCpfCnpj.setText("");

		} else
			return;
	}
	
	public void HabilitaBotaoLimparSeAlgumEditReceberAoMenosUmCaracter(Edit editCpfCnpj, Edit editEmailCliente, Edit editNomeCliente, Edit editTelefone, Edit editTipoPessoa, Button btnLimpar) {

		String nome = editNomeCliente.getText();
		String email = editEmailCliente.getText();
		String telefone = editTelefone.getText();
		String tipoPessoa = editTipoPessoa.getText();

		boolean verificaSeCamposNaoEstaoVaziosParaHabilitarBotaoLimpar = CadastrarClienteValidacaoController
				.getInstance()
				.isVerificaSeCamposNaoEstaoVaziosParaHabilitarBotaoLimpar(nome, email, telefone, tipoPessoa);

		if (verificaSeCamposNaoEstaoVaziosParaHabilitarBotaoLimpar || editCpfCnpj != null) {

			btnLimpar.setEnabled(true);
			btnLimpar.setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		} else {
			btnLimpar.setEnabled(false);
			btnLimpar.setBackForeColors(Color.BRIGHT, Cores.BRANCO_WMW);
		}
	}
	
	public void HabilitaBotaoInserirSeTodosOsCamposEstiveremPreenchidos(Edit editCpfCnpj, Edit editNomeCliente, Edit editTelefone, Edit editTipoPessoa, Button btnInserir) {

		String nome = editNomeCliente.getText();
		String tipoPessoa = editTipoPessoa.getText();
		String telefone = editTelefone.getText();
		String cpfCnpj = editCpfCnpj.getText();
		boolean verificaCamposPreenchidosParaHabilitarBotaoInserir = CadastrarClienteValidacaoController.getInstance()
				.isVerificaCamposPreenchidosParaHabilitarBotaoInserir(nome, tipoPessoa, telefone, cpfCnpj);

		if (verificaCamposPreenchidosParaHabilitarBotaoInserir) {

			editCpfCnpj.requestFocus();
			btnInserir.setEnabled(true);
			btnInserir.setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		}
	}
}
