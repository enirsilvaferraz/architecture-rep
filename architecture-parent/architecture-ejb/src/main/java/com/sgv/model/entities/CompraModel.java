package com.sgv.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.archtecture.model.entities.ModelAb;

@Entity
@Table(name = "TBCOMPRA", schema="sgvdev")
public class CompraModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3914907894285724080L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_COMPRA", length = 11, nullable = false, unique = true)
	private Long codigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_COMPRA", nullable = false)
	private Date data;

	@Column(name = "NR_NOTA_FISCAL", length = 50)
	private String numeroNotaFiscal;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_FORNECEDOR")
	private FornecedorModel fornecedor;

	@Override
	public String[] getAtributosOrdencao() {
		return new String[] { "data" };
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	public Date getData() {
		return data;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public FornecedorModel getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorModel fornecedor) {
		this.fornecedor = fornecedor;
	}

}
