package com.sistema.codegenerator.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspactForFactoryProperties {

	@AfterReturning(pointcut = "execution(* com.sistema.codegenerator.main.FactoryProperties.verificarExistenciaLabel (..)) && args(lSB,pLabel)", returning = "returning", argNames = "lSB,pLabel")
	public void verificarExistenciaLabel(Boolean returning, StringBuilder lSB, String pLabel) throws Throwable {

		if (returning) {
			System.out.println("           Label: " + pLabel + " (Adicionado)");
		} else {
			System.out.println("           Label: " + pLabel + " (Já no arquivo)");
		}

	}

	@Pointcut("execution(* com.sistema.codegenerator.main.FactoryProperties.gerar (..))")
	public void definePointGerar() {
	}

	@Before(value = "definePointGerar()")
	public void antesGerar() {
		System.out.println("      Executando geração dos Arquivos de Propriedades");
	}

	@After(value = "definePointGerar()")
	public void depoisGerar() {
		System.out.println("           Geração concluída com sucesso.");
		System.out.println("");
	}

}
