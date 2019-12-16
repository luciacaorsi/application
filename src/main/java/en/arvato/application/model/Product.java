package en.arvato.application.model;

public class Product {

	private String name;
	private String countryOfOrigin;
	private double price;
	private boolean isFragile;
	private int timesPurchased;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean getIsFragile() {
		return isFragile;
	}
	public void setIsFragile(boolean isFragile) {
		this.isFragile = isFragile;
	}
	public int getTimesPurchased() {
		return timesPurchased;
	}
	public void setTimesPurchased(int timesPurchased) {
		this.timesPurchased = timesPurchased;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Product [" + name + "]";
	}
}