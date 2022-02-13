package com.project.ChocoWorld;

import com.project.ChocoWorld.entities.Product;
import com.project.ChocoWorld.entities.User;
import com.project.ChocoWorld.repositories.ProductRepository;
import com.project.ChocoWorld.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ChocoWorldApplication {

	@Autowired
	PasswordEncoder passwordEncoder;


	@Bean
	public CommandLineRunner dataLoad(UserRepository userRepository, ProductRepository productRepository) {
		return args -> {
			// adds a new User
			if (((List) userRepository.findAll()).isEmpty()) {
				User user = new User();
				user.setUsername("user1");
				user.setPassword(passwordEncoder.encode("pass"));
				user.setMoneyBalance(200);
				userRepository.save(user);
			}

			// adds Product items if the DB
			if (((List) productRepository.findAll()).isEmpty()) {
				Product HazelnutCrispMilkChocolateBar = new Product("HazelnutCrispMilkChocolateBar", 6.5, 10);
				Product HazelnutCrispDarkChocolateBar = new Product("HazelnutCrispDarkChocolateBar", 6.5, 10);
				Product VirginiaPeanutsMilkChocolate = new Product("VirginiaPeanutsMilkChocolate", 11.9, 15);
				Product VirginiaPeanutsDarkChocolate = new Product("VirginiaPeanutsDarkChocolate", 11.9, 10);
				Product SugarFreeSolidDarkChocolateBar = new Product("SugarFreeSolidDarkChocolateBar", 6.5, 15);
				Product SugarFreeSolidMilkChocolateBar = new Product("SugarFreeSolidMilkChocolateBar", 6.5, 15);
				Product AssortedBridgewaterTruffles = new Product("AssortedBridgewaterTruffles", 28.95, 10);
				Product AlmondsDarkChocolateBar = new Product("AlmondsDarkChocolateBar", 6.5, 10);
				Product AlmondsMilkChocolateBar = new Product("AlmondsMilkChocolateBar", 6.5, 15);
				Product PretzelsChocolate = new Product("PretzelsChocolate", 20.5, 10);

				productRepository.save(HazelnutCrispMilkChocolateBar);
				productRepository.save(HazelnutCrispDarkChocolateBar);
				productRepository.save(VirginiaPeanutsMilkChocolate);
				productRepository.save(VirginiaPeanutsDarkChocolate);
				productRepository.save(SugarFreeSolidDarkChocolateBar);
				productRepository.save(SugarFreeSolidMilkChocolateBar);
				productRepository.save(AssortedBridgewaterTruffles);
				productRepository.save(AlmondsDarkChocolateBar);
				productRepository.save(AlmondsMilkChocolateBar);
				productRepository.save(PretzelsChocolate);
			}
		};

	}
	public static void main(String[] args) {
		SpringApplication.run(ChocoWorldApplication.class, args);
	}
}

