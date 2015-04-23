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

import com.archtecture.control.models.ModelAb;

@Entity
@Table(name = "TBITEM_VENDA")
public class ItemVendaModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7321303070239180659L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_ITEM_VENDA", length = 11, nullable = false, unique = true)
	private Long codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_VENDA")
	private VendaModel venda;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_PRODUTO")
	private ProdutoModel produto;

	@Column(name = "NR_QUANTIDADE_VENDIDA", length = 9)
	private Integer quantidadeVendida;

	@Override
	public String[] getAtributosOrdencao() {
		return null;
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	public VendaModel getVenda() {
		return venda;
	}

	public void setVenda(VendaModel venda) {
		this.venda = venda;
	}

	public ProdutoModel getProduto() {
		return produto;
	}

	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}

	public Integer getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(Integer quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getTotalVenda() {
		if (getProduto() != null && getProduto().getValorVenda() != null && getQuantidadeVendida() != null) {
			return getProduto().getValorVenda() * getQuantidadeVendida();
		}
		return null;
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
		ItemVendaModel other = (ItemVendaModel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		}
		else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}