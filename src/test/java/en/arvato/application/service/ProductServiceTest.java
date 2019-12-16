package en.arvato.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import en.arvato.application.model.Product;
import en.arvato.application.model.ProductAnalysis;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductService.class)
public class ProductServiceTest {

	@Autowired
	private ProductService service;

	private List<Product> products;
	private Product p1;
	private Product p2;
	private Product p3;

	@Before
	public void setup() {
		p1 = new Product();
		p1.setName("product1");
		p1.setCountryOfOrigin("DE");
		p1.setPrice(123.50);
		p1.setIsFragile(true);
		p1.setTimesPurchased(150);

		p2 = new Product();
		p2.setName("product2");
		p2.setCountryOfOrigin("DE");
		p2.setPrice(45);
		p2.setIsFragile(false);
		p2.setTimesPurchased(1241);

		p3 = new Product();
		p3.setName("product3");
		p3.setCountryOfOrigin("ES");
		p3.setPrice(13);
		p3.setIsFragile(false);
		p3.setTimesPurchased(772);

		products = Arrays.asList(p1, p2, p3);
	}
	
	@Test
	public void testAnalyzeProducts() {
		ProductAnalysis analysis = service.analyzeProducts(products);
		
		assertEquals("product1", analysis.getMostExpensiveProduct());
		assertEquals("product3", analysis.getCheapestProduct());
		assertEquals("product2", analysis.getMostPopularProduct());
		
		assertEquals(2, analysis.getGermanProducts().size());
		assertTrue(analysis.getGermanProducts().containsAll(Arrays.asList(p1.getName(), p2.getName())));
		
		assertEquals(0, analysis.getChineseProducts().size());
		
		assertTrue(analysis.isContainsFragileProducts());
		
	}
	
	@Test
	public void testAnalyzeProductsWithEmptyList() {
		ProductAnalysis analysis = service.analyzeProducts(new ArrayList<Product>());
		
		assertNull(analysis.getMostExpensiveProduct());
		assertNull(analysis.getCheapestProduct());
		assertNull(analysis.getMostPopularProduct());
		
		assertEquals(0, analysis.getGermanProducts().size());
		
		assertEquals(0, analysis.getChineseProducts().size());
		
		assertFalse(analysis.isContainsFragileProducts());
	}
}