package com.example.project1.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project1.domain.CrafterRepository;
import com.example.project1.domain.Product;
import com.example.project1.domain.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CrafterRepository crafterRepo;

	@RequestMapping("/products")
	public String products(Model model) {
		model.addAttribute("products", productRepo.findAll());
		return "productList";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("crafters", crafterRepo.findAll());
		return "addProduct";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/saveNew", method = RequestMethod.POST)
	public String saveNew(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("crafters", crafterRepo.findAll());
			return "addProduct";
		}
		productRepo.save(product);
		return "redirect:products";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") Long productId, Model model) {
		productRepo.deleteById(productId);
		return "redirect:../products";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}")
	public String editProduct(@PathVariable("id") Long productId, Model model) {
		model.addAttribute("product", productRepo.findById(productId));
		model.addAttribute("crafters", crafterRepo.findAll());
		return "editProduct";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("crafters", crafterRepo.findAll());
			return "editProduct";
		}
		productRepo.save(product);
		return "redirect:products";
	}

	// RESTful service to get all products
	@RequestMapping(value = "/productsrest", method = RequestMethod.GET)
	public @ResponseBody List<Product> productsRest() {
		return (List<Product>) productRepo.findAll();
	}

	// RESTful service to get products by id
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Product> findProductRest(@PathVariable("id") Long productId) {
		return productRepo.findById(productId);
	}
}
