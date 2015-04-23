package com.sgv.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.archtecture.control.models.ModelAb;

@Entity
@Table(name = "TBVENDA")
public class VendaModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2992659432627557993L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_VENDA")
	private Long codigo;

	@Column(name = "VL_VENDA", nullable = false, length = 10, precision = 2)
	private Double vlTotalVenda;

	@Column(name = "VL_PAGO", nullable = false, length = 10, precision = 2)
	private Double vlPagamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_INICIO", nullable = false)
	private Date dtInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_FIM", nullable = false)
	private Date dtFim;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_ATENDENTE")
	private UsuarioModel atendente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_CAIXA")
	private CaixaModel caixa;

	@OneToMany(mappedBy = "venda")
	private List<ItemVendaModel> listItemVenda;

	@Transient
	private Double vlTroco;

	@Override
	public String[] getAtributosOrdencao() {
		return null;
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<ItemVendaModel> getListItemVenda() {
		return listItemVenda;
	}

	public void setListItemVenda(List<ItemVendaModel> listItemVenda) {
		this.listItemVenda = listItemVenda;
	}

	public Double getVlTotalVenda() {
		// TODO Verificar
//		vlTotalVenda = 0D;
//		for (ItemVendaModel lItem : getListItemVenda()) {
//			vlTotalVenda += lItem.getProduto().getValorVenda() * lItem.getQuantidadeVendida();
//		}
		return vlTotalVenda;
	}

	public void setVlTotalVenda(Double vlTotalVenda) {
		this.vlTotalVenda = vlTotalVenda;
	}

	public Double getVlPagamento() {
		return vlPagamento;
	}

	public void setVlPagamento(Double vlPagamento) {
		this.vlPagamento = vlPagamento;
	}

	public Double getVlTroco() {
		if (getVlTotalVenda() != null && getVlPagamento() != null) {
			vlTroco = getVlTotalVenda() - getVlPagamento();
		}
		return vlTroco;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public UsuarioModel getAtendente() {
		return atendente;
	}

	public void setAtendente(UsuarioModel atendente) {
		this.atendente = atendente;
	}

	public CaixaModel getCaixa() {
		return caixa;
	}

	public void setCaixa(CaixaModel caixa) {
		this.caixa = caixa;
	}

}
