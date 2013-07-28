package com.diaos_erp.oauth.dao;

import java.util.List;

import org.mybatis.guice.transactional.Transactional;
import org.restlet.ext.oauth.Client;

import com.diaos_erp.oauth.client.DiaosClientInfo;
import com.diaos_erp.oauth.dao.impl.ClientInfoDaoImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(ClientInfoDaoImpl.class)
public interface ClientInfoDaoIf {

	public abstract DiaosClientInfo getClientInfo(String clientId);

	public abstract List<DiaosClientInfo> getClientContainsUser(String userId);

	public abstract boolean deleteClient(String clientId);

	@Transactional
	public abstract boolean createClient(Client client);

	public abstract boolean updateClient(DiaosClientInfo client);

}