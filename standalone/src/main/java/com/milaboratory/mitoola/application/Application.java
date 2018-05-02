package com.milaboratory.mitoola.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Alexei Zakharov (ayza)
 */
@SpringBootApplication
@ImportResource("classpath:spring/application-ctx.xml")
public class Application {
    public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
    }
}
