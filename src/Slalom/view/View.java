package Slalom.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.BlockingQueue;

import javax.swing.SwingUtilities;

import Slalom.event.*;
import Slalom.model.Coordinates;

/**
 * Klasa obslugujaca widok widziany przez uzytkownika oraz wysyla zdarzenia generowane przez uzytkownika (ruchy) 
 */
public class View 
{
	/** ramka gry */
	private final SlalomFrame frame;
	/** panel gry */
	private final SlalomPanel panel;
	/** szerokosc okna*/
	private final int width = 600;
	/** wysokosc okna*/
	private final int height = 800;
	/** srednica kulki*/
	private final int ballDiameter = 10;
	/** kolejka zdarzen*/
	private BlockingQueue<SlalomEvent> eventQueue;
	/** współrzędne kulki */
	private Coordinates ballCords;
	/** współrzędne środków 2 kolejnych dziur */
	private double[] holeCenters;
	
	/** Ruch w lewo */
	private final int left = 37;
	/** Ruch w prawo*/
	private final int right = 39;
	/** Start nowej gry */
	private final int Start = 83;

	public View(final BlockingQueue<SlalomEvent> eventQueue)
	{
		this.eventQueue = eventQueue;
		frame = new SlalomFrame(this);
		panel = frame.getPanel();
		this.frame.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e){}
			
			@Override
			public void keyPressed(KeyEvent e) 
			{
				int key = e.getKeyCode();
				switch(key)
				{
					case left :
						eventQueue.offer(new MoveLeftEvent());
						break;
					case right:
						eventQueue.offer(new MoveRightEvent());
						break;
					case Start:
						eventQueue.offer(new StartGameEvent());
						break;
						
				}
			}
		});
	}

	/**
	 * Uaktualnia dane widoku
	 * 
	 * @param ballCords współrzędne kulki
	 * @param gameOver	informacja o tym czy gra sie zakonczyła
	 * @param holeCenters współrzędne środków dziur
	 */
	private void set(final Coordinates ballCords, final boolean gameOver, final double[] holeCenters)
	{
		this.ballCords = ballCords;
		this.holeCenters = holeCenters;
		panel.setGameOver(gameOver);
	}
	
	/**
	 * Funkcja aktualizująca widok
	 * 
	 * @param ballCords współrzędne kulki
	 * @param gameOver	informacja o tym czy gra sie zakonczyła
	 * @param holeCenters współrzędne środków dziur
	 */
	public void updateView(final Coordinates ballCords, final boolean gameOver, final double[] holeCenters)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run()
			{	
				set(ballCords, gameOver, holeCenters);
				panel.repaint();
			}
		});
	}

	/**
	 * @return szerokość okna
	 */
	int getWidth()
	{
		return width;
	}

	/**
	 * @return wysokość okna
	 */
	int getHeight()
	{
		return height;
	}

	/**
	 * @return średnica kulki
	 */
	int getBallDiameter()
	{
		return ballDiameter;
	}

	/**
	 * @return współrzędne kulki
	 */
	Coordinates getBallCords()
	{
		return ballCords;
	}

	/**
	 * @return współrzędne kolejnych środków dziur
	 */
	double[] getHoleCenters()
	{
		return holeCenters;
	}
}
