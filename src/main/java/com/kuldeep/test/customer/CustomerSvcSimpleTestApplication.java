package com.kuldeep.test.customer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class CustomerSvcSimpleTestApplication {


	@Bean
	ApplicationRunner init(final CustomerRepository customerRepository){
		return args -> {
			Stream.of("a", "b", "c").
					forEach(
						n -> customerRepository.saveAndFlush(new Customer(null, n, n, n + "@email.com"))
					);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomerSvcSimpleTestApplication.class, args);
	}

}
