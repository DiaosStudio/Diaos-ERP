package com.diaos_erp.oauth.client;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;

import org.restlet.ext.oauth.Client;
import org.restlet.ext.oauth.ClientStore;
import org.restlet.ext.oauth.internal.MemTokenGenerator;

import com.diaos_erp.dao.ServiceInjector;
import com.diaos_erp.oauth.dao.ClientInfoDaoIf;

public class DiaosClientStore extends ClientStore<MemTokenGenerator> {

	private volatile SecureRandom random;

	private static final int tokens = 1000;

	private volatile int count = 0;

	public DiaosClientStore(MemTokenGenerator generator) {
		super(generator);

		try {
			random = SecureRandom.getInstance("SHA1PRNG");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		}
	}

	protected String generate(int len) {
		if (count++ > tokens) {
			count = 0;
			random.setSeed(random.generateSeed(20));
		}
		byte[] token = new byte[len];
		random.nextBytes(token);
		return toHex(token);
	}

	protected String toHex(byte[] input) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < input.length; i++) {
			String d = Integer
					.toHexString(new Byte(input[i]).intValue() & 0xFF);
			if (d.length() == 1)
				sb.append('0');
			sb.append(d);
		}
		return sb.toString();
	}

	@Override
	public Client createClient(String clientId, String redirectUri) {
		return createClient(clientId, generate(40), redirectUri);
	}

	@Override
	public Client createClient(String clientId, String clientSecret,
			String redirectUri) {
		return createClient(clientId, clientSecret, redirectUri, clientId);
	}

	public Client createClient(String clientId, String clientSecret,
			String redirectUri, String appName) {
		ClientInfoDaoIf clientDao = ServiceInjector
				.getService(ClientInfoDaoIf.class);
		DiaosClientInfo client = new DiaosClientInfo();
		client.setApplicationName(appName);
		client.setClientId(clientId);
		client.setClientSecret(clientSecret);
		client.setRedirectUri(redirectUri);
		clientDao.createClient(client);

		return clientDao.getClientInfo(clientId);
	}

	@Override
	public void deleteClient(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Client findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends Client> findClientsForUser(String userid) {
		// TODO Auto-generated method stub
		return null;
	}
}
