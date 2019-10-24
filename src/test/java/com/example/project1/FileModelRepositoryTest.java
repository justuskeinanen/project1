package com.example.project1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.project1.domain.FileModel;
import com.example.project1.domain.FileModelRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FileModelRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private FileModelRepository fileRepo;

	@Test
	public void saveFileModel() {
		FileModel fileModel = new FileModel();
		entityManager.persistAndFlush(fileModel);
		assertThat(fileModel.getId()).isNotNull();
	}

	@Test
	public void saveFileModelRepository() {
		FileModel fileModel = new FileModel();
		entityManager.persistAndFlush(fileModel);
		fileRepo.save(fileModel);
		long id = fileModel.getId();
		assertThat(fileRepo.findById(id)).isNotNull();
	}

	@Test
	public void deleteAllFiles() {
		FileModel file1 = new FileModel();
		FileModel file2 = new FileModel();
		entityManager.persistAndFlush(file1);
		entityManager.persistAndFlush(file2);
		fileRepo.save(file1);
		fileRepo.save(file2);
		fileRepo.deleteAll();
		assertThat(fileRepo.findAll()).isEmpty();
	}

}
