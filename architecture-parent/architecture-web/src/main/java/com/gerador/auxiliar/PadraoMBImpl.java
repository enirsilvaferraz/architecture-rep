package com.gerador.auxiliar;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.primefaces.context.RequestContext;

import com.architecture.view.MensagemEnum;
import com.archtecture.control.enums.TipoOrdenacao;
import com.archtecture.control.exceptions.NegocioException;
import com.archtecture.control.facedes.PersistenceFacadeLocal;
import com.archtecture.control.models.ModelAb;

public abstract class PadraoMBImpl<Model extends ModelAb> implements PadraoMBIf<Model> {

	@EJB
	private PersistenceFacadeLocal persistenceFacade;

	private List<Model> listModel;

	private Model modelCad;

	private Model modelSel;

	private boolean pesquisaRealizada;

	protected void aposSalvar() throws NegocioException {

	}

	@Override
	public void executarExcluir(Model pModel) {

		try {
			getPersistenceFacade().excluir(pModel);
			UtilWeb.enviarMensagem(MensagemEnum.INFO_SUCESSO_EXCLUSAO);

			setListModel(null);
			setModelCad(null);

			if (isPesquisaRealizada()) {
				executarPesquisar();
			}
		} catch (Exception e) {
			UtilWeb.tratarException(e);
		}
	}

	@Override
	public void executarPesquisar() {
		try {
			setListModel(getPersistenceFacade().pesquisarLista(getModelSel(), getTipoOrdenacao(), getAtributosOrdenacao()));

			if (getListModel().isEmpty()) {
				UtilWeb.enviarMensagem(MensagemEnum.INFO_PESQUISA_VAZIA);
			}

			pesquisaRealizada = true;
		} catch (Exception e) {
			UtilWeb.tratarException(e);
		}
	}

	@Override
	public void executarSalvar() {

		try {

			if (getModelCad().getCodigo() == null || getModelCad().getCodigo().equals(0)) {
				prepararSalvar();
				getPersistenceFacade().inserir(getModelCad());
				UtilWeb.enviarMensagem(MensagemEnum.INFO_SUCESSO_CADASTRO);
			} else {
				getPersistenceFacade().atualizar(getModelCad());
				UtilWeb.enviarMensagem(MensagemEnum.INFO_SUCESSO_EDICAO);
			}

			aposSalvar();

			setListModel(null);
			setModelCad(null);

			if (isPesquisaRealizada()) {
				executarPesquisar();
			}

			RequestContext.getCurrentInstance().execute("PF('dialogCadastro').hide()");
		} catch (Exception e) {
			UtilWeb.tratarException(e);
		}
	}

	protected String[] getAtributosOrdenacao() {
		return getModelSel().getAtributosOrdencao();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Model getInstance() {
		try {
			ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class lClasse = (Class) parameterizedType.getActualTypeArguments()[0];
			return (Model) lClasse.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	@Override
	public List<Model> getListModel() {
		if (listModel == null) {
			listModel = new ArrayList<>();
		}
		return listModel;
	}

	@Override
	public Model getModelCad() {
		if (modelCad == null) {
			modelCad = getInstance();
		}
		return modelCad;
	}

	@Override
	public Model getModelSel() {
		if (modelSel == null) {
			modelSel = getInstance();
		}
		return modelSel;
	}

	public PersistenceFacadeLocal getPersistenceFacade() {
		return persistenceFacade;
	}

	protected TipoOrdenacao getTipoOrdenacao() {
		return TipoOrdenacao.ASC;
	}

	public boolean isPesquisaRealizada() {
		return pesquisaRealizada;
	}

	protected List<? extends ModelAb> montarCombo(ModelAb pModel, TipoOrdenacao pTipoOrdenacao, String... pAtributosOrdenacao) {

		List<ModelAb> listCombo = new ArrayList<ModelAb>();

		if (pTipoOrdenacao == null) {
			pTipoOrdenacao = TipoOrdenacao.ASC;
		}

		try {
			listCombo = getPersistenceFacade().pesquisarLista(pModel, pTipoOrdenacao, pAtributosOrdenacao);
		} catch (Exception e) {
			UtilWeb.tratarException(e);
		}

		return listCombo;
	}

	@Override
	public void prepararDetalhes(Model pModel) {
		setModelCad(pModel);
		RequestContext.getCurrentInstance().execute("PF('dialogDetalhes').show()");
	}

	@Override
	public void prepararEditar(Model pModel) {
		setModelCad(pModel);
		RequestContext.getCurrentInstance().execute("PF('dialogCadastro').show()");
	}

	protected void prepararSalvar() throws NegocioException {

	}

	public void setListModel(List<Model> listModel) {
		this.listModel = listModel;
	}

	public void setModelCad(Model modelCad) {
		this.modelCad = modelCad;
	}

	public void setModelSel(Model modelSel) {
		this.modelSel = modelSel;
	}
}
