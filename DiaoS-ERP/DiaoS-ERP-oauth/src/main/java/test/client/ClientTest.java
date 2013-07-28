package test.client;

import org.restlet.resource.ClientResource;

import com.diaos_erp.oauth.resource.ClientApplyFormResourceIf;

public class ClientTest {
	public static void main(String[] args) throws Exception {
		ClientApplyFormResourceIf mailRoot = ClientResource.create(
				"http://localhost:9090//clients/client1",
				ClientApplyFormResourceIf.class);
//		Client result = mailRoot.createNewClient();
//		System.out.println(result);
	}
}
