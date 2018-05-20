package com.milaboratory.mitoola.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Alexei Zakharov (ayza)
 */
@EnableAutoConfiguration
@ServletComponentScan
@ImportResource("classpath:spring/application-ctx.xml")
@PropertySource("classpath:properties/context.properties")
public class Application {
    public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
    }
}
