package com.iossocket.web.server;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class TomcatServer {
    private static final String DISPATCHER_SERVLET_NAME = "DispatcherServlet";
    private Tomcat tomcat;
    private String[] args;

    public TomcatServer(String[] args) {
        this.args = args;
    }

    public void startServer() throws LifecycleException {
        this.tomcat = new Tomcat();
        tomcat.setPort(6699);
        tomcat.start();

        // Engine => Host => Context => Wrapper
        Context context = new StandardContext();
        context.setPath("");
        context.addLifecycleListener(new Tomcat.FixContextListener());

        tomcat.getHost().addChild(context);

        Tomcat.addServlet(context, DISPATCHER_SERVLET_NAME, "com.iossocket.web.servlet.DispatcherServlet")
                .setAsyncSupported(true);
        context.addServletMappingDecoded("/", DISPATCHER_SERVLET_NAME);

        Thread awaitThread = new Thread("tomcat_await_thread") {
            @Override
            public void run() {
                TomcatServer.this.tomcat.getServer().await();
            }
        };

        awaitThread.setDaemon(false);
        awaitThread.start();
    }
}
