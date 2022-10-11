package br.com.wmw.projeto_integracao.ui;

import br.com.wmw.projeto_integracao.controller.CadastrarClienteValidacaoController;
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
import totalcross.ui.gfx.Color;

public class CadastrarClienteWindow extends Window {

	private static Button btnInserir;
	private static Button btnVoltar;
	private static Button btnLimpar;

	private Edit editNomeCliente= new Edit();
	private Edit editEmailCliente= new Edit();
	private Edit editTelefone= new Edit();
	private Edit editTipoPessoa= new Edit();
	private Edit editCpfCnpj = new Edit();
	private ImageControl logoWmw;

	@Override
	public void popup() {

		setRect(0, 0, Settings.screenWidth, Settings.screenHeight);
		super.popup();
	}

	public void initUI() {

		logoWmw = new ImageControl(Imagens.telaAnimacao);
		logoWmw.scaleToFit = true;
		logoWmw.transparentBackground = true;
		add(logoWmw, LEFT + ControleUtil.BORDER_SPACING, TOP + ControleUtil.BORDER_SPACING, PARENTSIZE + 30,
				PARENTSIZE + 20);

		Label cadastroClienteLabel = new Label("Cadastrar Cliente");
		cadastroClienteLabel.setFont(Fontes.latoLightPlus6);
		cadastroClienteLabel.setForeColor(Cores.AZUL_WMW);
		add(cadastroClienteLabel, CENTER + 30, TOP + 20, PREFERRED, TelaInicialWindow.PREFERRED);

		Label nomeClienteoLabel = new Label("Nome *");
		add(nomeClienteoLabel, LEFT + 20, AFTER + 30, PREFERRED, TelaInicialWindow.PREFERRED);
		editNomeCliente = new Edit();
		editNomeCliente.setFont(Fontes.latoLightPlus4);
		add(editNomeCliente, CENTER, AFTER);
		editNomeCliente.requestFocus();
		editNomeCliente.addValueChangeHandler((e) -> {
		CadastrarClienteValidacaoController.getInstance().
		HabilitaBotaoLimparSeAlgumEditReceberAoMenosUmCaracter(editCpfCnpj, editEmailCliente, editNomeCliente, editTelefone, editTipoPessoa, btnLimpar);
		});

		Label emailClienteLabel = new Label("E-mail");
		add(emailClienteLabel, LEFT + 20, AFTER + 5, PREFERRED, TelaInicialWindow.PREFERRED);
		editEmailCliente = new Edit();
		editEmailCliente.setFont(Fontes.latoLightPlus4);
		add(editEmailCliente, CENTER, AFTER);
		editEmailCliente.addValueChangeHandler((e) -> {

			CadastrarClienteValidacaoController.getInstance().
			HabilitaBotaoLimparSeAlgumEditReceberAoMenosUmCaracter(editCpfCnpj, editEmailCliente, editNomeCliente, editTelefone, editTipoPessoa, btnLimpar);
		});

		Label telefoneClienteLabel = new Label("Telefone *");
		add(telefoneClienteLabel, LEFT + 20, AFTER + 5, PREFERRED, TelaInicialWindow.PREFERRED);
		editTelefone = new Edit("+99 (99) 9 9999-9999");
		editTelefone.setFont(Fontes.latoLightPlus4);
		editTelefone.setValidChars(Edit.numbersSet);
		add(editTelefone, LEFT + 20, AFTER);
		editTelefone.addValueChangeHandler((e) -> {

			CadastrarClienteValidacaoController.getInstance().
			HabilitaBotaoLimparSeAlgumEditReceberAoMenosUmCaracter(editCpfCnpj, editEmailCliente, editNomeCliente, editTelefone, editTipoPessoa, btnLimpar);
		});

		Label tipoPessoaLabel = new Label("Tipo Pessoa * [F-J]");
		add(tipoPessoaLabel, LEFT + 20, AFTER + 5, PREFERRED, TelaInicialWindow.PREFERRED);
		editTipoPessoa = new Edit();
		editTipoPessoa.setMaxLength(1);
		editTipoPessoa.setFont(Fontes.latoLightPlus4);
		add(editTipoPessoa, CENTER, AFTER);
		editTipoPessoa.addValueChangeHandler((e) -> {

			CadastrarClienteValidacaoController.getInstance().
			HabilitaBotaoLimparSeAlgumEditReceberAoMenosUmCaracter(editCpfCnpj, editEmailCliente,  editNomeCliente, editTelefone, editTipoPessoa, btnLimpar);
		});

		editTipoPessoa.addValueChangeHandler((event) -> {
			String tipoPessoa = editTipoPessoa.getText();
			exibeCampoConformeTipoPessoa(tipoPessoa);
		});

		btnInserir = new Button("Inserir", Button.BORDER_ROUND);
		desabilitaBotaoInserir();
		add(btnInserir, RIGHT - 30, BOTTOM - 27, PREFERRED, PREFERRED - 10);
		btnInserir.addPressListener((event) -> {

			if((CadastrarClienteValidacaoController.getInstance().verificaSeEPessoaFisica(editTipoPessoa) && 
				CadastrarClienteValidacaoController.getInstance().VerificaSeTelefoneEValido(editTelefone) && 
				CadastrarClienteValidacaoController.getInstance().VerificaSeCpfEValido(editCpfCnpj)) || 
				(CadastrarClienteValidacaoController.getInstance().verificaSeEPessoaJuridica(editTipoPessoa) && 
				CadastrarClienteValidacaoController.getInstance().VerificaSeTelefoneEValido(editTelefone) && 
				CadastrarClienteValidacaoController.getInstance().VerificaSeCnpjEValido(editCpfCnpj))) {
				CadastrarClienteValidacaoController.getInstance().InsereDados(editNomeCliente, editEmailCliente, editTelefone, editTipoPessoa, editCpfCnpj);
				CadastrarClienteValidacaoController.getInstance().LimparCampos(editEmailCliente, editNomeCliente, editTelefone, editTipoPessoa, editCpfCnpj);
				desabilitaBotaoInserir();
			}
			if(CadastrarClienteValidacaoController.getInstance().verificaSeEPessoaFisica(editTipoPessoa) && 
			   CadastrarClienteValidacaoController.getInstance().VerificaSeTelefoneEValido(editTelefone) && 
			   !CadastrarClienteValidacaoController.getInstance().VerificaSeCpfEValido(editCpfCnpj)) {
				
				Vm.debug(Mensagens_Vm.CPF_INVALIDO);
				Mensagens_Popup.getInstance().CPF_INVALIDO();
				desabilitaBotaoInserir();
			}
			if(CadastrarClienteValidacaoController.getInstance().verificaSeEPessoaJuridica(editTipoPessoa) && 
			   CadastrarClienteValidacaoController.getInstance().VerificaSeTelefoneEValido(editTelefone) && 
			   !CadastrarClienteValidacaoController.getInstance().VerificaSeCnpjEValido(editCpfCnpj)) {
				
				Vm.debug(Mensagens_Vm.CPF_INVALIDO);
				Mensagens_Popup.getInstance().CNPJ_INVALIDO();
				desabilitaBotaoInserir();
			}
			
			editNomeCliente.requestFocus();
			editCpfCnpj.setText("");
		});

		btnVoltar = new Button("Voltar", Button.BORDER_ROUND);
		add(btnVoltar, LEFT + 20, BOTTOM - 27, PREFERRED, PREFERRED - 10);
		btnVoltar.setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		btnVoltar.addPressListener((event) -> {
			MenuWindow menuWindow = new MenuWindow();
			menuWindow.popup();
			popup();

		});

		btnLimpar = new Button("Limpar", Button.BORDER_ROUND);
		add(btnLimpar, CENTER - 6, BOTTOM - 27, PREFERRED, PREFERRED - 10);
		btnLimpar.setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		btnLimpar.setEnabled(false);
		btnLimpar.setBackForeColors(Color.BRIGHT, Cores.BRANCO_WMW);
		btnLimpar.addPressListener((event) -> {
			CadastrarClienteValidacaoController.getInstance().LimparCampos(editCpfCnpj, editEmailCliente, editNomeCliente, editTelefone, editTipoPessoa);

		});
	}

	private void desabilitaBotaoInserir() {
		btnInserir.setEnabled(false);
		btnInserir.setBackForeColors(Color.BRIGHT, Cores.BRANCO_WMW);
	}
	
	public void exibeCampoConformeTipoPessoa(String tipoPessoa) {
		
		if(tipoPessoa.equals("f")) {
			Label cpfCnpjLabel = new Label();
			cpfCnpjLabel.setVisible(true);
			cpfCnpjLabel.setText("CPF *      ");
			editCpfCnpj = new Edit("999.999.999-99");
			editCpfCnpj.setFont(Fontes.latoLightPlus4);
			editCpfCnpj.setMode(Edit.NORMAL, true);
			editCpfCnpj.setValidChars(Edit.numbersSet);
			add(cpfCnpjLabel, LEFT + 20, CENTER + 125);
			add(editCpfCnpj, CENTER, BOTTOM - 90);
			editCpfCnpj.addValueChangeHandler((e) -> {
				String cpfCnpj = editCpfCnpj.getText();
				if(cpfCnpj.length() > 13) {
					CadastrarClienteValidacaoController.getInstance().HabilitaBotaoInserirSeTodosOsCamposEstiveremPreenchidos(editCpfCnpj, editNomeCliente, editTelefone, editTipoPessoa, btnInserir);
				}
			});
		} 
		if(tipoPessoa.equals("j")) {			
			Label cpfCnpjLabel = new Label();
			cpfCnpjLabel.setVisible(true);
			cpfCnpjLabel.setText("CNPJ *      ");
			editCpfCnpj = new Edit("99.999.999/9999-99");
			editCpfCnpj.setFont(Fontes.latoLightPlus4);
			editCpfCnpj.setMode(Edit.NORMAL, true);
			editCpfCnpj.setValidChars(Edit.numbersSet);
			add(cpfCnpjLabel, LEFT + 20, CENTER + 125);
			add(editCpfCnpj, CENTER, BOTTOM - 90);
			editCpfCnpj.addValueChangeHandler((e) -> {
				String cpfCnpj = editCpfCnpj.getText();
				if(cpfCnpj.length() > 17) {
					CadastrarClienteValidacaoController.getInstance().HabilitaBotaoInserirSeTodosOsCamposEstiveremPreenchidos(editCpfCnpj, editNomeCliente, editTelefone, editTipoPessoa, btnInserir);
				}
			});
		}
	}
}
