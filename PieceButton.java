/**
 * PieceButton.java
 * 
 * 
 * $Id: PieceButton.java,v 1.1 2013/05/05 00:29:25 aab2210 Exp $
 * 
 * 
 * $Log: PieceButton.java,v $
 * Revision 1.1  2013/05/05 00:29:25  aab2210
 * Solver works with all 3 puzzles.  Chess GUI fully functional.
 *
 * Code Functionality: 100%
 *
 * 
 * 
 */


import javax.swing.JButton;



/**
 * Creates JButtons
 * 
 * @author Amar Bhatt, aab2210
 *
 */
public class PieceButton extends JButton {

	/**
	 * Class Generated
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Position of button on game grid
	 */
	private int pos;
	/**
	 * Constructor
	 * 
	 * @param name Name on Button
	 * @param pos Position of Button
	 */
	public PieceButton (String name, int pos) {
		super(name);
		this.pos = pos;
	}//end Constructor
	/**
	 * Returns position
	 * @return position
	 */
	public int getPos() {return pos;}

}//end PieceButton
