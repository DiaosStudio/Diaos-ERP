package com.diaos_erp.plugins;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class PluginsApplication extends Application
{
    @Override
    public synchronized Restlet createInboundRoot()
    {
        // Engine.setLogLevel(Level.FINE);
        Router root = new Router(getContext());

        final Directory plugins = new Directory(getContext(), "clap:///plugins/");
        root.attach("/", plugins);

        return root;
    }

}
