package en.arvato.application.model;

import java.util.ArrayList;
import java.util.List;

public class ProductAnalysis {

	private String mostExpensiveProduct;
	private String cheapestProduct;
	private String mostPopularProduct;
	private List<String> germanProducts;
	private List<String> chineseProducts;
	private boolean containsFragileProducts;

	public ProductAnalysis() {
		germanProducts = new ArrayList<>();
		chineseProducts = new ArrayList<>();
	}
	
	public String getMostExpensiveProduct() {
		return mostExpensiveProduct;
	}

	public void setMostExpensiveProduct(String mostExpensiveProduct) {
		this.mostExpensiveProduct = mostExpensiveProduct;
	}

	public String getCheapestProduct() {
		return cheapestProduct;
	}

	public void setCheapestProduct(String cheapestProduct) {
		this.cheapestProduct = cheapestProduct;
	}

	public String getMostPopularProduct() {
		return mostPopularProduct;
	}

	public void setMostPopularProduct(String mostPopularProduct) {
		this.mostPopularProduct = mostPopularProduct;
	}

	public List<String> getGermanProducts() {
		return germanProducts;
	}

	public void setGermanProducts(List<String> germanProducts) {
		this.germanProducts = germanProducts;
	}

	public List<String> getChineseProducts() {
		return chineseProducts;
	}

	public void setChineseProducts(List<String> chineseProducts) {
		this.chineseProducts = chineseProducts;
	}

	public boolean isContainsFragileProducts() {
		return containsFragileProducts;
	}

	public void setContainsFragileProducts(boolean containsFragileProducts) {
		this.containsFragileProducts = containsFragileProducts;
	}
}