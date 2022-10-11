package br.com.wmw.projeto_integracao.ui;

import java.sql.SQLException;

import br.com.wmw.projeto_integracao.controller.IncluirClientesValidacaoController;
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
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;

public class IncluirClientesWindow extends Window {

	private ClienteDao clienteDao = new ClienteDao();
	private Edit editNomeCliente;
	private Edit editEmailCliente;
	private Edit editTelefone;
	private Edit editTipoPessoa;
	private Edit editCpfCnpj;
	private Button btnVoltar;
	private Button btnAlterar;
	private Button btnExcluir;
	private ImageControl logoWmw;

	public IncluirClientesWindow() {

		logoWmw = new ImageControl(Imagens.telaAnimacao);
		logoWmw.scaleToFit = true;
		logoWmw.transparentBackground = true;
		add(logoWmw, LEFT + ControleUtil.BORDER_SPACING, TOP + ControleUtil.BORDER_SPACING, PARENTSIZE + 30,
				PARENTSIZE + 20);

		Label buscarClienteLabel = new Label("Cliente Selecionado");
		buscarClienteLabel.setFont(Fontes.latoLightPlus6);
		buscarClienteLabel.setForeColor(Cores.AZUL_WMW);
		add(buscarClienteLabel, CENTER + 30, TOP + 20, PREFERRED, TelaInicialWindow.PREFERRED);

		editNomeCliente = new Edit();
		editEmailCliente = new Edit();

		editTelefone = new Edit("+99 (99) 9 9999-9999");
		editTelefone.setValidChars("0123456789");

		editTipoPessoa = new Edit();
		editTipoPessoa.setMaxLength(1);

		editCpfCnpj = new Edit();
		editCpfCnpj.setMaxLength(14);

		btnVoltar = new Button("Voltar", Button.BORDER_ROUND);
		btnExcluir = new Button("Excluir", Button.BORDER_ROUND);
		btnAlterar = new Button("Alterar", Button.BORDER_ROUND);
	}

	public IncluirClientesWindow(Cliente cliente) {
		this();
		editNomeCliente.setText(cliente.getNome());
		editEmailCliente.setText(cliente.getEmail());
		editTelefone.setText(cliente.getTelefone());
		editTipoPessoa.setText(cliente.getTipo_de_Pessoa());
		editCpfCnpj.setText(cliente.getCpf_Cnpj());
	}

	@Override
	public void initUI() {

		add(new Label("Nome"), LEFT + 20, AFTER + 20);
		editNomeCliente.setFont(Fontes.latoLightPlus4);
		add(editNomeCliente, CENTER, AFTER);
		editNomeCliente.setEditable(false);

		add(new Label("E-mail"), LEFT + 20, AFTER + 10);
		editEmailCliente.setFont(Fontes.latoLightPlus4);
		add(editEmailCliente, CENTER, AFTER);

		add(new Label("Telefone"), LEFT + 20, AFTER + 10);
		editTelefone.setFont(Fontes.latoLightPlus4);
		add(editTelefone, CENTER, AFTER);

		add(new Label("Tipo Pessoa [F/J]"), LEFT + 20, AFTER + 10);
		editTipoPessoa.setFont(Fontes.latoLightPlus4);
		add(editTipoPessoa, CENTER, AFTER);
		editTipoPessoa.setEditable(false);

		add(new Label("Cpf/Cnpj"), LEFT + 20, AFTER + 10);
		editCpfCnpj.setFont(Fontes.latoLightPlus4);
		add(editCpfCnpj, CENTER, AFTER);
		editCpfCnpj.setEditable(false);

		add(btnVoltar, LEFT + 20, BOTTOM - 27, PREFERRED, PREFERRED - 10);
		btnVoltar.setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		btnVoltar.addPressListener((event) -> {
			ListaClienteWindow telaListaClienteWindow = new ListaClienteWindow();
			telaListaClienteWindow.popup();
			popup();

		});

		add(btnAlterar, CENTER - 8, BOTTOM - 27, PREFERRED, PREFERRED - 10);
		btnAlterar.setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		btnAlterar.addPressListener((event) -> {
			ClienteDao dao = new ClienteDao();
			String nome = editNomeCliente.getText().toUpperCase();
			String email = editEmailCliente.getText();
			String telefone = editTelefone.getText();
			String tipoPessoa = editTipoPessoa.getText().toUpperCase();
			String cpfCnpj = editCpfCnpj.getText();
			String status = "ATT";

			IncluirClientesValidacaoController.getInstance().ValidaSeCamposEstaoPreenchidosAntesDeAlterar(dao, nome, email, telefone, tipoPessoa, cpfCnpj, status);
			IncluirClientesValidacaoController.getInstance().BotaoDesabilitado(btnAlterar, btnExcluir);
			IncluirClientesValidacaoController.getInstance().LimparCampos(editCpfCnpj, editEmailCliente, editNomeCliente, editTelefone, editTipoPessoa);
		});

		add(btnExcluir, RIGHT - 30, BOTTOM - 27, PREFERRED, PREFERRED - 10);
		btnExcluir.setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		btnExcluir.addPressListener((event) -> {
			String cpf = editCpfCnpj.getText();

			try {
				clienteDao.ExcluirCliente(cpf);
			} catch (SQLException e) {
				Vm.debug(Mensagens_Vm.ERRO_EXCLUIR_CLIENTE);
				Mensagens_Popup.getInstance().ERRO_EXCLUSAO();
			}
		});
	}

	public void popup() {
		setRect(0, 0, Settings.screenWidth, Settings.screenHeight);
		super.popup();

	}

	@Override
	public void onEvent(@SuppressWarnings("rawtypes") Event event) {

		switch (event.type) {
			case ControlEvent.PRESSED:
				if (event.target == btnVoltar) {
					this.unpop();
				}
			default:

		}
	}

}
