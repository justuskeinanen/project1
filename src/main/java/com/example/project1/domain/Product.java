package com.example.project1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@Size(min=2, max=50)
	@Column
	private String name;
	@Min(0)
	@Column
	private double price;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "crafter")
	private Crafter crafter;

	public Product() {
		super();

	}

	public void setId(long id) {
		this.id = id;
	}

	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Product(String name, double price, Crafter crafter) {
		super();
		this.name = name;
		this.price = price;
		this.crafter = crafter;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public Product(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Crafter getCrafter() {
		return crafter;
	}

	public void setCrafter(Crafter crafter) {
		this.crafter = crafter;
	}

}