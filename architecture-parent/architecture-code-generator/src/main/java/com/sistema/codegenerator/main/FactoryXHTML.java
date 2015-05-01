package com.sistema.codegenerator.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Scanner;

import com.sistema.codegenerator.model.AplicacaoConfig;
import com.sistema.codegenerator.model.CampoConfig;
import com.sistema.codegenerator.model.ClasseConfig;
import com.sistema.codegenerator.model.enums.TipoTemplate;
import com.sistema.codegenerator.util.FileUtil;

public class FactoryXHTML {

	private static String carregarColunas(AplicacaoConfig pConfig, ClasseConfig pClasseModel, TipoTemplate pTipoTemplate)
			throws FileNotFoundException {

		StringBuilder lRetorno = new StringBuilder();
		for (CampoConfig lCampo : pClasseModel.getListCampo()) {
			lRetorno.append(carregarComponente(pClasseModel, lCampo, pTipoTemplate));
		}
		return lRetorno.toString();
	}

	private static String carregarComponente(ClasseConfig pClasseModel, CampoConfig pCampoModel, TipoTemplate pTipoTemplate)
			throws FileNotFoundException {

		String lComponente = FileUtil.carregarComponente(pClasseModel, pCampoModel, pTipoTemplate);

		lComponente = lComponente.replaceAll("\\[ATRIBUTO]", pCampoModel.getNome());
		lComponente = lComponente.replaceAll("\\[REQUIRED_CAD]", pCampoModel.getObrigatorioCadastro().toString());
		lComponente = lComponente.replaceAll("\\[REQUIRED_SEL]", pCampoModel.getObrigatorioPesquisa().toString());
		lComponente = lComponente.replaceAll("\\[MAX_LENGTH]", pCampoModel.getTamanho().toString());

		if (pCampoModel.getField().getType() == List.class) {
			ParameterizedType stringType = (ParameterizedType) pCampoModel.getField().getGenericType();
			Class<?> stringListClass = (Class<?>) stringType.getActualTypeArguments()[0];
			lComponente = lComponente.replaceAll("\\[METODO]", stringListClass.getSimpleName());
			
		} else {
			lComponente = lComponente.replaceAll("\\[METODO]", pCampoModel.getField().getType().getSimpleName());
		}

		return lComponente;
	}

	private static String definirNomeModelParaPaginaXhtml(String pClasse) {

		String lNomeClasse = pClasse.replace("Model", "");
		return lNomeClasse.substring(0, 1).toLowerCase() + lNomeClasse.substring(1);
	}

	/**
	 * Inicia a geração do código do XHTML
	 * 
	 * @param pConfig
	 * @param pClasseModel
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void gerar(AplicacaoConfig pConfig, ClasseConfig pClasseModel) throws IOException {

		// Define o nome do model. Ex: Pessoa
		String lModelName = definirNomeModelParaPaginaXhtml(pClasseModel.getClasse().getSimpleName());

		File diretorio = new File(pConfig.getCaminhoWeb());
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		// Define o nome e o caminho do arquivo XHTML destino para aquele model
		String lFilePath = pConfig.getCaminhoWeb() + lModelName + "-" + pClasseModel.getLogicaTela().getExtesaoTela() + ".xhtml";

		// Abre o arquivo para escrita
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(lFilePath));

		// Realiza a leitura do template
		Scanner scanner = new Scanner(new FileReader(FileUtil.obterTemplateTela(pClasseModel))).useDelimiter("\\Z");
		String texto = scanner.next();
		scanner.close();

		// Inicia a atribuição das variáveis do template
		texto = texto.replaceAll("\\[DATATABLE_COLUMNS]", carregarColunas(pConfig, pClasseModel, TipoTemplate.DATATABLE));
		texto = texto.replaceAll("\\[ARGUMENTOS]", carregarColunas(pConfig, pClasseModel, TipoTemplate.ARGUMENTO));
		texto = texto.replaceAll("\\[CADASTRO]", carregarColunas(pConfig, pClasseModel, TipoTemplate.CADASTRO));
		texto = texto.replaceAll("\\[DETALHES]", carregarColunas(pConfig, pClasseModel, TipoTemplate.DETALHE));
		texto = texto.replaceAll("\\[NOME_MODEL]", lModelName);
		texto = texto.replaceAll("\\[NOME_MB]", pClasseModel.getNomeModelMB());
		texto = texto.replaceAll("\\[LOGICA_TELA]", pClasseModel.getLogicaTela().getExtesaoTela());

		buffWrite.append(texto);
		buffWrite.close();

	}
}
