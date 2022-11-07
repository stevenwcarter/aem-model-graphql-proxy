package com.bounteous.api;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServiceMain extends Thread {

    private static final Logger log = LoggerFactory.getLogger(WebServiceMain.class);

    public static void main(String[] args) throws Exception {
        new WebServiceMain().start();
    }

    public void run() {
        log.info("Setting up server");
        HandlerCollection handlerCollection = new HandlerCollection();
        WebAppContext context = new WebAppContext();
        context.setDescriptor(context+"/WEB-INF/web.xml");
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        Handler[] handlers = { context };

        handlerCollection.setHandlers(handlers);

        Server server = new Server(9999);
        server.setHandler(handlerCollection);
        try {
            log.debug("Starting up server");
            server.start();
        } catch (Exception e) {
            log.error("Could not start server");
        }
    }
}
