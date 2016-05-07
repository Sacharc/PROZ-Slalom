package Slalom.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa reprezentująca planszę.
 */
class Board
{
	/** Lista dziur stanowi mapę */
	private ArrayList<Hole> holes;
	/** Promień dziury */
	private final static double radius = 0.15;
	
	
	public Board()
	{
		holes = new ArrayList<Hole>();
		generate();
	}
	
	/**
	 * Resetuje planszę
	 */
	void resetBoard()
	{
		holes = new ArrayList<Hole>();
		generate();
	}
	
	/**
	 * Tworzy nowe poziomy jeżeli są potrzebne
	 * 
	 * @param currentLevel poziom na jakim obecnie znajduje się kulka
	 */
	public void generateNewLevelsIfNeeded(final int currentLevel)
	{
		if(currentLevel + 7 > holes.size())
			generate();
	}
	
	/**
	 * Generuje nowe dziury do mapy.
	 */
	private void generate()
	{	
		Random generator = new Random(); 
		for(int i = 0; i < 40; ++i)
			holes.add(new Hole(generator.nextDouble() * 0.7 + radius));
	}
	
	/**
	 * Sprawdza czy następuje kolizja
	 * 
	 * @param x współrzędna x na jakiej znajduje się kulka
	 * @param index	numer dziury z którą sprawdzamy czy koliduje
	 * @return	true jeżeli następuje kolizja
	 */
	public boolean checkIfColide(final double x, final int index)
	{
		double center = holes.get(index).getCenter();
		if(x > center - radius)
			if (x < center + radius)
				return false;
		return true;
	}
	
	/**
	 * Zwraca dziurę o zadanym numerze
	 * 
	 * @param index numer dziury 
	 * @return dziura o podanym numerze
	 */
	public Hole getHole(int index)
	{
		return holes.get(index);
	}
}
