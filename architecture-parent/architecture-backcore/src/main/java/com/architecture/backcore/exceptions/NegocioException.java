package com.architecture.backcore.exceptions;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.PersistenceException;

import lombok.Getter;

public class NegocioException extends Exception {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7991200126392598000L;

	@Getter
	private final String code;
	
	/**
	 * Contrutor utilizado para converter um exception em um CODE conhecido pelo sistema.
	 * 
	 * @param e excessao a ser avaliada e convertida em um CODE
	 * 
	 */
	public NegocioException(Throwable e) {
		super(e);
		this.code = obterMensagemException(this);
	}
	
	private static String obterMensagemException(Throwable throwable) {

		if (throwable instanceof EJBTransactionRolledbackException) {
			if (throwable.getCause().getCause() instanceof PersistenceException) {

				// e.getCause().getCause().getCause().getCause().getClass().getClassLoader();
				// MySQLIntegrityConstraintViolationException.class.getClassLoader();

				if (throwable.getCause().getCause().getCause().getCause().getClass().getName()
						.equals("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException")) {

					try {
						String lSQLState = (String) throwable.getCause().getCause().getCause().getCause().getClass().getMethod("getSQLState")
								.invoke(throwable.getCause().getCause().getCause().getCause());

						if (lSQLState == "23000") {
							if (throwable.getCause().getCause().getCause().getCause().getMessage().contains("FKCOMPRA_FORNECEDOR")) {
								return "";
							}
						}
					}
					catch(Exception ex){
						
					}
				}
			}
		}

		return throwable.getCause().getCause().getCause().getCause().getMessage();
	}
}
