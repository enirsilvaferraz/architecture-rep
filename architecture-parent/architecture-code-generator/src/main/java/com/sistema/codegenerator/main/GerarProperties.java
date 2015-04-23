package com.sistema.codegenerator.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.sistema.codegenerator.model.AplicacaoConfig;
import com.sistema.codegenerator.model.CampoConfig;
import com.sistema.codegenerator.model.ClasseConfig;
import com.sistema.codegenerator.util.FileUtil;

public class GerarProperties {

	/**
	 * Prop 0 --> Nome do Model <br>
	 * Prop 1 --> Lógica da página
	 */
	private static String PROP_TITULO = "{0}.{1}.titulo";

	/**
	 * Prop 0 --> Nome do Model <br>
	 * Prop 1 --> Lógica da página <br>
	 * Prop 2 --> Nome do Field
	 */
	private static String PROP_NOME = "{0}.{1}.campo.{2}";

	public static void gerarArquivoProperties(AplicacaoConfig pConfig, ClasseConfig pClasseModel) throws IOException {

		String lArquivoLabel = FileUtil.obterTextoArquivo(pConfig.getCaminhoLabel());

		String titulo = PROP_TITULO.replace("{0}", pClasseModel.getNomeModelProperties()).replace("{1}",
				pClasseModel.getLogicaTela().getExtesaoTela());

		String texto = "";

		if (!lArquivoLabel.contains(titulo)) {
			texto += titulo + "=Manutenção de " + pClasseModel.getNomeModelProperties().substring(0, 1).toUpperCase()
					+ pClasseModel.getNomeModelProperties().substring(1) + "\n";
			System.out.println("           Label: " + titulo);
		}

		for (CampoConfig atributo : pClasseModel.getListCampo()) {

			String label = PROP_NOME.replace("{0}", pClasseModel.getNomeModelProperties())
					.replace("{1}", pClasseModel.getLogicaTela().getExtesaoTela()).replace("{2}", atributo.getNome());

			if (!lArquivoLabel.contains(label)) {
				texto += label + "=" + atributo.getNome().substring(0, 1).toUpperCase() + atributo.getNome().substring(1) + "\n";
				System.out.println("           Label: " + label);
			}

		}

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(pConfig.getCaminhoLabel()));
		buffWrite.append(lArquivoLabel + "\n");
		buffWrite.append(texto + "\n");
		buffWrite.close();
	}
}
