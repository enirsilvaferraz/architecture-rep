package com.sistema.codegenerator.model;

import java.util.ArrayList;
import java.util.List;

import com.sistema.codegenerator.model.enums.LogicaTela;

public class ClasseConfig {

	private Class<?> classe;

	private List<CampoConfig> listCampo;

	private String pacoteManagedBean;

	private LogicaTela logicaTela;

	private String nomeModel;

	private String nomeModelProperties;

	private String nomeModelMB;

	private String nomeClasseMB;

	public ClasseConfig() {
		listCampo = new ArrayList<CampoConfig>();
	}

	public Class<?> getClasse() {
		return classe;
	}

	public List<CampoConfig> getListCampo() {
		return listCampo;
	}

	public LogicaTela getLogicaTela() {
		return logicaTela;
	}

	public String getNomeClasseMB() {

		nomeClasseMB = getNomeModelMB().substring(0, 1).toUpperCase() + getNomeModelMB().substring(1);

		return nomeClasseMB;
	}

	public String getNomeModel() {

		nomeModel = getClasse().getSimpleName().replace("Model", "").substring(0, 1).toLowerCase()
				+ getClasse().getSimpleName().replace("Model", "").substring(1) + getLogicaTela().getExtesaoTela().substring(0, 1).toUpperCase()
				+ getLogicaTela().getExtesaoTela().substring(1);

		return nomeModel;
	}

	public String getNomeModelMB() {

		nomeModelMB = getNomeModel() + "MB";

		return nomeModelMB;
	}

	public String getNomeModelProperties() {

		nomeModelProperties = "";

		String lNomeClasse = classe.getSimpleName().replace("Model", "");
		lNomeClasse = lNomeClasse.substring(0, 1).toLowerCase() + lNomeClasse.substring(1);

		for (int i = 0; i < lNomeClasse.toCharArray().length; i++) {

			char letra = lNomeClasse.toCharArray()[i];

			if (Character.isUpperCase(letra)) {
				nomeModelProperties += "_";
				nomeModelProperties += letra;
			} else {
				nomeModelProperties += letra;
			}
		}

		return nomeModelProperties;
	}

	public String getPacoteManagedBean() {
		return pacoteManagedBean;
	}

	public void setClasse(Class<?> classe) {
		this.classe = classe;
	}

	public void setListCampo(List<CampoConfig> listCampo) {
		this.listCampo = listCampo;
	}

	public void setLogicaTela(LogicaTela logicaTela) {
		this.logicaTela = logicaTela;
	}

	public void setPacoteManagedBean(String pacoteManagedBean) {
		this.pacoteManagedBean = pacoteManagedBean;
	}

}
