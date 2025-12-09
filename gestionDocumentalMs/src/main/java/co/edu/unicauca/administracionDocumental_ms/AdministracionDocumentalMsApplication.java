package co.edu.unicauca.administracionDocumental_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = "co.edu.unicauca.administracionDocumental_ms.infra.jpa")
@EnableJpaRepositories(basePackages = "co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa")
@ComponentScan(basePackages = {
        "co.edu.unicauca.administracionDocumental_ms",
        "co.edu.unicauca.administracionDocumental_ms.infra",
        "co.edu.unicauca.administracionDocumental_ms.controller"
})
public class AdministracionDocumentalMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministracionDocumentalMsApplication.class, args);
	}

}
