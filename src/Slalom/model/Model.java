package Slalom.model;

public class Model 
{
	/** Kulka */
	private Ball ball;
	/** Plansza */
	private Board board;
	/** Informuje czy gra sie skonczyla	 */
	private boolean gameOver;
	
	public Model()
	{
		gameOver = true;
		ball = new Ball();
		board = new Board();
	}
	
	/**
	 * Funkcja zaczyna nową grę 
	 */
	public void startNewGame()
	{
		ball.resetBall();
		board.resetBoard();
		gameOver = false;
	}
	
	/**
	 * Funkcja odpowiedzialna za ruch kulki w lewo
	 */
	public void moveBallLeft()
	{
		ball.moveLeft();
	}
	
	/**
	 * Funkcja odpowiedzialna za ruch kulki w prawo
	 */
	public void moveBallRight()
	{
		ball.moveRight();
	}
	
	/**
	 * Funkcja odpowiedzialna za ruch kulki w dół
	 */
	public void moveBalDown()
	{
		if(ball.moveDown())
		{
			board.generateNewLevelsIfNeeded((int)ball.getYcord());
			if(board.checkIfColide(ball.getXcord(), (int)ball.getYcord() + 1 ))
				gameOver = true;
		}
	}
	
	/**
	 * Zwraca współrzędne środków dwóch kolejnych dziur
	 * 
	 * @param index - numer pierwszej dziury w tablicy
	 * @return tablica środków 2 kolejnych dziur
	 */
	public double[] getNextHoles()
	{
		double[] d = new double[2];
		int index = (int)ball.getYcord() + 1;
		for(int i = 0; i < d.length; ++i)
			d[i] = board.getHole(index + i).getCenter();
		return d;
	}


	/**
	 * Zwraca współrzędne kulki
	 * 
	 * @return kopia współrzędnych kulki
	 */
	public Coordinates getBallCords()
	{
		return new Coordinates (ball.getCords());
	}
	
	/**
	 * @return true jeżeli gra się zakonczyłą
	 */
	public boolean isGameOver()
	{
		return gameOver;
	}
}
