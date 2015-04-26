package com.archtecture.model.exceptions;

import com.archtecture.model.enums.Mensagem;

public class NegocioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7991200126392598000L;

	private Mensagem mensagemErro;

	public NegocioException(Mensagem mensagemErro, Throwable e) {
		super(e);
		this.mensagemErro = mensagemErro;
	}

	public Mensagem getMensagemErro() {
		return mensagemErro;
	}
}
