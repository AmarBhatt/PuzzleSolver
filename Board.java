/**
 * Board.java
 * 
 * 
 * $Id: Board.java,v 1.3 2013/05/05 00:57:12 aab2210 Exp $
 * 
 * 
 * $Log: Board.java,v $
 * Revision 1.3  2013/05/05 00:57:12  aab2210
 * Added Warnings. Fixed Recolor. Changed ChessModel to Chess.
 *
 * Revision 1.2  2013/05/05 00:29:25  aab2210
 * Solver works with all 3 puzzles.  Chess GUI fully functional.
 *
 * Code Functionality: 100%
 *
 * 
 * 
 */


import java.util.*;
/**
 * Creates Board Configurations
 * 
 * @author Amar Bhatt, aab2210
 *
 */
public class Board {
	
	/**
	 * Board Configuration
	 */
	private ArrayList<ArrayList<String>> board;
	
	/**
	 * Constructor
	 * @param board Board needed to be made
	 */
	public Board (ArrayList<ArrayList<String>> board){
		this.board = board;
	}//end Constructor
	
	@Override
	public boolean equals(Object o) {
		Board board1 = (Board)o;
		for (int i = 0; i < Chess.BOARD_ROWS; i++){
			for (int j = 0; j < Chess.BOARD_COLS; j++){
				if (!(this.getBoard().get(i).get(j).equals(board1.getBoard().get(i).get(j)))){
					return false;
				}//end if
			}//end for
		}//end for
		return true;
	}//end equals
	@Override
	public int hashCode(){
		int res = 0;
		for (int i = 0; i < Chess.BOARD_ROWS; i ++ ){
			for (int j = 0; j < Chess.BOARD_COLS; j ++){
				res+= pieceValue(board.get(i).get(j))*(i+1) + j*4;
			}//end for
		}//end for
		return res;		
	}//end hashCode
	/**
	 * Values Pieces for hashcode()
	 * @param piece Piece being valued
	 * 
	 * @return Piece Value
	 */
	public int pieceValue(String piece){
		if (piece.equals("Q")){
			return 10;
		}//end if
		else if (piece.equals("K")){
			return 12;
		}//end else if
		else if (piece.equals("P")){
			return 1;
		}//end else if
		else if (piece.equals("N")){
			return 3;
		}//end else if
		else if (piece.equals("B")){
			return 4;
		}//end else if
		else if (piece.equals("R")){
			return 5;
		}//end else if
		else {
			return 0;
		}//end else
	}//end pieceValue
	/**
	 * Returns board object as a 2D ArrayList
	 * 
	 * @return board as 2D ArrayList
	 */
	public ArrayList<ArrayList<String>> getBoard(){
		return board;
	}//end getBoard
	/**
	 * Copies board and makes new instance
	 * @return new board object
	 */
	public Board getCopy() {
		ArrayList<ArrayList<String>> current = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < Chess.BOARD_ROWS; i++){
			ArrayList<String> row = new ArrayList<String>();
			for (int j = 0; j < Chess.BOARD_COLS; j ++){
				String piece = new String(board.get(i).get(j));
				row.add(piece);
			}//end for
			current.add(row);
		}//end for
		return new Board(current);
	}//end getCopy
	/**
	 * Updates board
	 * 
	 * @param row Row being updated
	 * @param col Column being updated
	 * @param piece Piece being set
	 */
	public void setBoard(int row, int col, String piece){
		board.get(row).set(col, piece);
	}//end setBoard
}//end Board
