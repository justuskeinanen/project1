package com.example.project1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.project1.domain.Crafter;
import com.example.project1.domain.CrafterRepository;
import com.example.project1.domain.Product;
import com.example.project1.domain.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository repo;
	
	@Autowired 
	CrafterRepository crafterrepo;
	
	private Crafter crafter;

	@Before
	public void createCrafter() {
		this.crafter = new Crafter("Mikkonen", "Häkä");
		entityManager.persistAndFlush(crafter);
	}
	
	@Test
	public void saveProduct() {
		Product product = new Product("Muki", 5.00,crafter);
		entityManager.persistAndFlush(product);
		assertThat(product.getId()).isNotNull();
	}

	@Test
	public void deleteAll() {
		entityManager.persistAndFlush(new Product("Maljakko", 55, crafter));
		entityManager.persistAndFlush(new Product("Moottoripyörä", 15000, crafter));
		repo.deleteAll();
		assertThat(repo.findAll()).isEmpty();
	}
}
