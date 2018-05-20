package com.milaboratory.mitoola.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author Alexei Zakharov (ayza)
 */
@Component
public class JettyFactory extends JettyServletWebServerFactory {
    private static Logger LOG = Logger.getLogger(JettyFactory.class.getName());

    @Autowired
    public JettyFactory(@Value("${server.port}") int serverPort,
                        @Value("${server.servlet.context-path}") String servletContextPath)
    {
        super();
        setPort(serverPort);
        setContextPath(servletContextPath);
        //addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
        LOG.info("jetty initialization finished");
    }
}

