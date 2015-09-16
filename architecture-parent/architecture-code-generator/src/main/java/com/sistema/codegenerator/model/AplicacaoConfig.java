package com.sistema.codegenerator.model;

public class AplicacaoConfig {

	private String caminhoAbsoluto;

	private String caminhoJava;

	private String caminhoResources;

	private String caminhoWeb;

	private String caminhoLabel;

	public AplicacaoConfig() {
	}

	public String getCaminhoAbsoluto() {
		return caminhoAbsoluto;
	}

	public String getCaminhoJava() {
		return caminhoJava;
	}

	public String getCaminhoLabel() {
		return caminhoLabel;
	}

	public String getCaminhoResources() {
		return caminhoResources;
	}

	public String getCaminhoWeb() {
		return caminhoWeb;
	}

	public void setCaminhoAbsoluto(String caminhoAbsoluto) {
		this.caminhoAbsoluto = caminhoAbsoluto;
	}

	public void setCaminhoJava(String caminhoJava) {
		this.caminhoJava = caminhoJava;
	}

	public void setCaminhoLabel(String caminhoLabel) {
		this.caminhoLabel = caminhoLabel;
	}

	public void setCaminhoResources(String caminhoResources) {
		this.caminhoResources = caminhoResources;
	}

	public void setCaminhoWeb(String caminhoWeb) {
		this.caminhoWeb = caminhoWeb;
	}

}
