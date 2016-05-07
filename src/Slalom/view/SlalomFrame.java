package Slalom.view;

import javax.swing.JFrame;


class SlalomFrame extends JFrame
{
	private static final long serialVersionUID = -3334206881047401410L;
	/** Referencja na widok */
	private final View view;
	/** Panel gry*/
	private final SlalomPanel panel;
	
    public SlalomFrame(final View view) 
	{
		this.view = view;
		initUI();
		panel = new SlalomPanel(view);
		add(panel);
	}
    
    /**
     * Inicjalizuje ramkę
     */
	private void initUI() 
    {    
        setTitle("Slalom");
        setSize(view.getWidth() + view.getBallDiameter(), view.getHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

	/**
	 * @return the panel
	 */
	public SlalomPanel getPanel()
	{
		return panel;
	}
}
