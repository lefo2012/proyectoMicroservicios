package co.edu.unicauca.administracionDocumental_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class AdministracionDocumentalMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministracionDocumentalMsApplication.class, args);
	}

}
