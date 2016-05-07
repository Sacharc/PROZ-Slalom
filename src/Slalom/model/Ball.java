package Slalom.model;

/**
 * Klasa reprezentuje kulkę
 */
class Ball
{
	/** Wsółrzędne kulki */
	private final Coordinates cords;
	/** Prędkość kulki */
	private double speed;
	/** Wartość o jaką zwiększa się prędkość kulki */
	private final double speedIncrease = 0.0015;
	/** Wartosć co jaką zwiększa się prędkość kulki */
	private final int step = 8;
	

	public Ball()
	{
		cords = new Coordinates(0.5, 0);
		speed = 0.004;
	}
	
	
	/**
	 * Wykonuje ruch w dół i sprawdza czy pokonane zostało już piętro
	 * 
	 * @return true jeżeli piętro zostało pokonane
	 */
	boolean moveDown()
	{	
		double oldPosition = cords.getY();
		cords.setY( oldPosition + speed);
		
		if((int)(oldPosition + 0.1) == (int)(cords.getY() + 0.1))
			return false;
		increaseSpeed();
		return true;
	}
	
	/**
	 * Zwiększa prędkość kulki
	 */
	public void increaseSpeed()
	{
		if((int)cords.getY() % step == 0 )
			speed += speedIncrease;
	}
	
	/**
	 * Ruch w lewo
	 */
	void moveLeft()
	{
		if(cords.getX() > speed * 3)
			cords.setX(cords.getX() - speed * 3);
		else
			cords.setX(0);
	}
	
	/**
	 * Ruch w prawo
	 */
	void moveRight()
	{
		if(cords.getX() < 1 - (speed * 3))
			cords.setX(cords.getX() + speed * 3);
		else
			cords.setX(1);
	}
	
	/**
	 * Zwraca współrzędną X
	 * 
	 * @return X
	 */
	
	double getXcord()
	{
		return cords.getX();
	}
	
	/**
	 * Zwraca współrzędną Y
	 * 
	 * @return y
	 */
	double getYcord()
	{
		return cords.getY();
	}
	
	/**
	 * Resetuje położenie kulki
	 */
	void resetBall()
	{
		cords.setX(0.5);
		cords.setY(0);
		speed = 0.004;
	}

	/**
	 * @return współrzędne kulki
	 */
	public Coordinates getCords()
	{
		return cords;
	}
}
