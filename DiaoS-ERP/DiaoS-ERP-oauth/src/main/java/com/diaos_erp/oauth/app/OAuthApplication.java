package com.diaos_erp.oauth.app;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.ext.crypto.DigestAuthenticator;
import org.restlet.ext.oauth.AccessTokenServerResource;
import org.restlet.ext.oauth.AuthPageServerResource;
import org.restlet.ext.oauth.AuthorizationServerResource;
import org.restlet.ext.oauth.ClientStore;
import org.restlet.ext.oauth.ClientStoreFactory;
import org.restlet.ext.oauth.HttpOAuthHelper;
import org.restlet.ext.oauth.ValidationServerResource;
import org.restlet.ext.oauth.internal.MemTokenGenerator;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.security.MapVerifier;

import test.client.ClientApplication;

import com.diaos_erp.oauth.client.DiaosClientStore;
import com.diaos_erp.oauth.resource.impl.ClientApplyFormResourceImpl;

public class OAuthApplication extends Application
{

    @Override
    public synchronized Restlet createInboundRoot()
    {
        // Engine.setLogLevel(Level.FINE);
        Router root = new Router(getContext());

        // Challenge Authenticator
        DigestAuthenticator da = new DigestAuthenticator(getContext(), "DiaoS ERP", "serverKey");
        MapVerifier mv = new MapVerifier();
        mv.getLocalSecrets().put("test", "password".toCharArray());
        da.setWrappedVerifier(mv);

        da.setNext(AuthorizationServerResource.class);
        root.attach("/authorize", da);

        root.attach("/access_token", AccessTokenServerResource.class);
        root.attach("/validate", ValidationServerResource.class);
        root.attach(HttpOAuthHelper.getAuthPage(getContext()), AuthPageServerResource.class);

        // Set Template for AuthPage:
        HttpOAuthHelper.setAuthPageTemplate("authorize.html", getContext());
        // Dont ask for approval if previously approved
        HttpOAuthHelper.setAuthSkipApproved(true, getContext());

        // Attach Image Directory for our login.html page
        final Directory imgs = new Directory(getContext(), "clap:///img/");
        root.attach("/img", imgs);
        getContext().getLogger().info("done");

        // Finally create a test client:
        ClientStoreFactory.setClientStoreImpl(DiaosClientStore.class, new MemTokenGenerator(
                new ScheduledThreadPoolExecutor(5)));
        ClientStore clientStore = ClientStoreFactory.getInstance();

        // client管理
        root.attach("/clients/{clientId}", ClientApplyFormResourceImpl.class);

        return root;
    }

    public static void main(String[] args) throws Exception
    {
        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9090);
        c.getClients().add(Protocol.HTTP);
        // c.getClients().add(Protocol.HTTPS);
        c.getClients().add(Protocol.RIAP);
        c.getClients().add(Protocol.CLAP);
        // map applications:
        c.getDefaultHost().attach("/", new OAuthApplication());
        c.getDefaultHost().attach("/client", new ClientApplication());

        c.start();
    }
}
