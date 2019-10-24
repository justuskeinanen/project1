package com.example.project1.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CrafterRepository extends PagingAndSortingRepository<Crafter, Long> {
	List<Crafter> findByFirstName(String firstName);

	List<Crafter> findByLastName(String lastName);
}
