package com.bjmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BjmcBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BjmcBackendApplication.class, args);
    }

    // Logs all registered RestController beans
    @Bean
    public CommandLineRunner listControllers(ApplicationContext context) {
        return args -> {
            System.out.println("=== Registered @RestController Beans ===");
            String[] controllerBeans = context.getBeanNamesForAnnotation(RestController.class);
            for (String beanName : controllerBeans) {
                Object bean = context.getBean(beanName);
                System.out.println("→ " + bean.getClass().getName());
            }
            System.out.println("========================================");
        };
    }
}
