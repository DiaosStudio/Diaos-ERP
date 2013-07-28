package com.diaos_erp.oauth.dao.mapper;

import org.restlet.ext.oauth.Client;

import com.diaos_erp.oauth.client.DiaosClientInfo;

public interface ClientInfoMapper {
	public Client selectTest(String clientId);

	public int createNew(DiaosClientInfo client);
}
