package br.com.wmw.projeto_integracao.util;

import totalcross.io.IOException;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Imagens {
	private Imagens() {
	}

	public static Image 
			fundoAzul,telaAnimacao;

	public static void loadImages(int fmH) {
		try {
			fundoAzul = new Image("images/fundoAzul.png");
			telaAnimacao = new Image("images/telaAnimacao.png");
			
		} catch (ImageException | IOException e) {
			e.printStackTrace();
		}
	}
}
