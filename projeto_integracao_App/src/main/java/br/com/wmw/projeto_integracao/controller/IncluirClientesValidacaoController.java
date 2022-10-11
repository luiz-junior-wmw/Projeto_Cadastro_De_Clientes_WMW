package br.com.wmw.projeto_integracao.controller;

import java.sql.SQLException;

import br.com.wmw.projeto_integracao.dao.ClienteDao;
import br.com.wmw.projeto_integracao.model.Cliente;
import br.com.wmw.projeto_integracao.util.Mensagens_Popup;
import br.com.wmw.projeto_integracao.util.Mensagens_Vm;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.gfx.Color;

public class IncluirClientesValidacaoController {
	
public static IncluirClientesValidacaoController instance;
	
	public static IncluirClientesValidacaoController getInstance() {
		if (instance == null) {
			instance = new IncluirClientesValidacaoController();
		}
		return instance;
	}

	public boolean isVerificaSeTodosOsCamposEstaoPreenchidos(String nome, String telefone, String tipoPessoa, String cpfCnpj, String status) {

       if(!nome.isEmpty() && !telefone.isEmpty() && !tipoPessoa.isEmpty() && !cpfCnpj.isEmpty()) {
    	  
    	   return true;
       }
		
		return false;
	}
	
	public void LimparCampos(Edit editNomeCliente, Edit editEmailCliente, Edit editTelefone, Edit editTipoPessoa, Edit editCpfCnpj) {
		editNomeCliente.setText("");
		editEmailCliente.setText("");
		editTelefone.setText("");
		editTipoPessoa.setText("");
		editCpfCnpj.setText("");
	}
	
	public void BotaoDesabilitado(Button btnAlterar, Button btnExcluir) {
		btnAlterar.setBackForeColors(Color.BRIGHT, Color.WHITE);
		btnAlterar.setEnabled(false);
		btnExcluir.setBackForeColors(Color.BRIGHT, Color.WHITE);
		btnExcluir.setEnabled(false);

	}
	
	public void ValidaSeCamposEstaoPreenchidosAntesDeAlterar(ClienteDao dao, String nome, String email, String telefone,
			String tipoPessoa, String cpfCnpj, String status) {
		Cliente cliente = new Cliente(nome, email, telefone, tipoPessoa, cpfCnpj, status);

		if (IncluirClientesValidacaoController.getInstance().isVerificaSeTodosOsCamposEstaoPreenchidos(nome, telefone,
				tipoPessoa, cpfCnpj, status)) {
			try {
//				IncluirClientesValidacaoController.getInstance().BotaoDesabilitado(btnAlterar, btnExcluir);
//				IncluirClientesValidacaoController.getInstance().LimparCampos(editCpfCnpj, editEmailCliente, editNomeCliente, editTelefone, editTipoPessoa);
				cliente.setStatus("ATT");
				dao.AlterarCliente(cliente);
			} catch (SQLException e) {
				Vm.debug(Mensagens_Vm.ERRO_ALTERAR_CLIENTE);
				Mensagens_Popup.getInstance().ERRO_ALTERACAO();
			}
		} else {
			
			Vm.debug(Mensagens_Vm.CPF_TELEFONE_INCOMPLETOS);
			Mensagens_Popup.getInstance().ERRO_DADOS_INCOMPLETOS();
		}
	}
}
