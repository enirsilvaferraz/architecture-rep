package com.sistema.codegenerator.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.sistema.codegenerator.model.AplicacaoConfig;
import com.sistema.codegenerator.model.CampoConfig;
import com.sistema.codegenerator.model.ClasseConfig;
import com.sistema.codegenerator.util.FileUtil;

public class GerarManagedBean {

	public static void gerarManagedBean(AplicacaoConfig pConfig, ClasseConfig pClasseModel) throws IOException {

		String texto = FileUtil.obterTextoArquivo(FileUtil.obterTemplateMB(pClasseModel));

		texto = texto.replaceAll("\\[NOME_MODEL]", pClasseModel.getNomeModel());
		texto = texto.replaceAll("\\[NOME_MB]", pClasseModel.getNomeModelMB());
		texto = texto.replaceAll("\\[CLASSE_MB]", pClasseModel.getNomeClasseMB());
		texto = texto.replaceAll("\\[NOME_CLASSE]", pClasseModel.getClasse().getSimpleName());
		texto = texto.replaceAll("\\[PACOTE]", pClasseModel.getPacoteManagedBean());
		texto = texto.replaceAll("\\[PREENCHER_COMBO]", preencherCombo(pClasseModel));
		texto = texto.replaceAll("\\[IMPORT]", preencherImport(pClasseModel));

		File diretorio = new File(pConfig.getCaminhoJava() + pClasseModel.getPacoteManagedBean().replaceAll("\\.", "\\/") + "/");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		// Abre o arquivo para escrita
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(pConfig.getCaminhoJava()
				+ pClasseModel.getPacoteManagedBean().replaceAll("\\.", "\\/") + "/" + pClasseModel.getNomeClasseMB() + ".java"));
		buffWrite.append(texto);
		buffWrite.close();

	}

	private static String preencherCombo(ClasseConfig pClasseModel) throws FileNotFoundException {

		StringBuilder lSB = new StringBuilder();

		for (CampoConfig lCampoTemp : pClasseModel.getListCampo()) {

			if (lCampoTemp.getField().getType() == List.class) {

				ParameterizedType stringType = (ParameterizedType) lCampoTemp.getField().getGenericType();
				Class<?> stringListClass = (Class<?>) stringType.getActualTypeArguments()[0];

				String texto = FileUtil.obterTextoArquivo(FileUtil.obterTemplateCombo());
				texto = texto.replaceAll("\\[NOME_CLASSE_COMBO]", stringListClass.getSimpleName()) + "\n\n";

				lSB.append(texto);
			}

			else if (FileUtil.verificarInstanciaObjeto(lCampoTemp.getField().getType())) {

				String texto = FileUtil.obterTextoArquivo(FileUtil.obterTemplateCombo());
				texto = texto.replaceAll("\\[NOME_CLASSE_COMBO]", lCampoTemp.getField().getType().getSimpleName()) + "\n\n";

				lSB.append(texto);
			}
		}

		return lSB.toString();
	}

	private static String preencherImport(ClasseConfig pClasseModel) throws FileNotFoundException {

		StringBuilder lSB = new StringBuilder();
		lSB.append("import " + pClasseModel.getClasse().getName() + ";\n");

		for (CampoConfig lCampoTemp : pClasseModel.getListCampo()) {

			if (lCampoTemp.getField().getType() == List.class) {

				if (!lSB.toString().contains(List.class.getName())) {
					lSB.append("import " + List.class.getName() + ";\n");
				}
				
				ParameterizedType stringType = (ParameterizedType) lCampoTemp.getField().getGenericType();
				Class<?> stringListClass = (Class<?>) stringType.getActualTypeArguments()[0];

				lSB.append("import " + stringListClass.getName() + ";\n");
			}

			else if (FileUtil.verificarInstanciaObjeto(lCampoTemp.getField().getType())) {
				
				if (!lSB.toString().contains(List.class.getName())) {
					lSB.append("import " + List.class.getName() + ";\n");
				}
				
				lSB.append("import " + lCampoTemp.getField().getType().getName() + ";\n");
			}
		}

		return lSB.toString();
	}
}
