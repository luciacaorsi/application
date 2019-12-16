package en.arvato.application.service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import en.arvato.application.model.Product;
import en.arvato.application.model.ProductAnalysis;

/**
 * Default implementation for the {@link ProductService} interface.
 * 
 * @author Lucía Caorsi
 */
@Service
public class DefaultProductService implements ProductService {

	@Override
	public ProductAnalysis analyzeProducts(List<Product> products) {

		ProductAnalysis analysis = new ProductAnalysis();

		if (!products.isEmpty()) {
			analysis.setMostExpensiveProduct(getMostExpensiveProductName(products));
			analysis.setCheapestProduct(getCheapestProductName(products));
			analysis.setMostPopularProduct(getMostPopularProductName(products));
			analysis.setGermanProducts(getProductNamesFromCountry(products, "DE"));
			analysis.setChineseProducts(getProductNamesFromCountry(products, "CN"));
			analysis.setContainsFragileProducts(containsFragileProducts(products));
		}

		return analysis;
	}

	/**
	 * Given a list of products, determines the most expensive and returns its name.
	 * 
	 * @param products
	 *            the list of products to evaluate
	 * @return the most expensive product on the list. If more than one product
	 *         share the highest price, then only one is returned.
	 */
	private String getMostExpensiveProductName(List<Product> products) {
		return products.stream()
				.max(Comparator.comparing(Product::getPrice))
				.orElseThrow(NoSuchElementException::new)
				.getName();
	}

	/**
	 * Given a list of products, determines the cheapest and returns its name.
	 * 
	 * @param products
	 *            the list of products to evaluate
	 * @return the cheapest product on the list. If more than one product share the
	 *         lowest price, then only one is returned.
	 */
	private String getCheapestProductName(List<Product> products) {
		return products.stream()
				.min(Comparator.comparing(Product::getPrice))
				.orElseThrow(NoSuchElementException::new)
				.getName();
	}

	/**
	 * Given a list of products, determines the one that has been purchased the most
	 * times and returns its name.
	 * 
	 * @param products
	 *            the list of products to evaluate
	 * @return the most purchased product on the list. If more than one product have
	 *         been purchased the same highest amount, then only one is returned.
	 */
	private String getMostPopularProductName(List<Product> products) {
		return products.stream()
				.max(Comparator.comparing(Product::getTimesPurchased))
				.orElseThrow(NoSuchElementException::new)
				.getName();
	}

	/**
	 * Given a list of products, return a sublist containing the products from the
	 * specified country.
	 * 
	 * @param products
	 *            the list of products to evaluate
	 * @param countryCode
	 *            the country code in accordance to the ISO-3166-2 standard
	 * @return a {@link List} of all the products with the specified country of
	 *         origin
	 */
	private List<String> getProductNamesFromCountry(List<Product> products, String countryCode) {
		return products.stream()
				.filter(p -> countryCode.equals(p.getCountryOfOrigin()))
				.map(Product::getName)
				.collect(Collectors.toList());
	}

	/**
	 * Determines whether the given list contains fragile products.
	 * 
	 * @param products
	 *            the list of products to evaluate
	 * @return {@code true} if the list contains one or more fragile products,
	 *         {@code false} otherwise
	 */
	private boolean containsFragileProducts(List<Product> products) {
		return products.stream()
				.anyMatch(Product::getIsFragile);
	}
}