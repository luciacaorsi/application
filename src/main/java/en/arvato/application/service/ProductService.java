package en.arvato.application.service;

import java.util.List;

import en.arvato.application.model.Product;
import en.arvato.application.model.ProductAnalysis;

/**
 * Service for product related operations.
 * 
 * @author Lucía Caorsi
 */
public interface ProductService {

	/**
	 * Given a list of products, this method returns an analysis with relevant
	 * information.
	 * 
	 * @param products
	 *            the list of products to be analyzed
	 * @return a {@link ProductAnalysis} containing relevant information about the
	 *         given product list
	 */
	public ProductAnalysis analyzeProducts(List<Product> products);
}