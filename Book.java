package osdCA3;

public class Book {

	private String Isbn, title, genre;
	private double price;

	
	public Book() {
		this.Isbn = "";
		this.title = "";
		this.genre = "";
		this.price =0;
	}
	
	public Book(String Isbn, String title, String genre, double price) {
		this.Isbn = Isbn;
		this.title = title;
		this.genre = genre;
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIsbn() {
		return Isbn;
	}

	public void setIsbn(String isbn) {
		Isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [Isbn=" + Isbn + ", title=" + title + ", genre=" + genre + ", price=" + price + "]";
	}
	
	
	

	
	
	
}
