package br.com.avaliacao.bluebank.BlueBankPag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "br.com.avaliacao.bluebank")
@EnableJpaRepositories(basePackages = "br.com.avaliacao.bluebank")
@EntityScan(basePackages = "br.com.avaliacao.bluebank")
@SpringBootApplication
public class BlueBankPagApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueBankPagApplication.class, args);
	}
}







