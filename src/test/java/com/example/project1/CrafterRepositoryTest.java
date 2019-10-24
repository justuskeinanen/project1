package com.example.project1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.project1.domain.Crafter;
import com.example.project1.domain.CrafterRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CrafterRepositoryTest {

	@Autowired
	private CrafterRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void addingNewCrafter() {
		Crafter crafter = new Crafter("Höpsähtänyt", "Henrik");
		entityManager.persistAndFlush(crafter);

		assertThat(crafter.getId()).isNotNull();
	}

	@Test
	public void addingAnddeletingAll() {
		Crafter crafter2 = new Crafter("Hupsuinen", "Julio");
		Crafter crafter3 = new Crafter("Innokas", "Inka");

		entityManager.persistAndFlush(crafter2);
		entityManager.persistAndFlush(crafter3);

		repo.deleteAll();
		assertThat(repo.findAll()).isEmpty();

	}
}
