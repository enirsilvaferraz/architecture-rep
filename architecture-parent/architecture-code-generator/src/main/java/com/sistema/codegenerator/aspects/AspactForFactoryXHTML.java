package com.sistema.codegenerator.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspactForFactoryXHTML {

	@Pointcut("execution(* com.sistema.codegenerator.main.FactoryXHTML.gerar (..))")
	public void definePointGerar() {
	}

	@Before(value = "definePointGerar()")
	public void antesGerar() {
		System.out.println("      Executando geração dos Arquivos XHTML");
	}

	@After(value = "definePointGerar()")
	public void depoisGerar() {
		System.out.println("           Geração concluída com sucesso.");
		System.out.println("");
	}

}
