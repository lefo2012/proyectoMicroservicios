package co.edu.unicauca.users_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.xml.crypto.Data;

//jdk 21.0.8
@EnableFeignClients
@SpringBootApplication
public class UsersMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(UsersMsApplication.class, args);
	}
}
