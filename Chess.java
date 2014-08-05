/**
 * Chess.java
 * 
 * $Id: Chess.java,v 1.1 2013/05/05 00:57:13 aab2210 Exp $
 * 
 * 
 * 
 * $Log: Chess.java,v $
 * Revision 1.1  2013/05/05 00:57:13  aab2210
 * Added Warnings. Fixed Recolor. Changed ChessModel to Chess.
 *
 *
 *
 * 
 * 
 */
import java.util.ArrayList;

/**
 * Behind the scenes Chess Solitare game play
 * 
 * @author Amar Bhatt, aab2210
 *
 */
public class Chess extends Object implements Puzzle<Board> {
	/**
	 * Starting configuration
	 */
	private Board start;
	/**
	 * Current Board State
	 */
	public Board currentState;
	/**
	 * Number of rows
	 */
	public static int BOARD_ROWS;
	/**
	 * Number of columns
	 */
	public static int BOARD_COLS;
	
	
	@Override
	public Board getStart() {
		return start;
	}//end getStart

	@Override
	public boolean getGoal(Board config) {
		boolean res = false;
		int piece_count = 0;
		for (int i = 0; i < BOARD_ROWS; i++) {
			for (int j = 0; j < BOARD_COLS; j++){
				if (!(config.getBoard().get(i).get(j).equals("."))){
					piece_count++;
				}//end if
			}//end for
		}//end for
		if (piece_count == 1){
			res = true;
		}//end if
		return res;
	}//end getGoal
	/**
	 * Finds all possible moves based on Piece, and location
	 * 
	 * @param piece Piece that moves need to be found for
	 * @param config Board state being tested
	 * @param r Initial row
	 * @param c Initial column
	 * @return ArrayList of Board configurations
	 */
	private ArrayList<Board> getMove (String piece, Board config, int r, int c) {
		ArrayList<Board> moveList = new ArrayList<Board>();
		if (piece.equals("Q")) {
			boolean found = false;
			//Horizontal
			for (int i = c; i < BOARD_COLS; i++) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(r).get(i).equals("."))) && (!(i == c)) && !found) {
					current.setBoard(r,i,"Q");
					current.setBoard(r,c,".");
					moveList.add(current);
					found = true;
					//break;
				}//end if
			}//end for
			found = false;
			for (int i = c; i >= 0; i--) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(r).get(i).equals("."))) && (!(i == c)) && !found) {
					current.setBoard(r,i,"Q");
					current.setBoard(r,c,".");
					moveList.add(current);
					found = true;
					//break;
				}//end if
			}//end for
			
			found = false;
			//Vertical
			for (int i = r; i < BOARD_ROWS; i++) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(i).get(c).equals("."))) && (!(i == r)) && !found) {
					current.setBoard(i,c,"Q");
					current.setBoard(r,c,".");
					moveList.add(current);
					found = true;
					//break;
				}//end if
			}//end for	
			found = false;
			for (int i = r; i >= 0; i--) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(i).get(c).equals("."))) && (!(i == r)) && !found) {
					current.setBoard(i,c,"Q");
					current.setBoard(r,c,".");
					moveList.add(current);
					found = true;
					//break;
				}//end if
			}//end for	
			//Diagonal to upper left
			int indexR = r;
			int indexC = c;
			found = false;
			while ((indexR != -1) && (indexC != -1) && !found) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(indexR).get(indexC).equals("."))) && (indexR != r) && (indexC != c)) {
					current.setBoard(indexR,indexC,"Q");
					current.setBoard(r,c,".");
					moveList.add(current);
					found = true;
					//break;
				}//end if
				indexR--;
				indexC--;
			}//end while
			//Diagonal to lower right
			indexR = r;
			indexC = c;
			found = false;
			while ((indexR != BOARD_ROWS) && (indexC != BOARD_COLS) && !found) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(indexR).get(indexC).equals("."))) && (indexR != r) && (indexC != c)) {
					current.setBoard(indexR,indexC,"Q");
					current.setBoard(r,c,".");
					moveList.add(current);
					found = true;
					//break;
				}//end if
				indexR++;
				indexC++;
			}//end while
			//Diagonal to upper right
			indexR = r;
			indexC = c;
			found = false;
			while ((indexR != -1) && (indexC != BOARD_COLS) && !found) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(indexR).get(indexC).equals("."))) && (indexR != r) && (indexC != c)) {
					current.setBoard(indexR,indexC,"Q");
					current.setBoard(r,c,".");
					moveList.add(current);
					found = true;
					//break;
				}//end if
				indexR--;
				indexC++;
			}//end while
			//Diagonal to lower left
			indexR = r;
			indexC = c;
			found = false;
			while ((indexR != BOARD_ROWS) && (indexC != -1) && !found) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(indexR).get(indexC).equals("."))) && (indexR != r) && (indexC != c)) {
					current.setBoard(indexR,indexC,"Q");
					current.setBoard(r,c,".");
					moveList.add(current);
					found = true;
					//break;
				}//end if
				indexR++;
				indexC--;
			}//end while
			return moveList;
		}//end if
		
		
		else if (piece.equals("K")){
			boolean upwards = !(r-1 <= -1);
			boolean downwards = !(r+1 >= BOARD_ROWS);
			boolean leftwards = !(c-1 <= -1);
			boolean rightwards = !(c+1 >= BOARD_COLS);
			
			//up
			if (upwards) {
				if (!(config.getBoard().get(r-1).get(c).equals("."))){
					Board current = config.getCopy();
					current.setBoard(r-1,c,"K");
					current.setBoard(r,c,".");
					moveList.add(current);
				}//end if
				//upper left
				if (leftwards){
					if (!(config.getBoard().get(r-1).get(c-1).equals("."))){
						Board current = config.getCopy();
						current.setBoard(r-1,c-1,"K");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
				//upper right
				if (rightwards){
					if (!(config.getBoard().get(r-1).get(c+1).equals("."))){
						Board current = config.getCopy();
						current.setBoard(r-1, c+1, "K");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
			}//end if
						
			//down
			if (downwards){
				if (!(config.getBoard().get(r+1).get(c).equals("."))){
					Board current = config.getCopy();
					current.setBoard(r+1, c, "K");
					current.setBoard(r,c,".");
					moveList.add(current);
				}//end if
				//upper left
				if (leftwards){
					if (!(config.getBoard().get(r+1).get(c-1).equals("."))){
						Board current = config.getCopy();
						current.setBoard(r+1,c-1,"K");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
				//upper right
				if (rightwards){
					if (!(config.getBoard().get(r+1).get(c+1).equals("."))){
						Board current = config.getCopy();
						current.setBoard(r+1,c+1,"K");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
			}//end if
			//horizontal
			if (leftwards) {
				if (!(config.getBoard().get(r).get(c-1).equals("."))){
					Board current = config.getCopy();
					current.setBoard(r,c-1,"K");
					current.setBoard(r,c,".");
					moveList.add(current);
				}//end if
			}//end if
			if (rightwards) {
				if (!(config.getBoard().get(r).get(c+1).equals("."))){
					Board current = config.getCopy();
					current.setBoard(r,c+1,"K");
					current.setBoard(r,c,".");
					moveList.add(current);
				}//end if
			}//end if
			return moveList;			
		}//end else if
		
		else if (piece.equals("P")){
			boolean upwards = !(r-1 <= -1);
			boolean leftwards = !(c-1 <= -1);
			boolean rightwards = !(c+1 >= BOARD_COLS);
			
			//up
			if (upwards) {
				//upper left
				if (leftwards){
					if (!(config.getBoard().get(r-1).get(c-1).equals("."))){
						Board current = config.getCopy();
						current.setBoard(r-1,c-1,"P");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
				//upper right
				if (rightwards){
					if (!(config.getBoard().get(r-1).get(c+1).equals("."))){
						Board current = config.getCopy();
						current.setBoard(r-1,c+1,"P");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
			}//end if
			return moveList;
		}//end else if
		else if (piece.equals("B")){
			//Diagonal to upper left
			int indexR = r;
			int indexC = c;
			while ((indexR != -1) && (indexC != -1)) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(indexR).get(indexC).equals("."))) && (indexR != r) && (indexC != c)) {
					current.setBoard(indexR,indexC,"B");
					current.setBoard(r,c,".");
					moveList.add(current);
					break;
				}//end if
				indexR--;
				indexC--;
			}//end while
			//Diagonal to lower right
			indexR = r;
			indexC = c;
			while ((indexR != BOARD_ROWS) && (indexC != BOARD_COLS)) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(indexR).get(indexC).equals("."))) && (indexR != r) && (indexC != c)) {
					current.setBoard(indexR,indexC,"B");
					current.setBoard(r,c,".");
					moveList.add(current);
					break;
				}//end if
				indexR++;
				indexC++;
			}//end while
			//Diagonal to upper right
			indexR = r;
			indexC = c;
			while ((indexR != -1) && (indexC != BOARD_COLS)) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(indexR).get(indexC).equals("."))) && (indexR != r) && (indexC != c)) {
					current.setBoard(indexR,indexC,"B");
					current.setBoard(r,c,".");
					moveList.add(current);
					break;
				}//end if
				indexR--;
				indexC++;
			}//end while
			//Diagonal to lower left
			indexR = r;
			indexC = c;
			while ((indexR != BOARD_ROWS) && (indexC != -1)) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(indexR).get(indexC).equals("."))) && (indexR != r) && (indexC != c)) {
					current.setBoard(indexR,indexC,"B");
					current.setBoard(r,c,".");
					moveList.add(current);
					break;
				}//end if
				indexR++;
				indexC--;
			}//end while
			return moveList;
		}//end else if
		else if (piece.equals("N")){
			boolean upwards1 = (r-1 > -1);
			boolean downwards1 = (r+1 < BOARD_ROWS);
			boolean leftwards1 = (c-1 > -1);
			boolean rightwards1 = (c+1 < BOARD_COLS);
			
			boolean upwards2 = (r-2 > -1);
			boolean downwards2 = (r+2 < BOARD_ROWS);
			boolean leftwards2 = (c-2 > -1);
			boolean rightwards2 = (c+2 < BOARD_COLS);
			
			//up
			if (upwards2){
				if (leftwards1){					
					if ((!(config.getBoard().get(r-2).get(c-1).equals(".")))) {
						Board current = config.getCopy();
						current.setBoard(r-2, c-1, "N");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
				if (rightwards1){
					if ((!(config.getBoard().get(r-2).get(c+1).equals(".")))) {
						Board current = config.getCopy();
						current.setBoard(r-2,c+1,"N");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
			}//end if
			if (downwards2){
				if (leftwards1){					
					if ((!(config.getBoard().get(r+2).get(c-1).equals(".")))) {
						Board current = config.getCopy();
						current.setBoard(r+2,c-1, "N");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
				if (rightwards1){
					if ((!(config.getBoard().get(r+2).get(c+1).equals(".")))) {
						Board current = config.getCopy();
						current.setBoard(r+2,c+1,"N");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
			}//end if
			
			//rightward
			if (rightwards2) {
				if (upwards1) {
					if ((!(config.getBoard().get(r-1).get(c+2).equals(".")))) {
						Board current = config.getCopy();
						current.setBoard(r-1,c+2,"N");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
				if (downwards1) {
					if ((!(config.getBoard().get(r+1).get(c+2).equals(".")))) {
						Board current = config.getCopy();
						current.setBoard(r+1,c+2,"N");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
			}//end if
			//leftwards
			if (leftwards2){
				if (upwards1) {
					if ((!(config.getBoard().get(r-1).get(c-2).equals(".")))) {
						Board current = config.getCopy();
						current.setBoard(r-1,c-2,"N");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
				if (downwards1) {
					if ((!(config.getBoard().get(r+1).get(c-2).equals(".")))) {
						Board current = config.getCopy();
						current.setBoard(r+1,c-2,"N");
						current.setBoard(r,c,".");
						moveList.add(current);
					}//end if
				}//end if
			}//end if
			return moveList;
		}//end else if
		else if (piece.equals("R")){
			//Horizontal
			for (int i = c; i < BOARD_COLS; i++) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(r).get(i).equals("."))) && (i != c)) {
					current.setBoard(r,i,"R");
					current.setBoard(r,c,".");
					moveList.add(current);
					break;
				}//end if
			}//end for
			for (int i = c; i >= 0; i--) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(r).get(i).equals("."))) && (i != c)) {
					current.setBoard(r,i,"R");
					current.setBoard(r,c,".");
					moveList.add(current);
					break;
				}//end if
			}//end for
			//Vertical
			for (int i = r; i < BOARD_ROWS; i++) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(i).get(c).equals("."))) && (i != r)) {
					current.setBoard(i,c,"R");
					current.setBoard(r,c,".");
					moveList.add(current);
					break;
				}//end if
			}//end for
			for (int i = r; i >= 0; i--) {
				Board current = config.getCopy();
				if ((!(current.getBoard().get(i).get(c).equals("."))) && (i != r)) {
					current.setBoard(i,c,"R");
					current.setBoard(r,c,".");
					moveList.add(current);
					break;
				}//end if
			}//end for
			return moveList;
		}//end else if
		else {
			return moveList;
		}//end else
	}//end getMove
	
	@Override
	public ArrayList<Board> getNeighbors(Board config) {
		ArrayList<Board> neighbors = new ArrayList<Board>();
		for (int i = 0; i < BOARD_ROWS; i++) {
			for (int j = 0; j < BOARD_COLS; j++){
				
				if (config.getBoard().get(i).get(j).equals("Q")){
					neighbors.addAll(getMove("Q", config, i, j));
				}//end if
				else if (config.getBoard().get(i).get(j).equals("K")){
					neighbors.addAll(getMove("K", config, i, j));
				}//end else if
				else if (config.getBoard().get(i).get(j).equals("N")){
					neighbors.addAll(getMove("N", config, i, j));
				}//end else if
				else if (config.getBoard().get(i).get(j).equals("B")){
					neighbors.addAll(getMove("B", config, i, j));
				}//end else if
				else if (config.getBoard().get(i).get(j).equals("R")){
					neighbors.addAll(getMove("R", config, i, j));
				}//end else if
				else if (config.getBoard().get(i).get(j).equals("P")){
					neighbors.addAll(getMove("P", config, i, j));
				}//end else if
			}//end for
		}//end for
		return neighbors;
	}//end getNeighbors
	/**
	 * Returns current state of board
	 * 
	 * @return currentState arraylist
	 */
	public ArrayList<String> getCurrentState() {
		ArrayList<String> current = new ArrayList<String>();
		for (int i = 0; i < BOARD_ROWS; i++){
			for (int j = 0; j < BOARD_COLS; j++) {
				current.add(currentState.getBoard().get(i).get(j));
			}//end for
		}//end for		
		return current;
	}//end currentState
	
	/**
	 * Prints Boards to output stream
	 * 
	 * @param board Board being printed
	 */
	private static void print (Board board) {
		for (int i = 0; i < BOARD_ROWS; i ++){
			for (int j = 0; j < BOARD_COLS; j++){
				System.out.print(board.getBoard().get(i).get(j));
			}//end for
			System.out.println("");
		}//end for
	}//end print
	/**
	 * Prints Board objects from an ArrayList
	 * 
	 * @param boardList Board List being printed
	 */
	@SuppressWarnings("unused")
	private static void printArray (ArrayList<Board> boardList){
		for (Board board : boardList){
			print(board);
		}//end for
	}//end printArray
	
	/**
	 * Sets new currentState
	 * 
	 * @param current actual current state 2D ArrayList
	 */
	public void setCurrent (ArrayList<ArrayList<String>> current){
		currentState = new Board (current);
	}//end setCurrent
	/**
	 * Constructor for new model
	 * 
	 * @param firststart starting config
	 * @param BOARD_ROWS number of rows
	 * @param BOARD_COLS number of columns
	 */
	@SuppressWarnings("static-access")
	public Chess (ArrayList<ArrayList<String>> firststart, int BOARD_ROWS, int BOARD_COLS) {
		this.start = new Board(firststart);
		this.BOARD_ROWS = BOARD_ROWS;
		this.BOARD_COLS = BOARD_COLS;		
	}//end Constructor
	/**
	 * Using Solver, next move on BFS path is found
	 * 
	 * @return Board configuration
	 */
	public boolean getNextMove() {
		Puzzle<Board> chess1 = new Chess(currentState.getBoard(), BOARD_ROWS, BOARD_COLS);
		Solver <Board> x = new Solver <Board>();
		ArrayList<Board> solution = x.solvePuzzle(chess1);
		if ((solution != null)){
			if (solution.size() != 1){
				ArrayList<ArrayList<String>> current = new ArrayList<ArrayList<String>>();
				for (int i = 0; i < Chess.BOARD_ROWS; i++){
					ArrayList<String> rows = new ArrayList<String>();
					for (int j = 0; j < Chess.BOARD_COLS; j ++){
						String piece = new String(solution.get(1).getBoard().get(i).get(j));
						rows.add(piece);
					}//end for
					current.add(rows);
				}//end for
				setCurrent(current);
				return true;
			}//end if
			else {
				return false;
			}//end else
		}//end if
		else {
			return false;
		}//end else
	}//end getNextMove
	/**
	 * Checks for a valid move
	 * 
	 * @param start Piece being moved
	 * @param row1 Initial row
	 * @param col1 Initial col
	 * @param end Piece being moved to
	 * @param row2 End row
	 * @param col2 End col
	 * @return boolean whether true or false for move validity
	 */
	public boolean isValid(String start, int row1, int col1, String end, int row2, int col2) {
		if (currentState.getBoard().get(row2).get(col2).equals(".")){
			return false;
		}//end if
		else {
			ArrayList<Board> possibleMoves = getMove(start, currentState, row1, col1);
			Board copyBoard = currentState.getCopy();
			copyBoard.setBoard(row1, col1, ".");
			copyBoard.setBoard(row2, col2, start);
			for (Board board : possibleMoves){
				if (copyBoard.equals(board)){
					if (start.equals("Q")){						
						//horizontal
						if (row1 == row2) {
							return true;
						}//end if
						//vertical
						else if (col1 == col2){
							return true;
						}//end else if
						//Diagonal to upper left
						else {
							int indexR = row1;
							int indexC = col1;
							while ((indexR != -1) && (indexC != -1)) {
								if ((indexR == row2) && (indexC == col2)) {
									return true;
								}//end if
								indexR--;
								indexC--;
							}//end while
							//Diagonal to lower right
							indexR = row1;
							indexC = col1;
							while ((indexR != BOARD_ROWS) && (indexC != BOARD_COLS)) {
								if ((indexR == row2) && (indexC == col2)) {
									return true;
								}//end if
								indexR++;
								indexC++;
							}//end while
							//Diagonal to upper right
							indexR = row1;
							indexC = col1;
							while ((indexR != -1) && (indexC != BOARD_COLS)) {
								if ((indexR == row2) && (indexC == col2)) {
									return true;
								}//end if
								indexR--;
								indexC++;
							}//end while
							//Diagonal to lower left
							indexR = row1;
							indexC = col1;
							while ((indexR != BOARD_ROWS) && (indexC != -1)) {
								if ((indexR == row2) && (indexC == col2)) {
									return true;
								}//end if
								indexR++;
								indexC--;
							}//end while
						}//end else
					}//end if
					else if (start.equals("K")){
						//up
						if (row1-1 == row2){
							if (col1 == col2){					
								return true;
							}//end if
							else if (col1-1 == col2){
								return true;
							}//end else if
							else if (col1+1 == col2){
								return true;
							}//end else if
						}//end if
						//down
						if (row1+1 == row2){
							if (col1 == col2){					
								return true;
							}//end if
							else if (col1-1 == col2){
								return true;
							}//end else if
							else if (col1+1 == col2){
								return true;
							}//end else if
						}//end if
						//left and right
						if (row1 == row2){
							if (col1-1 == col2){
								return true;
							}//end if
							else if (col1+1 == col2){
								return true;
							}//end else if
						}//end if
					}//end else if
					
					else if (start.equals("P")){
						//up
						if (row1-1 == row2){
							if (col1-1 == col2){
								return true;
							}//end else if
							else if (col1+1 == col2){
								return true;
							}//end else if
						}//end if
					}//end else if
					
					else if (start.equals("B")){
						//Diagonal to upper left
							int indexR = row1;
							int indexC = col1;
							while ((indexR != -1) && (indexC != -1)) {
								if ((indexR == row2) && (indexC == col2)) {
									return true;
								}//end if
								indexR--;
								indexC--;
							}//end while
							//Diagonal to lower right
							indexR = row1;
							indexC = col1;
							while ((indexR != BOARD_ROWS) && (indexC != BOARD_COLS)) {
								if ((indexR == row2) && (indexC == col2)) {
									return true;
								}//end if
								indexR++;
								indexC++;
							}//end while
							//Diagonal to upper right
							indexR = row1;
							indexC = col1;
							while ((indexR != -1) && (indexC != BOARD_COLS)) {
								if ((indexR == row2) && (indexC == col2)) {
									return true;
								}//end if
								indexR--;
								indexC++;
							}//end while
							//Diagonal to lower left
							indexR = row1;
							indexC = col1;
							while ((indexR != BOARD_ROWS) && (indexC != -1)) {
								if ((indexR == row2) && (indexC == col2)) {
									return true;
								}//end if
								indexR++;
								indexC--;
							}//end while
					}//end else if
					
					else if (start.equals("R")){
						if (row1 == row2) {
							return true;
						}//end if
						//vertical
						else if (col1 == col2){
							return true;
						}//end else if
					}//end else if
					
					else if (start.equals("N")){
						//up
						if (row1-2 == row2){
							if (col1-1 == col2){
								return true;
							}//end if
							else if (col1+1 == col2){
								return true;
							}//end else if
						}//end if
						//down
						if (row1+2 == row2){
							if (col1-1 == col2){
								return true;
							}//end if
							else if (col1+1 == col2){
								return true;
							}//end else if
						}//end if
						//left
						if (col1-2 == col2){
							if (row1-1 == row2){
								return true;
							}//end if
							else if (row1+1 == row2){
								return true;
							}//end else if
						}//end if
						//right
						if (col1+2 == col2){
							if (row1-1 == row2){
								return true;
							}//end if
							else if (row1+1 == row2){
								return true;
							}//end else if
						}//end if
					}//end else if
				}//end if
			}//end for
			return false;
		}//end else
	}//end isValid
	/**
	 * Updates currentState to user defined move
	 * 
	 * @param position1 Initial position
	 * @param position2 End position
	 * @return
	 */
	public boolean makeMove (int position1, int position2){
		String piece1 = getCurrentState().get(position1);
		int row1 = 0;
		int col1 = 0;
		String piece2 = getCurrentState().get(position2);
		int row2 = 0;
		int col2 = 0;
		int currentCount = 0;
		for (int i = 0; i < BOARD_ROWS; i++){
			for (int j = 0; j < BOARD_COLS; j++){
				if (currentCount == position1){
					row1 = i;
					col1 = j;
				}//end if
				else if (currentCount == position2){
					row2 = i;
					col2 = j;
				}//end else if
				currentCount++;
			}//end for
		}//end for
		if (isValid(piece1,row1,col1,piece2,row2,col2)){
			currentState.setBoard(row1, col1, ".");
			currentState.setBoard(row2, col2, piece1);
			return true;
		}//end if
		else {
			return false;
		}//end else
	}//end makeMove
	/**
	 * Resets board to starting configuration
	 */
	public void reset(){
		ArrayList<ArrayList<String>> current = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < Chess.BOARD_ROWS; i++){
			ArrayList<String> rows = new ArrayList<String>();
			for (int j = 0; j < Chess.BOARD_COLS; j ++){
				String piece = new String(start.getBoard().get(i).get(j));
				rows.add(piece);
			}//end for
			current.add(rows);
		}//end for
		setCurrent(current);
	}//end reset

}//end Chess Model
