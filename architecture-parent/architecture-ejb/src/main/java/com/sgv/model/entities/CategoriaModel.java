package com.sgv.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.archtecture.control.models.ModelAb;

@Entity
@Table(name = "TBCATEGORIA")
public class CategoriaModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1932551937576372240L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_CATEGORIA", length = 11, nullable = false, unique = true)
	private Long codigo;

	@Column(name = "NM_CATEGORIA", length = 50, nullable = false, unique = true)
	private String nome;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaModel other = (CategoriaModel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String[] getAtributosOrdencao() {
		return new String[] { "nome" };
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getLabel() {
		return getNome();
	}

}
