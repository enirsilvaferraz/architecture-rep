package com.exemplo.model.facades;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.archtecture.model.facedes.PersistenceFacadeBean;

@Stateless(name = "UsuarioFacade")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioFacadeBean extends PersistenceFacadeBean {

}
