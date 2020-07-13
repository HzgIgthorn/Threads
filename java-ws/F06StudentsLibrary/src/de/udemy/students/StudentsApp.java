package de.udemy.students;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentsApp implements Constants{

	public static void main(String[] args) {
		Student[] students = null;
		Book[] books = null;
		ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_STUDENTS);
		
		try {
			books = new Book[NUMBER_OF_BOOKS];
			students = new Student[NUMBER_OF_STUDENTS];
			
			for(int i = 0; i < NUMBER_OF_BOOKS; i++) {
				books[i] = new Book(i);
			}
			
			for(int i = 0; i < NUMBER_OF_STUDENTS; i++) {
				students[i] = new Student(i, books);
				service.execute(students[i]);
			}
		}finally {
			service.shutdown();
		}
	}
}