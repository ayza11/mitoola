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

    private static List<String> WS_CLASSES_PACKAGES = Arrays.asList(
            "com.milaboratory.mitoola.ws"
    );

    /**
     * Register JAX-RS application components.
     */
    public JaxrsApplication() {
        register(RequestContextFilter.class);

        StreamEx.of(WS_CLASSES_PACKAGES)
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
                .map(def -> {
                    try {
                        return (Class) (Class.forName(def.getBeanClassName()));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }
}
