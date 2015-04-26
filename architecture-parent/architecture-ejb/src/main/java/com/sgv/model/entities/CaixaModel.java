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
import javax.persistence.Transient;

import com.archtecture.model.entities.ModelAb;

@Entity
@Table(name = "TBCAIXA", schema="sgvdev")
public class CaixaModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4530576467403798147L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_CAIXA", length = 11, nullable = false, unique = true)
	private Long codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ABERTURA", nullable = false)
	private Date dataAbertura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_FECHAMENTO")
	private Date dataFechamento;

	@Column(name = "VL_CAIXA_INICIAL", length = 9, nullable = false)
	private Double vlrCaixaInicial;

	@Column(name = "VL_CAIXA_FECHAMENTO", length = 9)
	private Double vlrCaixaFechamento;

	@Column(name = "VL_DEPOSITADO", length = 9)
	private Double vlrDepositado;

	@Column(name = "VL_RETIRADA_AVULSA", length = 9)
	private Double vlrRetiradaAvulsa;

	@Column(name = "VL_ARRECADADO", length = 9)
	private Double vlrArrecadado;

	@Column(name = "DS_MOTIVO_RETIRADA", length = 500)
	private String motivoRetirada;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_USUARIO_ABERTURA")
	private UsuarioModel responsavelAbertura;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_USUARIO_FECHAMENTO")
	private UsuarioModel responsavelFechamento;

	@Column(name = "VL_VENDA_ATUAL", length = 9)
	private Double vlrVendaAtual;

	@Transient
	private Double vlrSaldoEmCaixa;

	@Transient
	private Double vlrTotal;

	@Override
	public String[] getAtributosOrdencao() {
		return new String[] { "dataAbertura" };
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	public Double getVlrTotal() {

		Double lValorDeposito = getVlrDepositado() != null ? getVlrDepositado() : 0D;
		Double lValorRetiradaAvulsa = getVlrRetiradaAvulsa() != null ? getVlrRetiradaAvulsa() : 0D;

		return vlrTotal = getVlSaldoEmCaixa() - lValorDeposito - lValorRetiradaAvulsa;
	}

	public Double getVlSaldoEmCaixa() {

		Double lValorVendaAtual = getVlrVendaAtual() != null ? getVlrVendaAtual() : 0D;
		Double lValorCaixaInicial = getVlrCaixaInicial() != null ? getVlrCaixaInicial() : 0D;

		return vlrSaldoEmCaixa = lValorVendaAtual + lValorCaixaInicial;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Double getVlrCaixaInicial() {
		return vlrCaixaInicial;
	}

	public void setVlrCaixaInicial(Double vlrCaixaInicial) {
		this.vlrCaixaInicial = vlrCaixaInicial;
	}

	public Double getVlrCaixaFechamento() {
		return vlrCaixaFechamento;
	}

	public void setVlrCaixaFechamento(Double vlrCaixaFechamento) {
		this.vlrCaixaFechamento = vlrCaixaFechamento;
	}

	public Double getVlrDepositado() {
		return vlrDepositado;
	}

	public void setVlrDepositado(Double vlrDepositado) {
		this.vlrDepositado = vlrDepositado;
	}

	public Double getVlrRetiradaAvulsa() {
		return vlrRetiradaAvulsa;
	}

	public void setVlrRetiradaAvulsa(Double vlrRetiradaAvulsa) {
		this.vlrRetiradaAvulsa = vlrRetiradaAvulsa;
	}

	public Double getVlrArrecadado() {
		return vlrArrecadado;
	}

	public void setVlrArrecadado(Double vlrArrecadado) {
		this.vlrArrecadado = vlrArrecadado;
	}

	public String getMotivoRetirada() {
		return motivoRetirada;
	}

	public void setMotivoRetirada(String motivoRetirada) {
		this.motivoRetirada = motivoRetirada;
	}

	public UsuarioModel getResponsavelAbertura() {
		return responsavelAbertura;
	}

	public void setResponsavelAbertura(UsuarioModel responsavelAbertura) {
		this.responsavelAbertura = responsavelAbertura;
	}

	public UsuarioModel getResponsavelFechamento() {
		return responsavelFechamento;
	}

	public void setResponsavelFechamento(UsuarioModel responsavelFechamento) {
		this.responsavelFechamento = responsavelFechamento;
	}

	public Double getVlrVendaAtual() {
		return vlrVendaAtual;
	}

	public void setVlrVendaAtual(Double vlrVendaAtual) {
		this.vlrVendaAtual = vlrVendaAtual;
	}

	public Double getVlrSaldoEmCaixa() {
		return vlrSaldoEmCaixa;
	}

	public void setVlrSaldoEmCaixa(Double vlrSaldoEmCaixa) {
		this.vlrSaldoEmCaixa = vlrSaldoEmCaixa;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setVlrTotal(Double vlrTotal) {
		this.vlrTotal = vlrTotal;
	}

}