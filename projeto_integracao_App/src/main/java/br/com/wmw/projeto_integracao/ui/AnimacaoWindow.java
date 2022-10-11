package br.com.wmw.projeto_integracao.ui;

import br.com.wmw.projeto_integracao.util.Imagens;
import totalcross.ui.ImageControl;
import totalcross.ui.Window;
import totalcross.ui.anim.ControlAnimation;
import totalcross.ui.anim.FadeAnimation;

public class AnimacaoWindow extends Window {

	public AnimacaoWindow() {

	}

	protected void onPopup() {
		Imagens.loadImages(fmH);
		ImageControl logo, back;

		back = new ImageControl(Imagens.fundoAzul);
		back.scaleToFit = true;
		back.centerImage = true;
		back.hwScale = true;
		back.strechImage = true;
		add(back, LEFT, TOP, FILL, FILL);

		logo = new ImageControl(Imagens.telaAnimacao);
		logo.scaleToFit = true;
		logo.centerImage = false;
	    logo.transparentBackground = true;
		add(logo, CENTER, CENTER, PARENTSIZE + 50, PARENTSIZE + 50);

		FadeAnimation.create(logo, true, null, 6000)
				.then(FadeAnimation.create(logo, false, this::onAnimationFinished, 3000)).start();
	}

	public void onAnimationFinished(ControlAnimation anim) {
		this.unpop();
	}

}
