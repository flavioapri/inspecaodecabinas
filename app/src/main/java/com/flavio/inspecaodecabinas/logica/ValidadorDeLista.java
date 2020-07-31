package com.flavio.inspecaodecabinas.logica;

import com.flavio.inspecaodecabinas.excecao.FormatoInvalidoException;
import com.flavio.inspecaodecabinas.excecao.InformacoesInsuficientesException;
import com.flavio.inspecaodecabinas.excecao.InspecaoCabinasException;

/**
 * Separa os campos da String com as informações da cabina e verifica se estas são validas.
 *
 * @author Flávio Aparecido Ribeiro
 * @version 1.0
 */
public class ValidadorDeLista {
	/**
	 * Divide a string com as informações da cabina gerando um array com essas informações. Cada informação está separada por um
	 * espaço na string, o método realiza esta operação e envia estas para os método verificador.
	 *
	 * @param informacoes string com as informações da cabina
	 * @return array com as informações da cabina
	 */
	public static String[] validar(String informacoes) {
		String[] informacoesSeparadas = informacoes.split(Constantes.ESPACO);
		int qtdInformacoes = informacoesSeparadas.length;
		try {
			validarArrayInformacoes(qtdInformacoes);
			validarInformacao(informacoesSeparadas[Constantes.POSICAO_SEQUENCIA], Constantes.FORMATO_CAMPO_SEQUENCIA);
			validarInformacao(informacoesSeparadas[Constantes.POSICAO_NP], Constantes.FORMATO_CAMPO_NP);
			validarInformacao(informacoesSeparadas[Constantes.POSICAO_FZ], Constantes.FORMATO_CAMPO_FZ);
			validarInformacao(informacoesSeparadas[Constantes.POSICAO_PAIS], Constantes.FORMATO_CAMPO_PAIS);
			validarInformacao(informacoesSeparadas[Constantes.POSICAO_SERIE], Constantes.FORMATO_CAMPO_SERIE);

			for (int contador = Constantes.POSICAO_SERIE; contador < informacoesSeparadas.length; contador++) {
				if (informacoesSeparadas[contador].startsWith(Constantes.CARACTER_INICIAL_CODE))
					validarInformacao(informacoesSeparadas[contador], Constantes.FORMATO_CAMPO_CODE);
			}
		} catch (InspecaoCabinasException ie) {
			System.err.println(ie.getMessage());
			return null;
		}
		return informacoesSeparadas;
	}

	/**
	 * Valida o array com as informações da cabina. Cada informação é respectiva a uma posição no array. Para que o conjunto de
	 * informações atenda a integridade este deve possuir a quantidade minima de 4.
	 *
	 * @param qtdInformacoes quantidade de informacoes da string
	 * @throws InformacoesInsuficientesException caso as informações não sejam suficientes
	 */
	private static void validarArrayInformacoes(int qtdInformacoes) throws InformacoesInsuficientesException {
		if (qtdInformacoes < Constantes.QTD_MINIMA_INFORMACOES)
			throw new InformacoesInsuficientesException();
	}

	/**
	 * Valida cada informação verificando se estas atendem seus formatos específicos.
	 *
	 * @param informacao string contendo a informação a ser validada.
	 * @param formato    formato que a informação deve atender.
	 * @throws FormatoInvalidoException se a informação não atender o formato especificado.
	 */
	private static void validarInformacao(String informacao, String formato) throws FormatoInvalidoException {
		if (!informacao.matches(formato))
			throw new FormatoInvalidoException();
	}
}
