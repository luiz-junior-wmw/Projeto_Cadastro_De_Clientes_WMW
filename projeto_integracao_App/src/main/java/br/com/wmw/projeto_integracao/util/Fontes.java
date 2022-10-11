package br.com.wmw.projeto_integracao.util;

import totalcross.ui.font.Font;

public class Fontes {

	public static final int FONT_DEFAULT_SIZE = 12;
	public static Font latoMediumDefaultSize;
	public static Font latoBoldMinus1;
	public static Font latoBoldPlus8;
	public static Font latoLightDefaultSize;
	public static Font latoLightPlus2;
	public static Font latoLightPlus4;
	public static Font latoLightPlus6;
	public static Font latoRegularDefaultSize;

	static {

		latoRegularDefaultSize = Font.getFont("Lato Regular", false, FONT_DEFAULT_SIZE);
		latoMediumDefaultSize = Font.getFont("Lato Medium", false, FONT_DEFAULT_SIZE);
		latoBoldPlus8 = latoMediumDefaultSize.adjustedBy(8);
		latoBoldMinus1 = latoMediumDefaultSize.adjustedBy(-1);
		latoLightDefaultSize = Font.getFont("Lato Light", false, FONT_DEFAULT_SIZE);
		latoLightPlus2 = latoLightDefaultSize.adjustedBy(2);
		latoLightPlus4 = latoLightDefaultSize.adjustedBy(4);
		latoLightPlus6 = latoLightDefaultSize.adjustedBy(6);

	}
}
