package br.com.wmw.projeto_integracao.ui;

import java.sql.SQLException;

import br.com.wmw.projeto_integracao.controller.BuscarClienteValidacaoController;
import br.com.wmw.projeto_integracao.dao.ClienteDao;
import br.com.wmw.projeto_integracao.model.Cliente;
import br.com.wmw.projeto_integracao.util.ControleUtil;
import br.com.wmw.projeto_integracao.util.Cores;
import br.com.wmw.projeto_integracao.util.Fontes;
import br.com.wmw.projeto_integracao.util.Imagens;
import br.com.wmw.projeto_integracao.util.Mensagens_Popup;
import br.com.wmw.projeto_integracao.util.Mensagens_Vm;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.Window;

public class BuscarClienteWindow extends Window {
	
	private ImageControl logoWmw;
	private Edit editNomeCliente;
	private Edit editEmailCliente;
	private Edit editTelefone;
	private Edit editTipoPessoa;
	private Edit editCpfCnpj;
	private static Button btnVoltar;
	
	@Override
	public void popup() {
		setRect(0, 0, Settings.screenWidth, Settings.screenHeight);
		super.popup();
	}

	@Override
	public void initUI() {

		logoWmw = new ImageControl(Imagens.telaAnimacao);
		logoWmw.scaleToFit = true;
		logoWmw.transparentBackground = true;
		add(logoWmw, LEFT + ControleUtil.BORDER_SPACING, TOP + ControleUtil.BORDER_SPACING, PARENTSIZE + 30,
				PARENTSIZE + 20);
		btnVoltar = new Button("Voltar", Button.BORDER_ROUND);

		Label buscarClienteLabel = new Label("Buscar Cliente");
		buscarClienteLabel.setFont(Fontes.latoLightPlus6);
		buscarClienteLabel.setForeColor(Cores.AZUL_WMW);
		add(buscarClienteLabel, CENTER + 30, TOP + 20, PREFERRED, TelaInicialWindow.PREFERRED);

		Label tipoPessoaLabel = new Label("Tipo Pessoa * [F-J]");
		add(tipoPessoaLabel, LEFT + 20, AFTER + 35, PREFERRED, TelaInicialWindow.PREFERRED);
		editTipoPessoa = new Edit();
		editTipoPessoa.setMaxLength(1);
		editTipoPessoa.setFont(Fontes.latoLightPlus4);
		add(editTipoPessoa, CENTER, AFTER);
		editTipoPessoa.requestFocus();

		Label cpfCnpjLabel = new Label("**********");
		cpfCnpjLabel.setVisible(false);
		add(cpfCnpjLabel, LEFT + 20, AFTER + 5, PREFERRED, TelaInicialWindow.PREFERRED);

		Label nomeClienteoLabel = new Label("Nome *");
		add(nomeClienteoLabel, LEFT + 20, AFTER + 60, PREFERRED, TelaInicialWindow.PREFERRED);
		editNomeCliente = new Edit();
		editNomeCliente.setFont(Fontes.latoLightPlus4);
		add(editNomeCliente, CENTER, AFTER);
		editNomeCliente.setEditable(false);

		Label emailClienteLabel = new Label("E-mail");
		add(emailClienteLabel, LEFT + 20, AFTER + 5, PREFERRED, TelaInicialWindow.PREFERRED);
		editEmailCliente = new Edit();
		editEmailCliente.setFont(Fontes.latoLightPlus4);
		add(editEmailCliente, CENTER, AFTER);
		editEmailCliente.setEditable(false);

		Label telefoneClienteLabel = new Label("Telefone *");
		add(telefoneClienteLabel, LEFT + 20, AFTER + 5, PREFERRED, TelaInicialWindow.PREFERRED);
		editTelefone = new Edit("+99 (99) 9 9999-9999");
		editTelefone.setFont(Fontes.latoLightPlus4);
		editTelefone.setValidChars(Edit.numbersSet);
		add(editTelefone, CENTER, AFTER);
		editTelefone.setEditable(false);

		add(btnVoltar, LEFT + 20, BOTTOM - 27, FILL - 20, PREFERRED - 10);
		btnVoltar.setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		btnVoltar.addPressListener((event) -> {
			MenuWindow menuWindow = new MenuWindow();
			menuWindow.popup();
		});

		VerificaSeEditTipoPessoaRecebeuNumeroDeCaracteresCorreto();

	}
	
	public void ExibeCampoCnpjCasoSejaPessoaJuridica() {
		Label cpfCnpjLabel = new Label();
		cpfCnpjLabel.setVisible(true);
		cpfCnpjLabel.setText("CNPJ *      ");
		add(cpfCnpjLabel, LEFT + 20, TOP + 163, PREFERRED, TelaInicialWindow.PREFERRED);
		editCpfCnpj = new Edit("99.999.999/9999-99");
		editCpfCnpj.setFont(Fontes.latoLightPlus4);
		editCpfCnpj.setMode(Edit.NORMAL, true);
		editCpfCnpj.setValidChars(Edit.numbersSet);
		add(editCpfCnpj, CENTER, CENTER - 73);
		editCpfCnpj.requestFocus();
	}

	public void ExibeCampoCpfCasoSejaPessoaFisica() {
		
		Label cpfCnpjLabel = new Label();
		cpfCnpjLabel.setVisible(true);
		cpfCnpjLabel.setText("CPF *      ");
		add(cpfCnpjLabel, LEFT + 20, TOP + 163, PREFERRED, TelaInicialWindow.PREFERRED);
		editCpfCnpj = new Edit("999.999.999-99");
		editCpfCnpj.setFont(Fontes.latoLightPlus4);
		editCpfCnpj.setMode(Edit.NORMAL, true);
		editCpfCnpj.setValidChars(Edit.numbersSet);
		add(editCpfCnpj, CENTER, CENTER - 73);
		editCpfCnpj.requestFocus();
	}

	private void VerificaSeEditTipoPessoaRecebeuNumeroDeCaracteresCorreto() {
		editTipoPessoa.addValueChangeHandler((event) -> {
			DefinirTipoDePessoa();

		});
	}

	private void DefinirTipoDePessoa() { 

		String tipoPessoa = editTipoPessoa.getText();
		
		if(editTipoPessoa.getText().isEmpty()) {
			editTipoPessoa.requestFocus();
		}

		else if (BuscarClienteValidacaoController.getInstance().isVerificaSeEditRecebe_F(tipoPessoa)) {
			 ExibeCampoCpfCasoSejaPessoaFisica();
			 VerificaSeEditCpfRecebeuNumeroDeCaracteresCorreto(editCpfCnpj,editNomeCliente,editEmailCliente,editTelefone,editTipoPessoa);

		} else if (BuscarClienteValidacaoController.getInstance().isVerificaSeEditRecebe_J(tipoPessoa)) {
			 ExibeCampoCnpjCasoSejaPessoaJuridica();
			 VerificaSeEditCnpjRecebeuNumeroDeCaracteresCorreto(editCpfCnpj,editEmailCliente,editNomeCliente,editTelefone,editTipoPessoa);

		} else {
            
			Vm.debug(Mensagens_Vm.DIGITE_F_OU_J);
			Mensagens_Popup.getInstance().DIGITE_F_OU_J();
			editTipoPessoa.setText("");
		}
	}
	
	private void SetaCamposDeAcordoComCpfCnpjDigitado(String cpfCnpj) throws SQLException {
		  
	    Cliente cliente;
	    ClienteDao dao = new ClienteDao();
		
	    cpfCnpj = editCpfCnpj.getText();
		cliente = dao.findAllClientesByCpfCnpj(cpfCnpj);
		editNomeCliente.setText(cliente.getNome());
		editTelefone.setText(cliente.getTelefone());
		editEmailCliente.setText(cliente.getEmail());
}
	public void VerificaSeEditCpfRecebeuNumeroDeCaracteresCorreto(Edit editCpfCnpj, Edit editNomeCliente,Edit editEmailCliente, Edit editTelefone, Edit editTipoPessoa) { 
		editCpfCnpj.addValueChangeHandler((e) -> {
			String cpf = editCpfCnpj.getText();
			if (BuscarClienteValidacaoController.getInstance().isVerificaSeCpfPossuiQuatorzeCaracteres(cpf)) {
				try {
					SetaCamposDeAcordoComCpfCnpjDigitado(cpf);
					editCpfCnpj.setEditable(false);
					editNomeCliente.setEditable(false);
					editEmailCliente.setEditable(false);
					editTelefone.setEditable(false);
					editTipoPessoa.requestFocus();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void VerificaSeEditCnpjRecebeuNumeroDeCaracteresCorreto(Edit editCpfCnpj,Edit editNomeCliente,Edit editEmailCliente,Edit editTelefone,Edit editTipoPessoa) { 
		editCpfCnpj.addValueChangeHandler((e) -> {
			String cpfCnpj = editCpfCnpj.getText();
			if (BuscarClienteValidacaoController.getInstance().isVerificaSeCnpjPossuiDezoitoCaracteres(cpfCnpj)) {
				try {
					SetaCamposDeAcordoComCpfCnpjDigitado(cpfCnpj);
					editCpfCnpj.setEditable(false);
					editNomeCliente.setEditable(false);
					editEmailCliente.setEditable(false);
					editTelefone.setEditable(false);
					editTipoPessoa.requestFocus();
					} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
