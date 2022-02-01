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

	public static void main(String[] args) {
		SpringApplication.run(ChocoWorldApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoad(UserRepository userRepository, ProductRepository productRepository) {
		return args -> {
			// adds a new User
			if (((List) userRepository.findAll()).isEmpty()) {
				User user = new User();
				user.setUsername("user");
				user.setPassword(passwordEncoder.encode("password"));
				user.setMoneyBalance(200);
				userRepository.save(user);
			}

			// adds Product items if the DB
			if (((List) productRepository.findAll()).isEmpty()) {
				Product Hazelnut_Crisp_Milk_Chocolate_Bar = new Product("Hazelnut Crisp Milk Chocolate Bar", 6.5, 10);
				Product Hazelnut_Crisp_Dark_Chocolate_Bar = new Product("Hazelnut Crisp Dark Chocolate Bar", 6.5, 10);
				Product Virginia_Peanuts_in_Milk_Chocolate = new Product("Virginia Peanuts in Milk Chocolate", 11.9, 15);
				Product Virginia_Peanuts_in_Dark_Chocolate = new Product("Virginia Peanuts in Dark Chocolate", 11.9, 10);
				Product Sugar_Free_Solid_Dark_Chocolate_Bar = new Product("Sugar Free Solid Dark Chocolate Bar", 6.5, 15);
				Product Sugar_Free_Solid_Milk_Chocolate_Bar = new Product("Sugar Free Solid Milk Chocolate Bar", 6.5, 15);
				Product Assorted_Bridgewater_Truffles = new Product("12 pc. Assorted Bridgewater Truffles", 28.95, 10);
				Product Almonds_in_Dark_Chocolate_Bar = new Product("Almonds in Dark Chocolate Bar", 6.5, 10);
				Product Almonds_in_Milk_Chocolate_Bar = new Product("Almonds in Milk Chocolate Bar", 6.5, 15);
				Product Pretzels_in_Chocolate = new Product("Pretzels in Chocolate", 20.5, 10);

				productRepository.save(Hazelnut_Crisp_Milk_Chocolate_Bar);
				productRepository.save(Hazelnut_Crisp_Dark_Chocolate_Bar);
				productRepository.save(Virginia_Peanuts_in_Milk_Chocolate);
				productRepository.save(Virginia_Peanuts_in_Dark_Chocolate);
				productRepository.save(Sugar_Free_Solid_Dark_Chocolate_Bar);
				productRepository.save(Sugar_Free_Solid_Milk_Chocolate_Bar);
				productRepository.save(Assorted_Bridgewater_Truffles);
				productRepository.save(Almonds_in_Dark_Chocolate_Bar);
				productRepository.save(Almonds_in_Milk_Chocolate_Bar);
				productRepository.save(Pretzels_in_Chocolate);
			}
		};
	}

}
