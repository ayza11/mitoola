package com.milaboratory.mitoola.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.shell.jline.PromptProvider;

/**
 * @author Alexei Zakharov (ayza)
 */
@SpringBootApplication
@ImportResource("classpath:spring/application-ctx.xml")
public class ShellApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(ShellApplication.class, args);
    }
}
