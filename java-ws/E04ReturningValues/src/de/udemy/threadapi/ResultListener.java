package de.udemy.threadapi;

public interface ResultListener<T> {
	void notifyResult(T result);
}
