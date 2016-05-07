package Slalom.model;
/**
 * Klasa reprezentująca współrzędne
 */
public class Coordinates
{
	/** Współrzędna X*/
	private double x;
	/** Współrzędna Y */
	private double y;
	
	/**
	 * 
	 * @param x Współrzedna X
	 * @param y Współrzędna Y
	 */
	public Coordinates(final double x, final double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Coordinates(final Coordinates cords)
	{
		this.x = cords.x;
		this.y = cords.y;
	}

	/**
	 * @return współrzędna X
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * @param x współrzędna X
	 */
	void setX(double x)
	{
		this.x = x;
	}

	/**
	 * @return współrzędna Y
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * @param y współrzędna Y
	 */
	void setY(double y)
	{
		this.y = y;
	}
}
