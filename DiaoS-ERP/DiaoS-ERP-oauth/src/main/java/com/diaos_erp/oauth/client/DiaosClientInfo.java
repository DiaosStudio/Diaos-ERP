package com.diaos_erp.oauth.client;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.restlet.ext.oauth.AuthenticatedUser;
import org.restlet.ext.oauth.Client;

public class DiaosClientInfo extends Client {

	private String clientId;

	private String clientSecret;

	private String redirectUri;

	private String applicationName;

	@Override
	public String getClientId() {
		return clientId;
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public String getRedirectUri() {
		return redirectUri;
	}

	@Override
	public String getApplicationName() {
		return applicationName;
	}

	@Override
	public boolean containsUser(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AuthenticatedUser createUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticatedUser findUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void revokeUser(String id) {
		// TODO Auto-generated method stub

	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
