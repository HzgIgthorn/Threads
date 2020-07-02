package de.udemy.exercises;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public interface Constants {
	
	static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");
	static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	static Random RAND = new Random();

}
