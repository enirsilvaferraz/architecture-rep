package com.sistema.codegenerator.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.sistema.codegenerator.model.AplicacaoConfig;
import com.sistema.codegenerator.model.ClasseConfig;

@Aspect
public class AspectForLog {

	@Around("execution(* com.sistema.codegenerator.main.GeradorDeCodigo.main (..))")
	public void main(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("=====================================================================================");
		System.out.println("===                           INICIAR GERA��O DE C�DIGO                           ===");
		System.out.println("=====================================================================================");
		System.out.println("");
		System.out.println("");

		joinPoint.proceed();

		System.out.println("=====================================================================================");
		System.out.println("===                                  FINALIZADO                                   ===");
		System.out.println("=====================================================================================");
	}

	@Around("execution(* com.sistema.codegenerator.main.GeradorDeCodigo.processarClasse (..)) && args(lConfig,lClasseModel)")
	public void processarClasse(ProceedingJoinPoint joinPoint, AplicacaoConfig lConfig, ClasseConfig lClasseModel)
			throws Throwable {

		System.out.println("");
		System.out.println("=====================================================================================");
		System.out.println("");
		System.out.println(" CLASSE PARA PROCESSAMENTO: " + lClasseModel.getClasse().getName());
		System.out.println(" LOGICA: " + lClasseModel.getLogicaTela().name());
		System.out.println("");
		
		joinPoint.proceed();
	}
	
	@Around("execution(* com.sistema.codegenerator.main.*.gerar (..)) && args(lConfig,lClasseModel)")
	public void gerar(ProceedingJoinPoint joinPoint, AplicacaoConfig lConfig, ClasseConfig lClasseModel)
			throws Throwable {

		System.out.println("      Executando gera��o de "
				+ joinPoint.getSourceLocation().getFileName().replace(".java", ""));

		joinPoint.proceed();

		System.out.println("           Gera��o conclu�da com sucesso.");
		System.out.println("");
	}
	
	@Around("execution(* com.sistema.codegenerator.util.FileUtil.obterConfiguracaoArquivoProperties (..))")
	public AplicacaoConfig obterConfiguracaoArquivoProperties(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println(" PARAMETROS DE CONFIGURA��O");
		System.out.println("");

		AplicacaoConfig fieldValue = (AplicacaoConfig) joinPoint.proceed();

		System.out.println("      Caminho da aplica��o:           " + fieldValue.getCaminhoAbsoluto());
		System.out.println("      Caminho do diret�rio Java:      " + fieldValue.getCaminhoJava());
		System.out.println("      Caminho do diret�rio Web:       " + fieldValue.getCaminhoWeb());
		System.out.println("      Caminho do diret�rio Resources: " + fieldValue.getCaminhoResources());
		System.out.println("");

		return fieldValue;
	}
}