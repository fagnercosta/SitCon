package br.com.cin.sitcon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages="br.com.cin.sitcon.model")
@ComponentScan(basePackages= {"br.com.cin.*"})
@EnableJpaRepositories(basePackages= {"br.com.cin.sitcon.*"})
public class OpalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpalaApplication.class, args);
	}

}
