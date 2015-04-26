package com.sgv.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.archtecture.model.entities.ModelAb;

@Entity
@Table(name = "TBPRODUTO", schema="sgvdev")
public class ProdutoModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078139870185089611L;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_PRODUTO", length = 20, nullable = false, unique = true)
	private Long codigo;

	// @Column(name = "CD_BARRAS", length = 50, nullable = false, unique = true)
	// private Long codigoBarras;

	@Column(name = "VL_LUCRO", length = 5)
	private Double lucro;

	@Column(name = "NM_PRODUTO", length = 100, nullable = false, unique = true)
	private String nome;

	@Column(name = "VL_CUSTO", length = 6)
	private Double valorCusto;

	@Column(name = "VL_VENDA", length = 6)
	private Double valorVenda;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_CATEGORIA")
	private CategoriaModel categoria;

	@Column(name = "QT_ESTOQUE", length = 4)
	private Integer quantidadeEstoque;

	@Column(name = "FL_PRE_CADASTRO")
	private String flPreCadastro;

	@Transient
	private boolean possuiNovoValor;

	@Override
	public String[] getAtributosOrdencao() {
		return new String[] { "nome" };
	}

	public CategoriaModel getCategoria() {
		if (categoria == null) {
			categoria = new CategoriaModel();
		}
		return categoria;
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	// public Long getCodigoBarras() {
	// return codigoBarras;
	// }

	public Double getLucro() {
		return lucro;
	}

	public String getNome() {
		return nome;
	}

	public Double getValorCusto() {
		return valorCusto;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	// public void setCodigoBarras(Long codigoBarras) {
	// this.codigoBarras = codigoBarras;
	// }

	public void setLucro(Double lucro) {
		this.lucro = lucro;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setValorCusto(Double valorCusto) {
		this.valorCusto = valorCusto;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoModel other = (ProdutoModel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public boolean isPossuiNovoValor() {
		return possuiNovoValor;
	}

	public void setPossuiNovoValor(boolean possuiNovoValor) {
		this.possuiNovoValor = possuiNovoValor;
	}

	public String getFlPreCadastro() {
		return flPreCadastro;
	}

	public void setFlPreCadastro(String flPreCadastro) {
		this.flPreCadastro = flPreCadastro;
	}

}