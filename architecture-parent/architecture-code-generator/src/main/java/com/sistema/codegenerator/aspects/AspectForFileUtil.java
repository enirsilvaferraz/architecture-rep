package com.sistema.codegenerator.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.sistema.codegenerator.model.AplicacaoConfig;

@Aspect
public class AspectForFileUtil {

	@Pointcut("execution(* com.sistema.codegenerator.util.FileUtil.obterConfiguracaoArquivoProperties (..))")
	public void definePointObterConfiguracaoArquivoProperties() {
	}

	@Before(value = "definePointObterConfiguracaoArquivoProperties()")
	public void antes() throws Throwable {

		System.out.println(" PARAMETROS DE CONFIGURA��O");
		System.out.println("");
	}

	@AfterReturning(value = "definePointObterConfiguracaoArquivoProperties()", returning = "returning")
	public void depoisRetorno(AplicacaoConfig returning) {

		System.out.println("      Caminho da aplica��o:           " + returning.getCaminhoAbsoluto());
		System.out.println("      Caminho do diret�rio Java:      " + returning.getCaminhoJava());
		System.out.println("      Caminho do diret�rio Web:       " + returning.getCaminhoWeb());
		System.out.println("      Caminho do diret�rio Resources: " + returning.getCaminhoResources());
		System.out.println("");
	}

}
