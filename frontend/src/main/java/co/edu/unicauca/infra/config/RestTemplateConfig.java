package co.edu.unicauca.infra.config;

import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplateConfig {

    public static RestTemplate getInstance(){
        return new RestTemplate();
    }
}
