package test.client;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class ClientApplication extends Application {
	@Override
	public synchronized Restlet createInboundRoot() {
		// Engine.setLogLevel(Level.FINE);
		Router root = new Router(getContext());

		final Directory bootstrap = new Directory(getContext(),
				"clap:///bootstrap/");
		root.attach("/bootstrap", bootstrap);

		final Directory polymer = new Directory(getContext(),
				"clap:///polymer/");
		root.attach("/polymer", polymer);

		final Directory client = new Directory(getContext(), "clap:///client/");
		root.attach("/", client);

		return root;
	}
}
