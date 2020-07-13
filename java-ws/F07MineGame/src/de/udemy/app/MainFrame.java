package de.udemy.app;

import java.awt.BorderLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import de.udemy.Constants.Constants;
import de.udemy.view.Board;
import de.udemy.view.ButtonListener;
import de.udemy.view.Toolbar;
import de.udemy.worker.MineLayer;
import de.udemy.worker.MineSweeper;

public class MainFrame extends JFrame implements ButtonListener, Constants{
	
	private static final long serialVersionUID = 1L;
	private Toolbar toolbar;
	private Board board;
	private ExecutorService layersExecutor;
	private ExecutorService sweeperExecutor;
	private MineLayer[] mineLayers;
	private MineSweeper[] mineSweepers;
	
	public MainFrame() {
		super(APP_NAME);

		toolbar = new Toolbar();
		board = new Board();
		
		init();
		
		toolbar.setButtonListener(this);
		
		add(toolbar, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		
		setSize(BOARD_WIDTH, BOARD_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void startClicked() {
		try {
			this.layersExecutor = Executors.newFixedThreadPool(NUMBER_OF_LAYERS);
			this.sweeperExecutor = Executors.newFixedThreadPool(NUMBER_OF_SWEEPERS);
			
			for(int i = 0; i < NUMBER_OF_LAYERS; i++) {
				mineLayers[i] = new MineLayer(i, board);
				layersExecutor.execute(mineLayers[i]);
			}
			
			for(int i = 0; i < NUMBER_OF_SWEEPERS; i++) {
				mineSweepers[i] = new MineSweeper(i, board);
				sweeperExecutor.execute(mineSweepers[i]);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			layersExecutor.shutdown();
			sweeperExecutor.shutdown();
		}
	}
	
	@Override
	public void stopClicked() {
		
		for(MineLayer mineLayer : this.mineLayers) {
			mineLayer.setLayerRunning(false);
		}
		
		for(MineSweeper mineSweeper : this.mineSweepers) {
			mineSweeper.setSweeperRunning(false);
		}
		
		layersExecutor.shutdown();
		sweeperExecutor.shutdown();
		
		try {
			layersExecutor.awaitTermination(1, TimeUnit.MINUTES);
			layersExecutor.awaitTermination(1, TimeUnit.MINUTES);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		layersExecutor.shutdownNow();
		sweeperExecutor.shutdownNow();
		
		this.board.clearBoard();
	}
	
	private void init() {
		mineLayers = new MineLayer[NUMBER_OF_LAYERS];
		mineSweepers = new MineSweeper[NUMBER_OF_SWEEPERS];
	}
	
	
}
