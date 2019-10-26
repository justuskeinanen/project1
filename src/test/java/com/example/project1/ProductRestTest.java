package com.example.project1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductRestTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAuthenticationWithCorrectCredentials() throws Exception {
		this.mockMvc.perform(post("/login").content("{\"username\":\"admin\", \"password\":\"admin\"}")).andDo(print())
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testAuthenticationWithWrongCredentials() throws Exception {
		this.mockMvc.perform(post("/login").content("{\"username\":\"admin\", \"password\":\"wrongpwd\"}"))
				.andDo(print()).andExpect(status().isForbidden());

	}

}