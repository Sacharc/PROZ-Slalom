package Slalom.controller;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import Slalom.event.*;



import Slalom.model.Model;
import Slalom.view.View;
import Slalom.event.SlalomEvent;


public class Controller 
{
	/** Model */
	private final Model model;
	/** Widok */
	private final View view;
	/** Kolejka zdarzeń */
	private BlockingQueue<SlalomEvent> eventQueue;
	/** Mapa strategi */
	private HashMap<Class<? extends SlalomEvent>, Strategy> strategyMap;
	
	
	private final Timer timer = new Timer(10, new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			view.updateView(model.getBallCords(), model.isGameOver(), model.getNextHoles());
			if(model.isGameOver())
				timer.stop();
			else
				eventQueue.offer(new MoveDownEvent());	
		}
	});
	
	/**
	 * 
	 * @param model Model
	 * @param view Widok
	 * @param eventQueue Kolejka zdarzeń 
	 */
	public Controller(final Model model, final View view, final BlockingQueue<SlalomEvent> eventQueue)
	{
		this.model = model;
		this.view = view; 
		this.eventQueue = eventQueue;
		createStrategy();
		timer.start();
	}
	
	/**
	 * Funkcja odpowiedzialna za pracę kontrolera. Pobiera eventy z kolejki
	 */
	public void startController()
	{
		while(true)
		{
			try
			{
				SlalomEvent event = eventQueue.take();
				Strategy strategy = strategyMap.get(event.getClass());
				strategy.execute();
			} 
			catch (InterruptedException e)
			{
				//Nic nie robię, czekam na nadchodzące eventy
			}
		}
	}

	/**
	 * Funkcja tworząca mapę strategii
	 */
	private void createStrategy()
	{
		strategyMap = new HashMap<Class<? extends SlalomEvent>, Strategy>();
		strategyMap.put(MoveLeftEvent.class, new MoveLeft());
		strategyMap.put(MoveRightEvent.class, new MoveRight());
		strategyMap.put(StartGameEvent.class, new StartAgain());
		strategyMap.put(MoveDownEvent.class, new MoveDown());
	}
	
	/**
	 * Abstrakcyjna klasa strategii
	 */
	private abstract class Strategy
	{
		abstract public void execute();
	}

	/**
	 * Strategia Ruchu w lewo
	 */
	private class MoveLeft extends Strategy
	{
		public void execute()
		{
			model.moveBallLeft();
		}
	}
	
	/**
	 * Strategia Ruchu w prawo
	 */
	private class MoveRight extends Strategy
	{
		public void execute()
		{
			model.moveBallRight();
		}
	}
	
	/**
	 * Strategia ruchu w dół
	 */
	private class MoveDown extends Strategy
	{

		public void execute()
		{
			model.moveBalDown();
		}
		
	}
	
	/**
	 * Strategia Zaczecia nowej gry
	 */
	private class StartAgain extends Strategy
	{
		public void execute()
		{
			if(model.isGameOver())
			{
				System.out.println("start");
				model.startNewGame();
				timer.start();
			}
		}
	}
}
