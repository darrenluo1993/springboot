package pers.darren.springboot.example.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.darren.springboot.example.model.Product;

@RestController
@RequestMapping("/example")
public class ExampleController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello, Spring Boot!";
	}

	@GetMapping("/aboutMe")
	public String aboutMe() {
		return "<a href='https://about.me/darrenluo' target='_blank'>About Darren Luo</a>";
	}

	@GetMapping("/getProduct")
	public Product getProduct() {
		final Product product = new Product();
		product.setName("Product Name");
		product.setSupplier("Supplier Name");
		product.setQuantity(10000);
		product.setPrice(new BigDecimal(1234));
		return product;
	}
}