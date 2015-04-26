package com.archtecture.model.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class ModelAb implements Serializable {

	@Deprecated
	public abstract String[] getAtributosOrdencao();

	public abstract Long getCodigo();
	
	public String getLabel(){
		return toString();
	}
}
