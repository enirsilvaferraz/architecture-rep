package com.sgv.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.archtecture.model.entities.ModelAb;

@Entity
@Table(name = "TBITEM_COMPRA", schema="sgvdev")
public class ItemCompraModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7321303070239180659L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_ITEM_COMPRA", length = 11, nullable = false, unique = true)
	private Long codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_COMPRA")
	private CompraModel compra;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_PRODUTO")
	private ProdutoModel produto;

	@Column(name = "NR_QUANTIDADE_COMPRADA", length = 9)
	private Integer quantidadeComprada;

	@Override
	public String[] getAtributosOrdencao() {
		return new String[] { "produto", "compra" };
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	public CompraModel getCompra() {
		return compra;
	}

	public ProdutoModel getProduto() {
		return produto;
	}

	public Integer getQuantidadeComprada() {
		return quantidadeComprada;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setCompra(CompraModel compra) {
		this.compra = compra;
	}

	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}

	public void setQuantidadeComprada(Integer quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}

}
