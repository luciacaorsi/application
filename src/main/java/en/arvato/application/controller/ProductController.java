package en.arvato.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import en.arvato.application.model.Product;
import en.arvato.application.model.ProductAnalysis;
import en.arvato.application.service.ProductService;

/**
 * This controller receives the HTTP request and returns the corresponding response.
 * 
 * @author Lucía Caorsi
 */
@RestController
@RequestMapping("/text")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping(value = "/analyze")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ProductAnalysis analyzeProducts(@RequestBody List<Product> products) {
		try {
			return service.analyzeProducts(products);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Products could not be analyzed", e);
		}
	}
}