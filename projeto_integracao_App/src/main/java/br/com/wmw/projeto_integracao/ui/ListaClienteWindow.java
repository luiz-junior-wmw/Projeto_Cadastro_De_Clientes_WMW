package br.com.wmw.projeto_integracao.ui;

import java.util.List;

import br.com.wmw.projeto_integracao.dao.ClienteDao;
import br.com.wmw.projeto_integracao.model.Cliente;
import br.com.wmw.projeto_integracao.util.ControleUtil;
import br.com.wmw.projeto_integracao.util.Cores;
import br.com.wmw.projeto_integracao.util.Fontes;
import br.com.wmw.projeto_integracao.util.Imagens;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Window;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.PenEvent;
import totalcross.ui.gfx.Color;

public class ListaClienteWindow extends Window {

	private ScrollContainer listaClientes = new ScrollContainer();
	private ClienteDao clienteDao = new ClienteDao();
	private List<Cliente> clientesList = clienteDao.findAllClientes();
	private static Button btnVoltar;
	private ImageControl logoWmw;

	public void loadList() {
		int index = 0;
		for (Cliente cliente : clientesList) {
			String[] dados = clienteToArray(cliente);
			Container clienteContainer = new Container();
			clienteContainer.appId = index++;
			clienteContainer.setBorderStyle(BORDER_SIMPLE);
			clienteContainer.setBackColor(Color.WHITE);
			listaClientes.add(clienteContainer, LEFT + 10, AFTER + 3, listaClientes.getWidth() - 30, 100);
			for (int dadosIndex = 0; dadosIndex < 5; dadosIndex++) {
				int horizontalPosition = LEFT + 10;
				int verticalPosition = AFTER;
				clienteContainer.add(new Label(dados[dadosIndex]), horizontalPosition, verticalPosition);
			}

		}
	}

	private String[] clienteToArray(Cliente cliente) {
		String[] dadosArray = new String[5];
		dadosArray[0] = "Nome - " + cliente.getNome();
		dadosArray[1] = "E-mail - " + cliente.getEmail();
		dadosArray[2] = "Fone - " + cliente.getTelefone();
		dadosArray[3] = "Tipo/Pessoa - " + cliente.getTipo_de_Pessoa();
		dadosArray[4] = "Cpf/Cnpj - " + cliente.getCpf_Cnpj();

		return dadosArray;
	}

	@Override
	public void initUI() {

		logoWmw = new ImageControl(Imagens.telaAnimacao);
		logoWmw.scaleToFit = true;
		logoWmw.transparentBackground = true;
		add(logoWmw, LEFT + ControleUtil.BORDER_SPACING, TOP + ControleUtil.BORDER_SPACING, PARENTSIZE + 30,
				PARENTSIZE + 20);

		Label listaClienteLabel = new Label("Lista de Clientes");
		listaClienteLabel.setForeColor(Cores.AZUL_WMW);
		listaClienteLabel.setFont(Fontes.latoLightPlus6);
		add(listaClienteLabel, CENTER + 40, TOP + 20, PREFERRED, TelaInicialWindow.PREFERRED);

		montaTela();

		btnVoltar = new Button("Voltar", Button.BORDER_ROUND);
		btnVoltar.setEnabled(true);
		btnVoltar.setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		add(btnVoltar, LEFT + 20, BOTTOM - 27, FILL - 20, PREFERRED - 10);
		btnVoltar.addPressListener((event) -> {
			MenuWindow telaMenuWindow = new MenuWindow();
			telaMenuWindow.popup();
			popup();

		});
	}

	public void montaTela() {
		add(listaClientes, LEFT, TOP + 60, FILL, getScrollContainerSize());

		loadList();

	}

	private int getScrollContainerSize() {
		int size = clientesList.size() + (clientesList.size() + 410);

		size = size > Settings.screenHeight ? Settings.screenHeight : size;
		return size;
	}

	@Override
	public void popup() {
		setRect(0, 0, Settings.screenWidth, Settings.screenHeight);
		super.popup();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onEvent(Event event) {
		switch (event.type) {
			case ControlEvent.PRESSED:

			case PenEvent.PEN_DOWN:
				if (event.target instanceof Container && !(event.target instanceof Window)) {
					Container c = (Container) event.target;
					Cliente cliente = clientesList.get(c.appId);
					if (cliente == null)
						return;
					IncluirClientesWindow clientesWindow = new IncluirClientesWindow(cliente);
					clientesWindow.popup();

					loadList();
				}
				break;
			default:
				break;
		}
	}
}
