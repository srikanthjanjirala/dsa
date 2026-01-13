package com.dsa.practice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//@ConditionalOnProperty(
//        name = "feature.email.enabled",
//        havingValue = "true"
//)
public class AppConfig {

    @Bean
    @Profile("dev")
//    @Conditional(DevEnvironmentCondition.class)
//    @Conditional(StageingCondition.class)
//    @Conditional(ProdCondition.class)
    public String samepleMethod() {
        return "This is a sample method in AppConfig";
    }
}
