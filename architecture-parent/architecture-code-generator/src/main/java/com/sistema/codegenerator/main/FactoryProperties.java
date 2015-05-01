package com.sistema.codegenerator.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.sistema.codegenerator.model.AplicacaoConfig;
import com.sistema.codegenerator.model.CampoConfig;
import com.sistema.codegenerator.model.ClasseConfig;
import com.sistema.codegenerator.util.FileUtil;

public class FactoryProperties {

	/**
	 * Prop 0 --> Nome do Model <br>
	 * Prop 1 --> L�gica da p�gina
	 */
	private static String PROP_TITULO = "{0}.{1}.titulo";

	/**
	 * Prop 0 --> Nome do Model <br>
	 * Prop 1 --> L�gica da p�gina <br>
	 * Prop 2 --> Nome do Field
	 */
	private static String PROP_NOME = "{0}.{1}.campo.{2}";

	/**
	 * Realiza a gera��o do c�digo
	 * 
	 * @param pConfig
	 * @param pClasseModel
	 * @throws IOException
	 */
	public static void gerar(AplicacaoConfig pConfig, ClasseConfig pClasseModel) throws IOException {

		// Obt�m o arquivo em que ser� salva as altera��es
		StringBuilder lSB = new StringBuilder(FileUtil.obterTextoArquivo(pConfig.getCaminhoLabel()));
		lSB.append("\n\n");

		// Define o t�tulo
		definirTitulo(pClasseModel, lSB);

		// Itera cada item da lista
		for (CampoConfig atributo : pClasseModel.getListCampo()) {

			// Define o label
			String lLabel = PROP_NOME.replace("{0}", pClasseModel.getNomeModelProperties())
					.replace("{1}", pClasseModel.getLogicaTela().getExtesaoTela()).replace("{2}", atributo.getNome());

			// Sugere um valor
			String lValor = atributo.getNome().substring(0, 1).toUpperCase() + atributo.getNome().substring(1);

			// Adiciona no buffer se necessario
			if (verificarExistenciaLabel(lSB, lLabel)) {
				lSB.append(lLabel).append("=").append(lValor).append("\n");
			}
		}

		// Adiciona no arquivo
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(pConfig.getCaminhoLabel()));
		buffWrite.append(lSB.toString().trim());
		buffWrite.close();
	}

	/**
	 * Define o t�tulo da tela
	 * 
	 * @param pClasseModel
	 * @param lSB
	 */
	private static void definirTitulo(ClasseConfig pClasseModel, StringBuilder lSB) {

		// Define o t�tulo conforme padr�o
		String lLabel = PROP_TITULO.replace("{0}", pClasseModel.getNomeModelProperties()).replace("{1}",
				pClasseModel.getLogicaTela().getExtesaoTela());

		String lValor = pClasseModel.getTituloDaTela();

		// Adiciona no buffer se necessario
		if (verificarExistenciaLabel(lSB, lLabel)) {
			lSB.append(lLabel).append("=").append(lValor).append("\n");
		}
	}

	/**
	 * Verifica se o arquivo j� cont�m o t�tulo
	 * 
	 * @param lSB
	 * @param pLabel
	 * @param pValor
	 * @return
	 */
	private static boolean verificarExistenciaLabel(StringBuilder lSB, String pLabel) {
		return !lSB.toString().contains(pLabel);
	}
}
