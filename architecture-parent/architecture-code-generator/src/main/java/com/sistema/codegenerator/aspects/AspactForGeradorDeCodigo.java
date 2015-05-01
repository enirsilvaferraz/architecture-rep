package com.sistema.codegenerator.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.sistema.codegenerator.model.AplicacaoConfig;
import com.sistema.codegenerator.model.ClasseConfig;

@Aspect
public class AspactForGeradorDeCodigo {

	@Pointcut("execution(* com.sistema.codegenerator.main.GeradorDeCodigo.main (..))")
	public void definePointMain() {
	}

	@Before("definePointMain()")
	public void antesMain() {

		System.out.println("=====================================================================================");
		System.out.println("===                           INICIAR GERAÇÃO DE CÓDIGO                           ===");
		System.out.println("=====================================================================================");
		System.out.println("");
		System.out.println("");
	}

	@After("definePointMain()")
	public void depoisMain() {

		System.out.println("=====================================================================================");
		System.out.println("===                                  FINALIZADO                                   ===");
		System.out.println("=====================================================================================");
	}

	@Pointcut("execution(* com.sistema.codegenerator.main.GeradorDeCodigo.processarClasse (com.sistema.codegenerator.model.AplicacaoConfig,com.sistema.codegenerator.model.ClasseConfig))")
	public void definePointProcessarClasse() {
	}

	@Before(value = "definePointProcessarClasse() && args(lConfig,lClasseModel)", argNames = "lConfig,lClasseModel")
	public void processarClasse(AplicacaoConfig lConfig, ClasseConfig lClasseModel) {

		System.out.println("");
		System.out.println("=====================================================================================");
		System.out.println("");
		System.out.println(" CLASSE PARA PROCESSAMENTO: " + lClasseModel.getClasse().getName());
		System.out.println(" LOGICA: " + lClasseModel.getLogicaTela().name());
		System.out.println("");
	}

}
