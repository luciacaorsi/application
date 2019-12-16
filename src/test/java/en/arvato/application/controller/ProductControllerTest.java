package en.arvato.application.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import en.arvato.application.model.Product;
import en.arvato.application.model.ProductAnalysis;
import en.arvato.application.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductService service;

	private List<Product> products;

	@Before
	public void setup() {
		Product p1 = new Product();
		p1.setName("product1");
		p1.setPrice(100.0);
		p1.setTimesPurchased(10);
		p1.setCountryOfOrigin("AR");
		p1.setIsFragile(true);

		products = Arrays.asList(p1);

		ProductAnalysis a = new ProductAnalysis();
		a.setCheapestProduct(p1.getName());
		a.setMostExpensiveProduct(p1.getName());
		a.setMostPopularProduct(p1.getName());
		a.setContainsFragileProducts(true);

		Mockito.when(service.analyzeProducts(products)).thenReturn(a);
	}

	@Test
	public void testAnalyzeProducts() throws Exception {
		mvc.perform(post("/text/analyze")
				.content("[{\"name\": \"product1\","
						+ "\"countryOfOrigin\": \"AR\","
						+ "\"price\": 100.0,"
						+ "\"isFragile\": true,"
						+ "\"timesPurchased\": 10}]")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		Mockito.verify(service).analyzeProducts(products);
	}

	@Test
	public void testAnalyzeProductsBadRequest() throws Exception {
		mvc.perform(post("/text/analyze")
				.content("")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		Mockito.verify(service, Mockito.times(0)).analyzeProducts(Mockito.any());
	}
}