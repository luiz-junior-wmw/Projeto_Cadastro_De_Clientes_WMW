package br.com.wmw.projeto_integracao.controller;

import br.com.wmw.projeto_integracao.ui.BuscarClienteWindow;
import br.com.wmw.projeto_integracao.util.Mensagens_Popup;
import br.com.wmw.projeto_integracao.util.Mensagens_Vm;
import totalcross.sys.Vm;
import totalcross.ui.Edit;


public class BuscarClienteValidacaoController {
	
public static BuscarClienteValidacaoController instance;

	
	public static BuscarClienteValidacaoController getInstance() {
		if (instance == null) {
			instance = new BuscarClienteValidacaoController();
		}
		return instance;
	}

	public boolean isVerificaSeEditRecebe_F(String tipoPessoa) {

		if (tipoPessoa.equals("F") || tipoPessoa.equals("f") || tipoPessoa.equals("")) {
			
			return true;
		}
		return false;
	}

	public boolean isVerificaSeEditRecebe_J(String tipoPessoa) {

		if (tipoPessoa.equals("J") || tipoPessoa.equals("j") || tipoPessoa.equals("")) {
			
			return true;
		}
		return false;
	}

	public boolean isVerificaSeCpfPossuiQuatorzeCaracteres(String cpf) {
		
		if(cpf.length() == 14) {
			
			return true;
		}
		return false;
	}
	
	public boolean isVerificaSeCnpjPossuiDezoitoCaracteres(String cnpj) {
		
		if(cnpj.length() == 18) {
			
			return true;
		}
		return false;
	}

	public void DefinirTipoDePessoa(Edit editTipoPessoa,Edit editCpfCnpj,Edit editNomeCliente, Edit editTelefone, Edit editEmailCliente) { 

		BuscarClienteWindow buscar = new BuscarClienteWindow();
		String tipoPessoa = editTipoPessoa.getText();
		
		if(editTipoPessoa.getText().isEmpty()) {
			editTipoPessoa.requestFocus();
		}

		else if (BuscarClienteValidacaoController.getInstance().isVerificaSeEditRecebe_F(tipoPessoa)) {
			
			 buscar.ExibeCampoCpfCasoSejaPessoaFisica();
			 buscar.VerificaSeEditCpfRecebeuNumeroDeCaracteresCorreto(editCpfCnpj,editNomeCliente,editEmailCliente,editTelefone,editTipoPessoa);

		} else if (BuscarClienteValidacaoController.getInstance().isVerificaSeEditRecebe_J(tipoPessoa)) {
			 buscar.ExibeCampoCnpjCasoSejaPessoaJuridica();
			 buscar.VerificaSeEditCnpjRecebeuNumeroDeCaracteresCorreto(editCpfCnpj,editEmailCliente,editNomeCliente,editTelefone,editTipoPessoa);

		} else {
            
			Vm.debug(Mensagens_Vm.DIGITE_F_OU_J);
			Mensagens_Popup.getInstance().DIGITE_F_OU_J();
			editTipoPessoa.setText("");
		}
	}
	

}