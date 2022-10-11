package br.com.wmw.projeto_integracao.ui;

import java.sql.SQLException;

import br.com.wmw.projeto_integracao.clienteRest.ClienteWebRest;
import br.com.wmw.projeto_integracao.util.ControleUtil;
import br.com.wmw.projeto_integracao.util.Cores;
import br.com.wmw.projeto_integracao.util.Imagens;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Window;

public class MenuWindow extends Window {
	
	private static ImageControl logon;

	@Override
	public void popup() {
		setRect(0, 0, Settings.screenWidth, Settings.screenHeight);
		super.popup();
	}

	@Override
	public void initUI() {

		logon = new ImageControl(Imagens.telaAnimacao);
		logon.scaleToFit = true;
		logon.transparentBackground = true;
		add(logon, LEFT + ControleUtil.BORDER_SPACING, TOP + ControleUtil.BORDER_SPACING, PARENTSIZE + 30,
				PARENTSIZE + 20);

		Container cont = new Container();
		cont.transparentBackground = true;
		add(cont, LEFT + ControleUtil.BORDER_SPACING, BOTTOM, FILL - ControleUtil.BORDER_SPACING, PARENTSIZE + 50);
		
		Button novoCliente = new Button("Cadastrar Cliente", Button.BORDER_ROUND);
		novoCliente.setForeColor(Cores.AZUL_WMW);
		cont.add(novoCliente, LEFT, TOP + 20, FILL, PREFERRED - 10);
		novoCliente.addPressListener((e) -> {
           CadastrarClienteWindow cadastrar = new CadastrarClienteWindow();
           cadastrar.popup();
		});

		Button listarCliente = new Button("Listar Clientes", Button.BORDER_ROUND);
		setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		listarCliente.setForeColor(Cores.AZUL_WMW);
		cont.add(listarCliente, LEFT, AFTER + 10, FILL, PREFERRED - 10);
		listarCliente.addPressListener((e) -> {
           ListaClienteWindow lista = new ListaClienteWindow();
           lista.popup();
			
		});

		Button buscarCliente = new Button("Buscar", Button.BORDER_ROUND);
		setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		buscarCliente.setForeColor(Cores.AZUL_WMW);
		cont.add(buscarCliente, LEFT, AFTER + 10, FILL, PREFERRED - 10);
		buscarCliente.addPressListener((e) -> {
            BuscarClienteWindow buscar = new BuscarClienteWindow();
           
            buscar.popup();
		});

		Button receberDados = new Button("Download de Dados", Button.BORDER_ROUND);
		setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		receberDados.setForeColor(Cores.AZUL_WMW);
		cont.add(receberDados, LEFT, AFTER + 10, FILL, PREFERRED - 10);
		receberDados.addPressListener((e) -> {
			ClienteWebRest webRest = new ClienteWebRest();
            webRest.ReceberDadosDaWeb();
		});

		Button enviarDados = new Button("Upload de Dados", Button.BORDER_ROUND);
		setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		enviarDados.setForeColor(Cores.AZUL_WMW);
		cont.add(enviarDados, LEFT, AFTER + 10, FILL, PREFERRED - 10);
		enviarDados.addPressListener((e) -> {
           ClienteWebRest webRest = new ClienteWebRest();
			try {
				webRest.EnviarDadosParaWeb();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		});
		
		Button sair = new Button("Sair", Button.BORDER_ROUND);
		setBackForeColors(Cores.AZUL_WMW, Cores.BRANCO_WMW);
		sair.setForeColor(Cores.AZUL_WMW);
		cont.add(sair, LEFT, AFTER + 10, FILL, PREFERRED - 10);
		sair.addPressListener((e) -> {

			System.exit(0);
		});
	}

}
