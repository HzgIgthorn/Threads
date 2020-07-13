package de.udemy.worker;

import de.udemy.Constants.Constants;
import de.udemy.view.Board;

public class MineSweeper implements Runnable,Constants {
	
	private int id;
	private Board board;
	private boolean run;

	public MineSweeper(int id, Board board) {
		this.id = id;
		this.board = board;
		this.run = true;
	}

	@Override
	public void run() {
		while(run) {
			if(Thread.currentThread().isInterrupted()) {
				return;
			}
			
			int index = RANDOM.nextInt(BOARD_ROWS*BOARD_COLUMNS);
			board.sweepMine(index);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setSweeperRunning(boolean run) {
		this.run = run;
	}
}