package com.archtecture.model.facedes;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import com.archtecture.model.entities.ModelAb;
import com.archtecture.model.enums.TipoOrdenacao;
import com.archtecture.model.exceptions.NegocioException;

@Remote
public interface PersistenceFacadeLocal {

	/**
	 * M�todo usado para atualiza��o de uma entidade no banco de dados.
	 * 
	 * @param pModel
	 *            modelo a ser atualizado
	 * @throws NegocioException
	 *             ERRO_ENTIDADE_SEM_CODIGO
	 * @return model atualizado
	 */
	<Model extends ModelAb> Model atualizar(Model pModel) throws Exception;

	/**
	 * M�todo usado para buscar um modelo com base no c�digo
	 * 
	 * @param pModel
	 *            modelo a ser carregado
	 * @throws NegocioException
	 *             [NENUM]
	 * @return modelo carregado
	 */
	<Model extends ModelAb> Model carregar(Model pModel) throws Exception;

	/**
	 * M�todo usado para remover um modelo da base de dados.
	 * 
	 * @param pModel
	 *            modelo a ser exclu�do
	 * @throws NegocioException
	 *             [NENUM]
	 */
	<Model extends ModelAb> void excluir(Model pModel) throws Exception;

	/**
	 * M�todo usado para persistir um modelo no banco de dados
	 * 
	 * @param pModel
	 *            modelo para inser��o
	 * @throws NegocioException
	 *             ERRO_ENTIDADE_COM_CODIGO
	 * @return modelo inserido
	 */
	<Model extends ModelAb> Model inserir(Model pModel) throws Exception;

	/**
	 * M�todo usado para realizar uma pesquisa com base nos atributos de um
	 * determinado modelo
	 * 
	 * @param pModel
	 *            modelo a ser pesquisado
	 * @throws NegocioException
	 *             ERRO_GERAL
	 * @return lista de objetos pesquisados
	 */
	<Model extends ModelAb> List<Model> pesquisarLista(Model pModel, TipoOrdenacao tipoOrdenacao,
			String... atributosOrdenacao) throws Exception;

	/**
	 * M�todo usado para realizar uma pesquisa com base nos atributos de um
	 * determinado modelo
	 * 
	 * @param pModel
	 *            modelo a ser pesquisado
	 * @param pCollectionsName
	 *            cole��o de nomes de metodos para inicializa��o
	 * @throws NegocioException
	 *             ERRO_GERAL, ERRO_MULTIPLOS_RESULTADOS_NA_PESQUISA_UNICA
	 * @return Resultado �nico
	 */
	<Model extends ModelAb> Model pesquisarUnico(Model pModel, String... pCollectionsName) throws Exception;

	EntityManager getManager();

}
