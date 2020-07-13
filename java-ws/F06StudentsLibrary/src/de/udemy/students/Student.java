package de.udemy.students;

public class Student implements Runnable, Constants{
	
	private int id;
	private Book[] books;
	
	public Student(int id, Book[] books) {
		super();
		this.id = id;
		this.books = books;
	}

	@Override
	public void run() {
		while(true) {
			int bookId = RANDOM.nextInt(books.length);
			
			books[bookId].read(this);
		}
	}

	@Override
	public String toString() {
		return "Student #" + id;
	}
}