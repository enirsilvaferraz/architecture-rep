package com.sistema.codegenerator.model;

import java.lang.reflect.Field;

public class CampoConfig {

	private String nome;

	private Boolean obrigatorioCadastro;

	private Boolean obrigatorioPesquisa;

	private Integer tamanho;

	private Boolean monetario;

	// Usado para carregar enuns
	private Field field;

	public CampoConfig() {
	}

	public CampoConfig(String name) {
		this.nome = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CampoConfig other = (CampoConfig) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public Field getField() {
		return field;
	}

	public Boolean getMonetario() {
		return monetario;
	}

	public String getNome() {
		return nome;
	}

	public Boolean getObrigatorioCadastro() {
		return obrigatorioCadastro;
	}

	public Boolean getObrigatorioPesquisa() {
		return obrigatorioPesquisa;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public void setMonetario(Boolean monetario) {
		this.monetario = monetario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setObrigatorioCadastro(Boolean obrigatorioCadastro) {
		this.obrigatorioCadastro = obrigatorioCadastro;
	}

	public void setObrigatorioPesquisa(Boolean obrigatorioPesquisa) {
		this.obrigatorioPesquisa = obrigatorioPesquisa;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

}
