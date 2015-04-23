package com.sistema.codegenerator.main;

import java.io.IOException;
import java.util.List;

import com.sistema.codegenerator.model.ClasseConfig;
import com.sistema.codegenerator.model.AplicacaoConfig;
import com.sistema.codegenerator.util.FileUtil;

public class GeradorDeCodigo {

	public static void main(String[] args) {

		try {

			System.out.println("=====================================================================================");
			System.out.println("===                           INICIAR GERA��O DE C�DIGO                           ===");
			System.out.println("=====================================================================================");
			System.out.println("");
			System.out.println("");

			AplicacaoConfig lConfig = FileUtil.obterConfiguracaoArquivoProperties();

			System.out.println(" PARAMETROS DE CONFIGURA��O");
			System.out.println("");
			System.out.println("      Caminho da aplica��o:           " + lConfig.getCaminhoAbsoluto());
			System.out.println("      Caminho do diret�rio Java:      " + lConfig.getCaminhoJava());
			System.out.println("      Caminho do diret�rio Web:       " + lConfig.getCaminhoWeb());
			System.out.println("      Caminho do diret�rio Resources: " + lConfig.getCaminhoResources());
			System.out.println("");

			List<ClasseConfig> lListClasseModel = FileUtil.obterListaClassesModelArquivoProperties();

			for (ClasseConfig lClasseModel : lListClasseModel) {

				System.out.println("");
				System.out.println("=====================================================================================");
				System.out.println("");
				System.out.println(" CLASSE PARA PROCESSAMENTO: " + lClasseModel.getClasse().getName());
				System.out.println(" LOGICA: " + lClasseModel.getLogicaTela().name());
				System.out.println("");
				
				System.out.println("      VALIDANDO CLASSE...");
				System.out.println("           Validar se todas as depend�ncias est�o no classloader ");

				lClasseModel.getClasse().getDeclaredFields(); // Validar se todas as dependencias est�o no classloader

				System.out.println("           Valida��o conclu�da com sucesso.");
				System.out.println("");

				System.out.println("      GERANDO ARQUIVO XHTML...");

				GerarXHTML.gerarArquivoXhtml(lConfig, lClasseModel);

				System.out.println("           Gera��o conclu�da com sucesso.");
				System.out.println("");

				System.out.println("      GERANDO PROPRIEDADES...");

				GerarProperties.gerarArquivoProperties(lConfig, lClasseModel);

				System.out.println("           Gera��o conclu�da com sucesso.");
				System.out.println("");

				System.out.println("      GERANDO MANAGED BEAN...");

				GerarManagedBean.gerarManagedBean(lConfig, lClasseModel);

				System.out.println("           Gera��o conclu�da com sucesso.");
				System.out.println("");

			}

			System.out.println("=====================================================================================");
			System.out.println("===                                  FINALIZADO                                   ===");
			System.out.println("=====================================================================================");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

	}
}
