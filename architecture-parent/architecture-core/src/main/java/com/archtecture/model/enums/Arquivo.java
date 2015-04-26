package com.archtecture.model.enums;

public enum Arquivo {

	MENSAGENS("Mensagens"),
	LABELS("Labels");

	private String	nomeProperties;

	private Arquivo(String nomeProperties) {
		this.nomeProperties = nomeProperties;
	}

	public String getNomeProperties() {
		return nomeProperties;
	}
}
