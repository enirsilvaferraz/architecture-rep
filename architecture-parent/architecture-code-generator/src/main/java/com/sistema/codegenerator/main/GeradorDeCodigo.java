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
			System.out.println("===                           INICIAR GERAÇÃO DE CÓDIGO                           ===");
			System.out.println("=====================================================================================");
			System.out.println("");
			System.out.println("");

			AplicacaoConfig lConfig = FileUtil.obterConfiguracaoArquivoProperties();

			System.out.println(" PARAMETROS DE CONFIGURAÇÃO");
			System.out.println("");
			System.out.println("      Caminho da aplicação:           " + lConfig.getCaminhoAbsoluto());
			System.out.println("      Caminho do diretório Java:      " + lConfig.getCaminhoJava());
			System.out.println("      Caminho do diretório Web:       " + lConfig.getCaminhoWeb());
			System.out.println("      Caminho do diretório Resources: " + lConfig.getCaminhoResources());
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
				System.out.println("           Validar se todas as dependências estão no classloader ");

				lClasseModel.getClasse().getDeclaredFields(); // Validar se todas as dependencias estão no classloader

				System.out.println("           Validação concluída com sucesso.");
				System.out.println("");

				System.out.println("      GERANDO ARQUIVO XHTML...");

				GerarXHTML.gerarArquivoXhtml(lConfig, lClasseModel);

				System.out.println("           Geração concluída com sucesso.");
				System.out.println("");

				System.out.println("      GERANDO PROPRIEDADES...");

				GerarProperties.gerarArquivoProperties(lConfig, lClasseModel);

				System.out.println("           Geração concluída com sucesso.");
				System.out.println("");

				System.out.println("      GERANDO MANAGED BEAN...");

				GerarManagedBean.gerarManagedBean(lConfig, lClasseModel);

				System.out.println("           Geração concluída com sucesso.");
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
