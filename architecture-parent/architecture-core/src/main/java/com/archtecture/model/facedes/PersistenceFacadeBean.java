package com.archtecture.model.facedes;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.archtecture.model.entities.ModelAb;
import com.archtecture.model.enums.TipoOrdenacao;
import com.archtecture.model.util.JPQLFactory;

@Stateless(name = "PersistenceFacade")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersistenceFacadeBean implements PersistenceFacadeLocal {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public EntityManager getManager() {
		return manager;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <Model extends ModelAb> Model atualizar(Model pModel) throws Exception {
		return manager.merge(pModel);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Model extends ModelAb> Model carregar(Model pModel) throws Exception {

		// String jpqlString =
		// JPQLFactory.montarConsultaJPQLResultadoUnico(pModel);

		// return (Model) manager.createQuery(jpqlString).getSingleResult();
		return (Model) manager.find(pModel.getClass(), pModel.getCodigo());

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <Model extends ModelAb> void excluir(Model pModel) throws Exception {
		manager.remove(manager.find(pModel.getClass(), pModel.getCodigo()));
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <Model extends ModelAb> Model inserir(Model pModel) throws Exception {
		manager.persist(pModel);
		return pModel;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Model extends ModelAb> List<Model> pesquisarLista(Model pModel, TipoOrdenacao tipoOrdenacao,
			String... atributosOrdenacao) throws Exception {

		String jpqlString = JPQLFactory
				.montarConsultaJPQLMultiplosResultados(pModel, tipoOrdenacao, atributosOrdenacao);

		return manager.createQuery(jpqlString).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Model extends ModelAb> Model pesquisarUnico(Model pModel, String... pCollectionsName) throws Exception {

		String jpqlString = JPQLFactory.montarConsultaJPQLMultiplosResultados(pModel, TipoOrdenacao.ASC);

		Model lModel = (Model) manager.createQuery(jpqlString).getSingleResult();

		if (pCollectionsName != null) {

			for (String string : pCollectionsName) {
//				Hibernate.initialize(pModel.getClass().getMethod(string, (Class<?>[]) null)
//						.invoke(pModel, (Object[]) null));
			}
		}

		return lModel;
	}
}