package br.com.wmw.projeto_integracao.util;

import br.com.wmw.projeto_integracao.ui.AnimacaoWindow;
import br.com.wmw.projeto_integracao.ui.TelaInicialWindow;
import totalcross.sys.Settings;
import totalcross.ui.MainWindow;
import totalcross.ui.font.Font;

public class WmwAppConfig extends MainWindow {
	public WmwAppConfig() {
		setUIStyle(Settings.MATERIAL_UI);
		setDefaultFont(Font.getFont(Fontes.FONT_DEFAULT_SIZE));
	}

	static {
		Settings.applicationId = "WMWAPP";
		Settings.appVersion = "1.0.1";
		Settings.iosCFBundleIdentifier = "com.totalcross.sample.wmwApp";
	}

	public void initUI() {
		AnimacaoWindow wmwAnimacao;
		TelaInicialWindow inicial = new TelaInicialWindow();
		
		wmwAnimacao = new AnimacaoWindow();
		wmwAnimacao.popupNonBlocking();
		swap(inicial);
		
	}
}
