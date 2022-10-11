package br.com.wmw.projeto_integracao.ui;

import br.com.wmw.projeto_integracao.util.ControleUtil;
import br.com.wmw.projeto_integracao.util.Cores;
import br.com.wmw.projeto_integracao.util.Fontes;
import br.com.wmw.projeto_integracao.util.Imagens;
import br.com.wmw.projeto_integracao.util.Mensagens_Vm;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.SlidingWindow;

public class TelaInicialWindow extends Container {
	private Container back;
	private ImageControl tela1, logoWmw;

	public TelaInicialWindow() {

	}

	public void initUI() {
		Imagens.loadImages(fmH);
		back = new Container();
		add(back, LEFT, TOP, FILL, FILL);

		tela1 = new ImageControl(Imagens.fundoAzul);
		tela1.scaleToFit = true;
		tela1.centerImage = true;
		tela1.hwScale = true;
		tela1.strechImage = true;
		back.add(tela1, LEFT, TOP, FILL, FILL);

		logoWmw = new ImageControl(Imagens.telaAnimacao);
		logoWmw.scaleToFit = true;
		logoWmw.transparentBackground = true;
		back.add(logoWmw, LEFT + ControleUtil.BORDER_SPACING, TOP + ControleUtil.BORDER_SPACING, PARENTSIZE + 30,
				PARENTSIZE + 20);

		Container cont = new Container();
		cont.transparentBackground = true;
		back.add(cont, LEFT + ControleUtil.BORDER_SPACING, BOTTOM, FILL - ControleUtil.BORDER_SPACING, PARENTSIZE + 50);

		Label lbl = new Label(Mensagens_Vm.MENSAGEM_BOAS_VINDAS);
		lbl.setFont(Fontes.latoBoldPlus8);
		lbl.transparentBackground = true;
		lbl.setForeColor(Cores.BRANCO_WMW);
		cont.add(lbl, LEFT, TOP + ControleUtil.COMPONENT_SPACING);

		Button login = new Button("Entrar", Button.BORDER_ROUND);
		login.setForeColor(Cores.AZUL_WMW);
		cont.add(login, LEFT, AFTER + ControleUtil.COMPONENT_SPACING + 30, FILL, PREFERRED - 10);
		login.addPressListener((e) -> {
			SlidingWindow info = new AutenticacaoWindow();
			info.popup();
		});

		Button sair = new Button("Sair", Button.BORDER_OUTLINED);
		sair.setForeColor(Cores.BRANCO_WMW);
		cont.add(sair, LEFT, AFTER + 20, FILL, PREFERRED - 10);
		sair.addPressListener((e) -> {
			System.exit(0);
		});

	}
}
