package com.architecture.persistence.model.ejbs;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.architecture.backcore.exceptions.NegocioException;
import com.architecture.persistence.model.entities.ModelAb;
import com.architecture.persistence.model.enums.TipoOrdenacao;

// @Stateless(name = PersistenceFacadeLocal.JNDI_NAME)
// @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
// @TransactionManagement(TransactionManagementType.CONTAINER)
public class PersistenceFacadeBean implements PersistenceFacadeLocal {

	@PersistenceContext
	private EntityManager	manager;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <Model extends ModelAb> Model atualizar(Model pModel) throws NegocioException {
		try {
			return manager.merge(pModel);
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Model extends ModelAb> Model carregar(Model pModel) throws NegocioException {
		try {
			return (Model) manager.find(pModel.getClass(), pModel.getCodigo());
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <Model extends ModelAb> void excluir(Model pModel) throws NegocioException {
		try {
			manager.remove(carregar(pModel));
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <Model extends ModelAb> Model inserir(Model pModel) throws NegocioException {
		try {
			manager.persist(pModel);
			return pModel;
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Model extends ModelAb> List<Model> pesquisarLista(Model pModel, TipoOrdenacao tipoOrdenacao,
			String... atributosOrdenacao) throws NegocioException {

		try {
			
			String jpqlString = JPQLFactory.montarConsultaJPQLMultiplosResultados(pModel, tipoOrdenacao,
					atributosOrdenacao);
			return manager.createQuery(jpqlString).getResultList();
			
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	@Deprecated
	@Override
	public <Model extends ModelAb> Model pesquisarUnico(Model pModel, String... pCollectionsName)
			throws NegocioException {

		// String jpqlString = JPQLFactory.montarConsultaJPQLMultiplosResultados(pModel, TipoOrdenacao.ASC);
		//
		// Model lModel = (Model) manager.createQuery(jpqlString).getSingleResult();
		//
		// if (pCollectionsName != null) {
		//
		// for (String string : pCollectionsName) {
		// Hibernate.initialize(pModel.getClass().getMethod(string, (Class<?>[]) null)
		// .invoke(pModel, (Object[]) null));
		// }
		// }

		return null;
	}
}