package com.exemplo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.archtecture.model.entities.ModelAb;

@Entity
@Table(name = "TBPAPEL", schema="sgvdev")
public class PapelModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6972218042212900381L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_ROLE")
	private Long codigo;

	@Column(name = "NM_ROLE", length = 50, nullable = false, unique = true)
	private String nome;

	@Override
	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String getLabel() {
		return getNome();
	}

}
