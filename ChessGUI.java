/**
 * 
 * ChessGUI.java
 * 
 * 
 * $Id: ChessGUI.java,v 1.2 2013/05/05 00:57:12 aab2210 Exp $
 * 
 * 
 * $Log: ChessGUI.java,v $
 * Revision 1.2  2013/05/05 00:57:12  aab2210
 * Added Warnings. Fixed Recolor. Changed ChessModel to Chess.
 *
 * Revision 1.1  2013/05/05 00:29:25  aab2210
 * Solver works with all 3 puzzles.  Chess GUI fully functional.
 *
 * Code Functionality: 100%
 *
 * 
 * 
 */


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



/**
 * Runs the user viewed GUI for chess solitare using model
 * 
 * @author Amar Bhatt, aab2210
 *
 */
public class ChessGUI extends JFrame implements Observer {
	
	/**
	 * Class Generated
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Current Game Details/State
	 */
	private Chess model;
	/**
	 * List of chess board buttons
	 */
	private ArrayList<PieceButton> buttons = new ArrayList<PieceButton>();
	/**
	 * Status Update Text
	 */
	private JLabel status;
	/**
	 * First Button Clicked
	 */
	private int firstButton = -1;
	/**
	 * Second Button Clicked
	 */
	private int secondButton = -1;
	/**
	 * Number of pieces selected, 0 - 1
	 */
	private int pieceSelect = 0;
	/**
	 * number of moves
	 */
	private int move = 0;
	
	/**
	 * ActionListener Class for all buttons * 
	 *
	 */
	class ButtonListener implements ActionListener {
		/**
		 * Calls Update after button has been pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Object buttonObject = e.getSource();
            Observable listener = new Observable();
            if (buttonObject instanceof PieceButton) {
                update(listener, buttonObject);
            }//end if
            else if (buttonObject instanceof JButton){
            	update(listener, buttonObject);
            }//else if		
		}//end actionPerformed		
	}//end ButtonListener
	
	/**
	 * Constructs GUI based off current game
	 * 
	 * @param model Current Game
	 */
	public ChessGUI (Chess model) {
		this.model = model;
		
		JFrame userView = new JFrame("Chess Solitare!   Amar Bhatt, aab2210");
		userView.setLayout(new BorderLayout());
		
		//Make Game Grid
		Panel game = new Panel();
		game.setLayout(new GridLayout(Chess.BOARD_ROWS,Chess.BOARD_COLS));
		ButtonListener listener = new ButtonListener();
		PieceButton button;
		Boolean white = true;
		int count = 0;
		for (int i = 0; i < model.getCurrentState().size(); i++){
			if (!(model.getCurrentState().get(i).equals("."))){
				button = new PieceButton(model.getCurrentState().get(i), i);
			}//end if
			else {
				button = new PieceButton("", i);
			}//end else
			if (Chess.BOARD_COLS%2 != 0){
				if (white){
					button.setBackground(Color.WHITE);
					button.setForeground(Color.BLACK);
					white= false;
				}//end if
				else {
					button.setBackground(Color.BLACK);
					button.setForeground(Color.WHITE);
					white = true;
				}//end else
			}//end if
			else {
				if (count%Chess.BOARD_COLS == 0){
					white = !(white.booleanValue());
				}//end if
				if (white){
					button.setBackground(Color.WHITE);
					button.setForeground(Color.BLACK);
					white= false;
				}//end if
				else {
					button.setBackground(Color.BLACK);
					button.setForeground(Color.WHITE);
					white = true;
				}//end else
				count++;
			}//end else
			button.addActionListener(listener);
			game.add(button);
			buttons.add(button);
		}//end for
		
		//user Buttons (Reset, Cheat, Undo)
		Panel userButtons = new Panel();
		userButtons.setLayout(new FlowLayout());
		JButton reset = new JButton("Reset");
		reset.addActionListener(listener);
		JButton nextmove = new JButton("Next Move");
		nextmove.addActionListener(listener);
		JButton help = new JButton("Help");
		help.addActionListener(listener);
		JButton quit = new JButton("Quit");
		quit.addActionListener(listener);
		userButtons.add(reset);
		userButtons.add(nextmove);
		userButtons.add(help);
		userButtons.add(quit);
			
		//Status bar		
		status = new JLabel ("Moves: 0       Welcome! Make your first move");
		
		//Put it together
		userView.add(game, BorderLayout.CENTER);
		userView.add(userButtons, BorderLayout.SOUTH);
		userView.add(status, BorderLayout.NORTH);
		
		//Set Window Size and Visibility
		userView.setSize(600,450);
		userView.setLocation(100,100);
		userView.setVisible(true);
		
		//update board
		boardDisplay();
	}//end Constructor
	
	/**
	 * Resets buttons after a reset, de-selection, or no match
	 */
	private void  recolorButtons(){
		int count = 0;
		Boolean white = true;
		for(PieceButton button : buttons){
			if (Chess.BOARD_COLS%2 != 0){
				if (white){
					button.setBackground(Color.WHITE);
					button.setForeground(Color.BLACK);
					white= false;
				}//end if
				else {
					button.setBackground(Color.BLACK);
					button.setForeground(Color.WHITE);
					white = true;
				}//end else
			}//end if
			else {
				if (count%Chess.BOARD_COLS == 0){
					white = !(white.booleanValue());
				}//end if
				if (white){
					button.setBackground(Color.WHITE);
					button.setForeground(Color.BLACK);
					white= false;
				}//end if
				else {
					button.setBackground(Color.BLACK);
					button.setForeground(Color.WHITE);
					white = true;
				}//end else
				count++;
			}//end else
		}//end for
	}//end recolorButtons
	
	/**
	 * Updates board based on which button is pressed
	 * 
	 * @param t -- not used
	 * @param o The Button Pushed
	 */
	@Override
	public void update(Observable t, Object o) {
		if (o instanceof PieceButton) {
			if (pieceSelect == 0){
				pieceSelect++;
				firstButton = ((PieceButton) o).getPos();
				buttons.get(firstButton).setBackground(Color.GREEN);
				buttons.get(firstButton).setForeground(Color.BLUE);
				status.setText("Moves: " + move + " "+ 
						PieceName.getName(model.getCurrentState().get(firstButton)) + " Selected.");
			}//end if
			else if (pieceSelect == 1) {
				secondButton = ((PieceButton) o).getPos();
				if (secondButton != firstButton) {
					buttons.get(secondButton).setBackground(Color.GREEN);
					buttons.get(secondButton).setForeground(Color.BLUE);
					status.setText("Moves: " + move + " "+ 
							PieceName.getName(model.getCurrentState().get(secondButton)) + " Selected.");
					String first = model.getCurrentState().get(firstButton);
					String second = model.getCurrentState().get(secondButton);
					if (model.makeMove(firstButton, secondButton)){
						move++;
						status.setText("Moves: " + move + "  You took a " + 
								PieceName.getName(second) + " with a " + PieceName.getName(first));
						boardDisplay();
						firstButton = -1;
						secondButton = -1;
						pieceSelect = 0;
						recolorButtons();
					}//end if
					else {
						status.setText("Moves: " + move + "  Invalid Move. Press Help button or Try Again.");
						firstButton = -1;
						secondButton = -1;
						pieceSelect = 0;
						recolorButtons();
						boardDisplay();
					}//end else					
				}//end if
				else {
					status.setText("Moves: " + move + "  Select Piece to Move.");
					firstButton = -1;
					secondButton = -1;
					pieceSelect = 0;
					recolorButtons();
					recolorButtons();
				}
				recolorButtons();
			}//end else if           
		}//end if
		else if (o instanceof JButton) {
			String buttonText = ((JButton) o).getText();
	    	if (buttonText.compareTo("Quit") == 0){
	    		System.exit(0);	    		
	    	}//end if
	    	else if (buttonText.compareTo("Reset") == 0) {
	    		model.reset();
	    		move = 0;
	    		status.setText("Moves: " + move + "  Welcome! Make your first move. ");
	    		pieceSelect = 0;
	    		firstButton = -1;
	    		secondButton = -1;
	    		recolorButtons();
	    		boardDisplay();
	    	}//end else if
	    	else if (buttonText.compareTo("Help") == 0){
	    		JFrame help = new JFrame("Help");
	    		//JTextArea content = new JTextArea(100, 100);
	    		JOptionPane content = new JOptionPane();
	    		content.setMessage("Welcome to Chess Solitaire! \n \n To begin:" +
	    				" \n \n " +	"Select your piece and the place you want " +
	    				"to move it by" +" clicking the buttons. \n"+
	    				"You must remove a piece every move. \n The game is " +
	    				"over when there is only one piece " +
	    				"remaining. \n ENJOY! \n");
	    		help.add(content);
	    		help.setSize(500,200);
	    		help.setLocation(700,100);
	    		help.setVisible(true);
	    	}//end else if
	    	else if (buttonText.compareTo("Next Move") == 0){
	    		if (model.getNextMove()){
		    		move++;
		    		status.setText("Moves: " + move + "  Next Move Displayed");
		    		boardDisplay();
	    		}//end if
	    		else {
	    			status.setText("Moves: " + move + "  No More Valid Moves. " +
	    					"Game Over. Press Reset to Start Over, or Quit to exit.");
	    			boardDisplay();
	    		}//end else
	    	}//end else
		}//end else if
	}//end update
	
	/**
	 * Updates the board to reflect current state of model
	 */
	private void boardDisplay() {
		ArrayList<String> current = model.getCurrentState();
		int i = 0;
		for (PieceButton button : buttons){
			if (current.get(i).equals(".")){
				button.setText("");
			}//end if
			else {
				button.setText(current.get(i));
			}//end else
			i++;
		}//end for
		if (model.getGoal(model.currentState)){
			status.setText ("Moves: " + move + "  You Won!");
		}//end if
	}//end boardDisplay
	
	/**
	 * Error checks game file
	 * Creates new Chess Model
	 * Starts GUI
	 * 
	 * @param args Chess game inputFile
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usuage: ChessGUI inputfile");
		}//end if
		String file = args[0];
		BufferedReader reader = null;
		try {
			FileReader gameFile = new FileReader (file);
			reader = new BufferedReader(gameFile);
		}catch (FileNotFoundException e) {
			System.err.println("FILE " + file +" DOES NOT EXIST");
			e.printStackTrace();
			System.exit(0);
		}//end catch
		
		ArrayList<ArrayList<String>> firststart = new ArrayList<ArrayList<String>>();
		String line;
		int row = 0;
		int col = 0;
		int h = 0;
		try {
			while ((line = reader.readLine()) != null) {
				String[] array = line.split(" ");
				if (h != 0){
					ArrayList<String> current = new ArrayList<String>();
					for (String str : array) {
						current.add(str);
					}//end for					
					firststart.add(current);
				}//end if
				else {
					row = Integer.parseInt(array[0]);
					col = Integer.parseInt(array[1]);
					h++;
				}//end else
			}//end while
		} catch (IOException e) {
			System.err.println("Not able to read file correctly.");
			e.printStackTrace();
			System.exit(0);
		}//end catch
		Chess chess = new Chess(firststart, row, col);
		ArrayList<ArrayList<String>> current = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < Chess.BOARD_ROWS; i++){
			ArrayList<String> rows = new ArrayList<String>();
			for (int j = 0; j < Chess.BOARD_COLS; j ++){
				String piece = new String(firststart.get(i).get(j));
				rows.add(piece);
			}//end for
			current.add(rows);
		}//end for
		chess.setCurrent(current);
		new ChessGUI(chess);
	}//end main
}//end ChessGUI
