package br.com.wmw.projeto_integracao.ui;

import br.com.wmw.projeto_integracao.controller.AutenticacaoValidacaoController;
import br.com.wmw.projeto_integracao.util.ControleUtil;
import br.com.wmw.projeto_integracao.util.Cores;
import br.com.wmw.projeto_integracao.util.Fontes;
import br.com.wmw.projeto_integracao.util.Mensagens;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.MaterialWindow;
import totalcross.ui.Presenter;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class AutenticacaoWindow extends MaterialWindow {

	private static Edit editSenha;
	private static Button btnContinuar;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AutenticacaoWindow() {
		super(false, new Presenter() {
			@Override
			public Container getView() {
				return new Container() {
					@Override
					public void initUI() {
						Label autenticacaoLabel = new Label(Mensagens.ACESSO_APP);
						autenticacaoLabel.setFont(Fontes.latoLightPlus6);

						add(autenticacaoLabel, LEFT + ControleUtil.BORDER_SPACING,
								AFTER + ControleUtil.COMPONENT_SPACING, PREFERRED, TelaInicialWindow.PREFERRED);

						editSenha = new Edit("999999");
						editSenha.setMaxLength(6);
						btnContinuar = new Button("Continuar", Button.BORDER_ROUND);
						btnContinuar.setEnabled(false);
						btnContinuar.setBackForeColors(Color.BRIGHT, Color.WHITE);
						add(btnContinuar, LEFT + 20, CENTER +80, FILL - 20, PREFERRED-10);

						editSenha.caption = "";
						editSenha.setMode(Edit.PASSWORD_ALL, false);
						editSenha.transparentBackground = true;
						editSenha.addPressListener(new PressListener() {

							@Override
							public void controlPressed(ControlEvent event) {
								String senha = editSenha.getText();

								if (AutenticacaoValidacaoController.getInstance().isVerificaSeSenhaPossuiSeisDigitos(senha)) {
									btnContinuar.setBackForeColors(Cores.AZUL_WMW, Color.WHITE);
									btnContinuar.setEnabled(true);
								}
							}
						});

						add(editSenha, SAME, AFTER + ControleUtil.COMPONENT_SPACING, FILL - ControleUtil.BORDER_SPACING,
								PREFERRED);
						add(btnContinuar, LEFT, CENTER, FILL, PREFERRED-10);
						btnContinuar.addPressListener((event) -> {
						
							AutenticacaoValidacaoController.getInstance().Autenticacao(editSenha, btnContinuar) ;
							
						});
					}
				};
			}
		});

		transparentBackground = true;
		fadeOtherWindows = true;

		this.setBackColor(Cores.AZUL_WMW);
		this.setSlackSpace(100);
	}
}
