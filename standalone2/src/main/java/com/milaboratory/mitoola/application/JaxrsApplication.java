package com.milaboratory.mitoola.application;

import one.util.streamex.StreamEx;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Alexei Zakharov (ayza)
 */
public class JaxrsApplication extends ResourceConfig {

    private static List<String> PACKAGES_WITH_WS_CLASSES = Arrays.asList(
            "com.milaboratory.mitoola.ws"
    );

    /**
     * Register JAX-RS application components.
     */
    public JaxrsApplication() {
        register(RequestContextFilter.class);
        StreamEx.of(PACKAGES_WITH_WS_CLASSES)
                .map(JaxrsApplication::findClassesByPackage)
                .flatMap(List::stream)
                .forEach(super::register);
    }

    private static List<Class> findClassesByPackage(String packageName) {
        // create scanner and disable default filters (that is the 'false' argument)
        ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);
        // add include filters which matches all the classes (or use your own)
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        // get matching classes defined in the package
        Set<BeanDefinition> classes = provider.findCandidateComponents(packageName);
        return StreamEx.of(classes)
                .map(def -> forName(def.getBeanClassName()))
                .toList();
    }

    private static Class forName(String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
