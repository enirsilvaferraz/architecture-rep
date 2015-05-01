package com.sistema.codegenerator.main;

import java.io.IOException;
import java.util.List;

import com.sistema.codegenerator.model.ClasseConfig;
import com.sistema.codegenerator.model.AplicacaoConfig;
import com.sistema.codegenerator.util.FileUtil;

public class GeradorDeCodigo {

	public static void main(String[] args) {

		try {

			AplicacaoConfig lConfig = FileUtil.obterConfiguracaoArquivoProperties();

			List<ClasseConfig> lListClasseModel = FileUtil.obterListaClassesModelArquivoProperties();

			for (ClasseConfig lClasseModel : lListClasseModel) {
				processarClasse(lConfig, lClasseModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void processarClasse(AplicacaoConfig lConfig, ClasseConfig lClasseModel) throws IOException {

		// Validar se todas as dependencias estão no classloader
		lClasseModel.getClasse().getDeclaredFields();

		FactoryXHTML.gerar(lConfig, lClasseModel);

		FactoryProperties.gerar(lConfig, lClasseModel);

		FactoryManagedBean.gerar(lConfig, lClasseModel);
	}
}
