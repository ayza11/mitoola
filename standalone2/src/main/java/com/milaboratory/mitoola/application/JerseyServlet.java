package com.milaboratory.mitoola.application;


import org.springframework.stereotype.Component;


import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;


import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Jersey servlet mapping
 * @author Alexei Zakharov (ayza)
 */
@Component("jerseyServlet")
@WebServlet(
        name = "jerseyServlet",
        urlPatterns = "/api/*",
        loadOnStartup = 1,
        initParams = {
                @WebInitParam(name = "javax.ws.rs.Application", value = "com.milaboratory.mitoola.application.JaxrsApplication")
        })
public class JerseyServlet extends ServletContainer {
}

