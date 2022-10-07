package com.ssafy.mas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class MasApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(MasApplication.class);
        springApplicationBuilder.properties("spring.config.location="
                + "classpath:/application.yml, "
                + "classpath:/application-database.yml, "
                + "classpath:/application-env.yml");
        SpringApplication springApplication = springApplicationBuilder.build();
        springApplication.run(args);
//        SpringApplication.run(MasApplication.class, args);
    }

}
