package com.diaos_erp.oauth.resource.impl;

import java.util.List;

import org.restlet.ext.oauth.Client;
import org.restlet.ext.oauth.ClientStore;
import org.restlet.ext.oauth.ClientStoreFactory;
import org.restlet.representation.Variant;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.diaos_erp.oauth.resource.ClientApplyFormResourceIf;

public class ClientApplyFormResourceImpl extends ServerResource implements
		ClientApplyFormResourceIf {

	public Client createNewClient(String clientId) {
		List<Variant> list = this.getVariants();

		getContext().getLogger().info(list.size() + "");

		ClientStore clientStore = ClientStoreFactory.getInstance();

		return clientStore.createClient("1234567890",
				"http://localhost:8080/proxy");
	}

	@Get
	public Client getClient() {
		return null;
	}
}
