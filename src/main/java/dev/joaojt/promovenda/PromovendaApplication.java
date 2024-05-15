package dev.joaojt.promovenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "dev.joaojt.promovenda")
public class PromovendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromovendaApplication.class, args);
	}

}
