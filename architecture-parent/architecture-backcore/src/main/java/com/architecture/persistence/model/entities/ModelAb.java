package com.architecture.persistence.model.entities;

import java.io.Serializable;

public abstract class ModelAb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4601241836318767159L;

	public abstract Long getCodigo();
	
	public abstract void setCodigo(Long id);
	
	public String getLabel(){
		return toString();
	}
}
