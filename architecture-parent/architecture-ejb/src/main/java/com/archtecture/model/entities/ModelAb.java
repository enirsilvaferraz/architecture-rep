package com.archtecture.model.entities;

import java.io.Serializable;

public abstract class ModelAb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4601241836318767159L;

	@Deprecated
	public abstract String[] getAtributosOrdencao();

	public abstract Long getCodigo();
	
	public abstract void setCodigo(Long id);
	
	public String getLabel(){
		return toString();
	}
}
