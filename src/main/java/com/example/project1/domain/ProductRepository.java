package com.example.project1.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
//public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByNameIgnoreCase(String name);

	List<Product> findByNameAndPrice(String name, Double price);

	List<Product> findByNameOrderByPriceAsc(String name);

	List<Product> findByNameOrPrice(String name, Double price);

	List<Product> findByPrice(Double price);
}
