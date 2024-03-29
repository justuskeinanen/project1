package com.example.project1;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.project1.web.ProductController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Project1ApplicationTest {

	@Autowired
	private ProductController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
