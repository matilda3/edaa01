package bst;

public class Book {
	
	private String title, author;
	private int num;

	public Book(String title, String author, int num) {
		this.title = title;
		this.author = author;
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}
	
	
	@Override
	public String toString() {
		return title;
	}

}
