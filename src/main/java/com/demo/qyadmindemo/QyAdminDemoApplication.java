package com.demo.qyadmindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAsync
@EntityScan("com.demo.qyadmindemo")
@EnableJpaRepositories("com.demo.qyadmindemo")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@SpringBootApplication
public class QyAdminDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QyAdminDemoApplication.class, args);
    }

}
