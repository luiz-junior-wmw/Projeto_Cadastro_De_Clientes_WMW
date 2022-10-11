package br.com.wmw.projeto_integracao.controller;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidarCnpj {

	public static boolean validaCnpj(String cpfCnpj) {

		CNPJValidator cnpjValidator = new CNPJValidator();

		try {
			cnpjValidator.assertValid(cpfCnpj);

			if (!cpfCnpj.equals("00.000.000/0000-00")) {

				return true;
			}

		}catch(InvalidStateException e) {

			return false;

		}
		return false;
	}

}
