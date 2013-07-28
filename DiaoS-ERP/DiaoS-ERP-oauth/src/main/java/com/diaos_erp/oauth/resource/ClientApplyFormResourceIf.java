package com.diaos_erp.oauth.resource;

import org.restlet.ext.oauth.Client;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface ClientApplyFormResourceIf {
	@Put("json|form:json")
	public Client createNewClient(String clientId);

	@Get
	public Client getClient();
}
