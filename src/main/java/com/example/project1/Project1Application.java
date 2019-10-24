package com.example.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.project1.domain.Crafter;
import com.example.project1.domain.CrafterRepository;
import com.example.project1.domain.Product;
import com.example.project1.domain.ProductRepository;
import com.example.project1.domain.User;
import com.example.project1.domain.UserRepository;

@SpringBootApplication
public class Project1Application {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CrafterRepository crafterRepo;
	@Autowired
	private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			
			Crafter crafter1 = new Crafter("Homssuinen", "Tuutikki");
			Crafter crafter2 = new Crafter("Röhnäinen", "Raineri");
			crafterRepo.save(crafter1);
			crafterRepo.save(crafter2);

			Product product1 = new Product("Kahvikuppi", 15.00);
			Product product2 = new Product("Kahvipannu", 50.00);
			Product product3 = new Product("Kukkaruukku", 10.00);

			product1.setCrafter(crafter1);
			product2.setCrafter(crafter1);
			product3.setCrafter(crafter2);

			productRepo.save(product1);
			productRepo.save(product2);
			productRepo.save(product3);

			userRepo.save(new User("user", "$2a$10$UW4LoGnlJTcVNWcx/chmW.LF5zriF9PO7VgRlfOJPymj4dncGxPZa", "USER"));
			userRepo.save(new User("admin", "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", "ADMIN"));
			
		};
	}

}
