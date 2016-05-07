package Slalom.model;

/**
 * Klasa reprezentująca dziurę
 */
class Hole
{
	/** Współrzędna środka */
	private final double center;

	/**
	 * @param center środek dziury
	 */
	public Hole(final double center)
	{
		this.center = center;
	}

	/**
	 * @return Środek dziury
	 */
	public double getCenter()
	{
		return center;
	}
}
