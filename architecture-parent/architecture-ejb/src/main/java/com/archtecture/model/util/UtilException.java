package com.archtecture.model.util;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.PersistenceException;

public class UtilException {

	public UtilException() {
		// TODO Auto-generated constructor stub
	}

	public static String obterMensagemException(Exception e) {

		if (e instanceof EJBTransactionRolledbackException) {
			if (e.getCause().getCause() instanceof PersistenceException) {

				// e.getCause().getCause().getCause().getCause().getClass().getClassLoader();
				// MySQLIntegrityConstraintViolationException.class.getClassLoader();

				if (e.getCause().getCause().getCause().getCause().getClass().getName()
						.equals("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException")) {

					try {
						String lSQLState = (String) e.getCause().getCause().getCause().getCause().getClass().getMethod("getSQLState")
								.invoke(e.getCause().getCause().getCause().getCause());

						if (lSQLState == "23000") {
							if (e.getCause().getCause().getCause().getCause().getMessage().contains("FKCOMPRA_FORNECEDOR")) {
								return "";
							}
						}
					}
					catch(Exception ex){
						
					}
				}
			}
		}

		return e.getCause().getCause().getCause().getCause().getMessage();
	}

}
