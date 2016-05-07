package Slalom.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


/**
 * Panel służący do rysowania mapy 
 */
class SlalomPanel extends JPanel
{
	private static final long serialVersionUID = 1984086429533491554L;
	/**Referencja na widok */
	private final View view;
	/** Informacja czy gra się skonczyła */
	private boolean gameOver;
	/** Informacja czy jest to pierwsze uruchomienie gry */
	private boolean firstRun;
	/** Odległość miedzy pietrami */
	private final int floorDistance;
	/** Punkt zawieszenia kulki */
	private final int ballPosition;
	
	
	/**
	 * @param view Widok
	 */
	public SlalomPanel(final View view)
	{
		super();
		this.view = view;
		firstRun = true;
		gameOver = true;
		floorDistance = view.getHeight() / 2;
		ballPosition = floorDistance / 10;
	}
	
	/**
	 * Funkcja wybiera czy rysować mapę czy menu
	 */
	private void draw(final Graphics g)
	{
		if(gameOver)
		{
			drawMenu(g);
			if(!firstRun)
				drawMap(g);
		}
		else
			drawMap(g);
	}

	/**
	 * Rysuje mapę gry
	 */
	private void drawMap(final Graphics g)
	{
		firstRun = false;
		final double[] holeCenters = view.getHoleCenters();
		double startValue = view.getBallCords().getY() * floorDistance;
		int t = floorDistance - ((int)startValue % floorDistance);
		Double db;
		Double db2;
		
		for(int i = 0; i<2; ++i)
		{
			db = holeCenters[i] * view.getWidth() - 90;
			db2 = holeCenters[i] * view.getWidth() + 90 ;
			
			g.fillRect(0, t + i*floorDistance, db.intValue(), 5);
			
			g.fillRect(db2.intValue() + view.getBallDiameter(), t + i*floorDistance, view.getWidth() - db2.intValue() + view.getBallDiameter(), 5);
		}
		
		g.setColor(Color.red);
		g.fillOval((int)(view.getBallCords().getX() * view.getWidth()), ballPosition - view.getBallDiameter(), view.getBallDiameter(), view.getBallDiameter());
		
		String s = new String() + (int)(view.getBallCords().getY());
		g.setFont(g.getFont().deriveFont(18.0f));
		g.drawString(s, 500, 750);
	}

	/**
	 * Rysuje menu
	 */
	private void drawMenu(final Graphics g)
	{
		String s = new String ("Nacisnij S aby rozpoczac gre");
		g.fillRect(0, 300, view.getWidth() + view.getBallDiameter(), 200);
		g.setColor(Color.white);
		g.setFont(g.getFont().deriveFont(18.0f));
		g.drawString(s, 150, 400);
		g.setColor(Color.black);
	}
	
	/**
	 * @param gameOver 
	 */
	public void setGameOver(final boolean gameOver)
	{
		this.gameOver = gameOver;
	}
	
	public void paintComponent(final Graphics g)
	{    
		super.paintComponent(g);
		draw(g);
	}	
}
