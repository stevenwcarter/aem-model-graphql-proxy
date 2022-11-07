package com.bounteous.api.inject;

import com.bounteous.service.ServiceConfigModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import java.util.Set;

public class WebServiceGuiceConfig extends GuiceServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(WebServiceGuiceConfig.class);

    private Injector injector;

    @Override
    protected Injector getInjector() {
        log.debug("Getting injector");
        injector = Guice.createInjector(
                                        new ServiceConfigModule(),
                                        new WebServiceConfigModule(),
                                        new GraphQLModule());

        return injector;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
        log.debug("Context destroyed");

        Set<Thread> threadSet = Thread.getAllStackTraces()
                                      .keySet();
        Thread[] threadArray = threadSet.toArray(new Thread[0]);
        for (Thread t : threadArray) {
            if (t.getName()
                 .contains("Abandoned connection cleanup thread")) {
                t.stop(); //don't complain, it works
            }
        }
    }

}
