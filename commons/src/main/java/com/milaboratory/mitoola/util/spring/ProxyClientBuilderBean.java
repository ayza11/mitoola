package com.milaboratory.mitoola.util.spring;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.FactoryBean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * @author Alexei Zakharov (ayza)
 */
public class ProxyClientBuilderBean<T> implements FactoryBean<T> {

    private Class<T> serviceClazz;
    private String serviceUri;

    public Class<T> getServiceClass() {
        return serviceClazz;
    }

    public void setServiceClass(Class<T> clazz) {
        this.serviceClazz = clazz;
    }

    public String getAddress() {
        return serviceUri;
    }

    public void setAddress(String serviceUri) {
        this.serviceUri = serviceUri;
    }

    // ---

    @Override
    public T getObject() throws Exception {
        return getJerseyServiceProxy(serviceClazz, serviceUri);
    }

    @Override
    public Class<?> getObjectType() {
        return serviceClazz;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    // ---

    private T getJerseyServiceProxy(Class<T> serviceClazz, String serviceAddress) {
        ClientConfig cc = new ClientConfig()
                .register(JacksonFeature.class);
//                .register(AnotherFeature.class)
//                .register(SomeFilter.class)
        Client resource = ClientBuilder.newClient(cc);
        WebTarget serviceTarget = resource.target(serviceAddress);
        return WebResourceFactory.newResource(serviceClazz, serviceTarget);
    }

}
