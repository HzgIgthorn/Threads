package de.udemy.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import de.udemy.Constants.Constants;

public class Board extends JPanel implements Constants{
	
	private static final long serialVersionUID = 1L;
	private Cell[] cells;
	private int numberOfMines;
	
	public Board() {
		init();
		setLayout(new GridLayout(BOARD_ROWS, BOARD_COLUMNS));
		initBoard();
	}
	
	public synchronized void incrementBombNumber() {
		this.numberOfMines++;
	}
	
	public synchronized void decrementBombNumber() {
		this.numberOfMines--;
	}

	public void clearBoard() {
		for(int i = 0; i < BOARD_COLUMNS* BOARD_ROWS; i++) {
			int row = (i / BOARD_ROWS) % 2;
			if(row == 0) {
				cells[i].setBackground((i % 2 == 0)? Color.GRAY: Color.WHITE);
			}else {
				cells[i].setBackground((i % 2 == 0)? Color.WHITE: Color.GRAY);
			}
		}
		
	}

	public void setMine(int index) {
		cells[index].lock();
		incrementBombNumber();
		cells[index].setBackground(Color.RED);
		cells[index].unlock();
		
		sleepThread(500);
		
	}

	public void sweepMine(int index) {
		cells[index].lock();
		decrementBombNumber();
		int row = (index / BOARD_ROWS) % 2;
		
		if(row == 0) {
			cells[index].setBackground((index % 2 == 0)? Color.GRAY : Color.WHITE);
		}else {
			cells[index].setBackground((index % 2 == 0)? Color.WHITE : Color.GRAY);
		}
		
		cells[index].unlock();
		sleepThread(500);
	}
	
	public int getNumberOfMines() {
		return numberOfMines;
	}

	private void init() {
		this.cells = new Cell[BOARD_ROWS*BOARD_COLUMNS];
		this.numberOfMines = 0;
	}

	private void initBoard() {
		for(int i = 0; i < BOARD_COLUMNS * BOARD_ROWS; i++) {
			cells[i] = new Cell(i+1);
			add(cells[i]);
			
			int row = (i / BOARD_ROWS) % 2;
			
			if(row == 0) {
				cells[i].setBackground((i % 2 == 0)? Color.GRAY: Color.WHITE);
			}else {
				cells[i].setBackground((i % 2 == 0)? Color.WHITE: Color.GRAY);
			}
		}
	}
	
	private void sleepThread(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}