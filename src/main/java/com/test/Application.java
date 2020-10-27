package com.test;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)

/**
 * 
 * Main class for sentence service application.
 * 
 * @author Vitalij Havryk
 *
 */
public class Application {

  public static void main(String[] args) {
    run(Application.class, args);
  }

}
