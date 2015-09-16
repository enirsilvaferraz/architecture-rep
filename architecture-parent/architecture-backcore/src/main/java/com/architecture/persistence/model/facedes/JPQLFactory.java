package com.architecture.persistence.model.facedes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Transient;

import com.architecture.backcore.exceptions.NegocioException;
import com.architecture.persistence.model.entities.ModelAb;
import com.architecture.persistence.model.enums.TipoOrdenacao;

final class JPQLFactory {

	/**
	 * Método usado para realizar a montagem da parte da connsulta JPQL
	 * referente a associação
	 * 
	 * @param nomeObjeto
	 *            nome do objeto principal da consulta
	 * @param jpqlAssociacao
	 *            StringBuilder para montagem da parte da consulta referente as
	 *            associações
	 * @param field
	 *            atributo do ovbjeto
	 * @param nomeObjetoAb
	 *            nome do field
	 */
	private static void inserirClausulaJoinFetch(String nomeObjeto, StringBuilder jpqlAssociacao, Field field,
			String nomeObjetoAb) {
		jpqlAssociacao.append(" JOIN FETCH ");
		jpqlAssociacao.append(nomeObjeto);
		jpqlAssociacao.append(".");
		jpqlAssociacao.append(field.getName());
		jpqlAssociacao.append(" ");
		jpqlAssociacao.append(nomeObjetoAb);
		jpqlAssociacao.append(" ");
	}

	/**
	 * Metodo usado para adicionar os criterios de WHERE ou AND
	 * 
	 * @param jpqlWhere
	 *            StringBuilder para montagem da parte da consulta referente os
	 *            atributos do objeto principal
	 */
	private static void inserirClausulaWhereAnd(StringBuilder jpqlWhere) {

		if (!jpqlWhere.toString().contains("WHERE")) {
			jpqlWhere.append(" WHERE ");
		} else {
			jpqlWhere.append(" AND ");
		}
	}

	private static <Model> StringBuilder inserirClausuraOrderBy(Model pModel, String nomeObjeto,
			TipoOrdenacao tipoOrdenacao, String... atributosOrdenacao) throws Exception {

		StringBuilder jpqlOrderBy = new StringBuilder();

		if (atributosOrdenacao != null && atributosOrdenacao.length > 0) {
			jpqlOrderBy.append(" ORDER BY ");

			// String[] array = (String[]) obterValor(pModel,
			// "atributosOrdencao");
			int contItens = 0;
			for (String orderParam : atributosOrdenacao) {
				jpqlOrderBy.append(" ");
				jpqlOrderBy.append(nomeObjeto);
				jpqlOrderBy.append(".");
				jpqlOrderBy.append(orderParam);
				if (++contItens < atributosOrdenacao.length) {
					jpqlOrderBy.append(",");
				}
			}

			if (tipoOrdenacao != null) {
				jpqlOrderBy.append(" ").append(tipoOrdenacao.name());
			}

		}

		return jpqlOrderBy;
	}

	/**
	 * Método usado na montagem do corpo da consulta (SELECT)
	 * 
	 * @param pModel
	 *            Objeto principal da consulta
	 * @param nomeObjeto
	 *            nome padrão para esse objeto
	 * @param jpqlPrincipal
	 *            StringBuilder para montagem da parte da consulta referente ao
	 *            corpo principal
	 */
	private static <Model> StringBuilder inserirClausuraSelect(Model pModel, String nomeObjeto) {
		StringBuilder jpqlSelect = new StringBuilder();
		jpqlSelect.append("SELECT DISTINCT ");
		jpqlSelect.append(nomeObjeto);
		jpqlSelect.append(" FROM ");
		jpqlSelect.append(pModel.getClass().getSimpleName());
		jpqlSelect.append(" ");
		jpqlSelect.append(nomeObjeto);
		return jpqlSelect;
	}

	@SuppressWarnings("rawtypes")
	private static <Model> StringBuilder inserirClausuraWhere(Model pModel, String nomeObjeto) throws Exception {

		StringBuilder jpqlWhere = new StringBuilder();
		StringBuilder jpqlAssociacao = new StringBuilder();

		INICIO_FOR: for (Field field : pModel.getClass().getDeclaredFields()) {

			for (Annotation annot : field.getAnnotations()) {
				if (annot.annotationType().equals(Transient.class)) {
					continue INICIO_FOR;
				}
			}

			Object valor = obterValor(pModel, field);

			if (valor != null) {

				if (valor instanceof String) {
					if (!((String) valor).isEmpty()) {
						inserirParametroClausulaLike(jpqlWhere, nomeObjeto, field.getName(), valor);
					}
				}

				else if (valor instanceof Integer) {
					if (((Integer) valor) != 0) {
						inserirParametroGenerico(jpqlWhere, nomeObjeto, field.getName(), valor);
					}
				}

				else if (valor instanceof Double) {
					if (((Double) valor) != 0.0) {
						inserirParametroGenerico(jpqlWhere, nomeObjeto, field.getName(), valor);
					}
				}

				else if (valor instanceof Long) {
					if (((Long) valor) != 0) {
						inserirParametroGenerico(jpqlWhere, nomeObjeto, field.getName(), valor);
					}
				}

				else if (valor instanceof Date) {
					if (((Date) valor) != null) {
						inserirParametroData(jpqlWhere, nomeObjeto, field.getName(), ((Date) valor));
					}
				}

				else if (valor instanceof Collection<?>) {
					String nomeObjetoAb = valor.getClass().getSimpleName() + valor.hashCode();
					inserirClausulaJoinFetch(nomeObjeto, jpqlAssociacao, field, nomeObjetoAb);
				}

				else if (valor instanceof ModelAb) {
					String nomeObjetoAb = valor.getClass().getSimpleName() + valor.hashCode();
					inserirClausulaJoinFetch(nomeObjeto, jpqlAssociacao, field, nomeObjetoAb);

					if (valor != null && ((ModelAb) valor).getCodigo() != null && ((ModelAb) valor).getCodigo() != 0) {
						inserirParametroGenerico(jpqlWhere, nomeObjetoAb, "codigo", ((ModelAb) valor).getCodigo());
					}
				}

				else if (valor instanceof Enum) {
					inserirParametroGenerico(jpqlWhere, nomeObjeto, field.getName(), ((Enum) valor).ordinal());
				}

				else {
					inserirParametroGenerico(jpqlWhere, nomeObjeto, field.getName(), valor);
				}
			}
		}

		jpqlAssociacao.append(" ");
		jpqlAssociacao.append(jpqlWhere.toString());

		return jpqlAssociacao;
	}

	/**
	 * Métod usado para inserir uma condição LIKE de pesquisa
	 * 
	 * @param jpqlWhere
	 *            para montagem da parte da consulta referente as clausulas
	 *            WHERE
	 * @param nomeObjeto
	 *            nome do objeto principal
	 * @param nomefield
	 *            nome do campo em uso
	 * @param valor
	 *            valor da variável
	 */
	private static void inserirParametroClausulaLike(StringBuilder jpqlWhere, String nomeObjeto, String nomefield,
			Object valor) {

		inserirClausulaWhereAnd(jpqlWhere);
		jpqlWhere.append(nomeObjeto).append(".").append(nomefield);
		jpqlWhere.append(" LIKE '%").append(valor).append("%' ");

	}

	/**
	 * Métod usado para inserir uma condição genérica de pesquisa
	 * 
	 * @param jpqlWhere
	 *            para montagem da parte da consulta referente as clausulas
	 *            WHERE
	 * @param nomeObjeto
	 *            nome do objeto principal
	 * @param nomefield
	 *            nome do campo em uso
	 * @param valor
	 *            valor da variável
	 */
	private static void inserirParametroGenerico(StringBuilder jpqlWhere, String nomeObjeto, String nomefield,
			Object valor) {

		inserirClausulaWhereAnd(jpqlWhere);
		jpqlWhere.append(nomeObjeto).append(".").append(nomefield).append(" = ").append(valor);
	}

	/**
	 * Métod usado para inserir uma condição de DATE de pesquisa
	 * 
	 * @param jpqlWhere
	 *            para montagem da parte da consulta referente as clausulas
	 *            WHERE
	 * @param nomeObjeto
	 *            nome do objeto principal
	 * @param nomefield
	 *            nome do campo em uso
	 * @param valor
	 *            valor da variável
	 */
	private static void inserirParametroData(StringBuilder jpqlWhere, String nomeObjeto, String nomefield, Date valor) {

		inserirClausulaWhereAnd(jpqlWhere);
		jpqlWhere.append(nomeObjeto).append(".").append(nomefield).append(" BETWEEN ").append("'")
				.append(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(valor)).append("' AND '")
				.append(new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(valor)).append("'");
	}

	/**
	 * Monta uma string JPQL a partir de um model
	 * 
	 * @param pModel
	 *            modelo base para montagem da consulta
	 * @param atributosOrdenacao
	 * @throws NegocioException
	 *             ERRO_GERAL
	 * @return JPQL montado
	 */
	public static <Model> String montarConsultaJPQLMultiplosResultados(Model pModel, TipoOrdenacao tipoOrdenacao,
			String... atributosOrdenacao) throws Exception {

		String nomeObjeto = pModel.getClass().getSimpleName() + Math.abs(pModel.hashCode());

		StringBuilder jpqlSelect = inserirClausuraSelect(pModel, nomeObjeto);
		StringBuilder jpqlWhere = inserirClausuraWhere(pModel, nomeObjeto);
		StringBuilder jpqlOrderBy = inserirClausuraOrderBy(pModel, nomeObjeto, tipoOrdenacao, atributosOrdenacao);

		return jpqlSelect.toString() + jpqlWhere.toString() + jpqlOrderBy.toString();

	}

	/**
	 * Monta uma string JPQL a partir de um model
	 * 
	 * @param pModel
	 *            modelo base para montagem da consulta
	 * @throws NegocioException
	 *             ERRO_GERAL
	 * @return JPQL montado
	 */
	public static <Model> String montarConsultaJPQLResultadoUnico(Model pModel) throws Exception {

		String nomeObjeto = pModel.getClass().getSimpleName() + pModel.hashCode();
		StringBuilder jpqlSelect = inserirClausuraSelect(pModel, nomeObjeto);

		StringBuilder jpqlWhere = new StringBuilder();
		StringBuilder jpqlAssociacao = new StringBuilder();

		INICIO_FOR: for (Field field : pModel.getClass().getDeclaredFields()) {

			for (Annotation annot : field.getAnnotations()) {
				if (annot.annotationType().equals(Transient.class)) {
					continue INICIO_FOR;
				}
			}

			Object valor = obterValor(pModel, field);
			if (valor instanceof Collection<?>) {
				String nomeObjetoAb = field.getName() + field.getName().hashCode();
				inserirClausulaJoinFetch(nomeObjeto, jpqlAssociacao, field, nomeObjetoAb);
				if (valor != null && ((ModelAb) valor).getCodigo() != null && ((ModelAb) valor).getCodigo() != 0) {
					inserirParametroGenerico(jpqlWhere, nomeObjetoAb, "codigo", valor);
				}
			}
		}

		inserirParametroGenerico(jpqlWhere, nomeObjeto, "codigo", obterValor(pModel, "codigo"));

		String jpqlString = jpqlSelect.toString() + jpqlAssociacao.toString() + jpqlWhere.toString();
		return jpqlString;

	}

	/**
	 * Método usado para obter o valor a partir de um field
	 * 
	 * @throws NegocioException
	 *             ERRO_GERAL
	 */
	private static <Model> Object obterValor(Model pModel, Field pField) throws Exception {

		if (pField.getName().equals("serialVersionUID")) {
			return null;
		}

		String nomeMetodoGet = "get" + pField.getName().substring(0, 1).toUpperCase() + pField.getName().substring(1);
		return pModel.getClass().getMethod(nomeMetodoGet, (Class<?>[]) null).invoke(pModel, (Object[]) null);

	}

	/**
	 * Método usado para obter o valor a partir de um nome de field
	 * 
	 * @throws NegocioException
	 *             ERRO_GERAL
	 */
	private static <Model> Object obterValor(Model pModel, String pFieldName) throws Exception {

		String nomeMetodoGet = "get" + pFieldName.substring(0, 1).toUpperCase() + pFieldName.substring(1);
		return pModel.getClass().getMethod(nomeMetodoGet, (Class<?>[]) null).invoke(pModel, (Object[]) null);

	}
}
