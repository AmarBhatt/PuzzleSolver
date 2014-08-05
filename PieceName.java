/**
 * PieceName.java
 * 
 * $Id: PieceName.java,v 1.1 2013/05/05 00:29:24 aab2210 Exp $
 * 
 * 
 * $Log: PieceName.java,v $
 * Revision 1.1  2013/05/05 00:29:24  aab2210
 * Solver works with all 3 puzzles.  Chess GUI fully functional.
 *
 * Code Functionality: 100%
 *
 */

/**
 * Returns piece name based on Id
 * 
 * @author Amar Bhatt, aab2210
 *
 */
public class PieceName {
	/**
	 * Returns piece name based on Id
	 * 
	 * @param piece Piece Id
	 * @return name
	 */
	public static String getName (String piece) {
		if (piece.equals("Q")){
			return "Queen";
		}//end if
		else if (piece.equals("K")){
			return "King";
		}//end else if
		else if (piece.equals("P")){
			return "Pawn";
		}//end else if
		else if (piece.equals("B")){
			return "Bishop";
		}//end else if
		else if (piece.equals("R")){
			return "Rook";
		}//end else if
		else if (piece.equals("N")){
			return "Knight";
		}//end else if
		else {
			return "Empty Space";
		}//end else
	}//end getName
}//end PieceName
