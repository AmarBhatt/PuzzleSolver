/**
 * 
 * Clock.java
 * 
 * $Id: Clock.java,v 1.7 2013/04/16 19:14:18 aab2210 Exp $
 * 
 * 
 * $Log: Clock.java,v $
 * Revision 1.7  2013/04/16 19:14:18  aab2210
 * "No Solution." --> "No solution."
 *
 * Revision 1.6  2013/04/16 19:10:34  aab2210
 * "No Solution" --> "No Solution."
 *
 * Revision 1.5  2013/04/16 18:58:11  aab2210
 * Changed Spacing of Solver <E>
 *
 * Revision 1.4  2013/04/15 03:46:49  aab2210
 * Partner Project: Added Derek Brown, djb3718, to files.
 *
 * Revision:  Implement Water Puzzle, Solver memoization, Use of Generics
 *
 * Code Functionality: Success! Both Puzzles can be solved using Solver.
 *
 * Revision 1.3  2013/03/25 06:19:13  aab2210
 * Change: Error Spelling
 *
 * Revision 1.2  2013/03/25 06:05:28  aab2210
 * Changes:  Error Output
 *
 * Revision 1.1  2013/03/25 05:43:37  aab2210
 * Initial Revision
 *
 * Code Functionality: Success. Takes Parameters, Outputs Results.
 *
 * 
 * 
 */
import java.util.ArrayList;
/**
 * 
 * Solves Clock Puzzle using Puzzle Interface
 * 
 * @author Amar Bhatt aab2210
 * @author Derek Brown djb3718
 *
 */
public class Clock implements Puzzle <Integer> {
	/**
	 * Number of Hours on Clock
	 */
	private static int hours;
	/**
	 * Staring Hour
	 */
	private static int start;
	/**
	 * Ending Hour
	 */
	private static int goal;
	

	/* (non-Javadoc)
	 * @see Puzzle#getStart()
	 */
	@Override
	public Integer getStart() {
		return start;
	}//end getStart

	/* (non-Javadoc)
	 * @see Puzzle#getGoal()
	 */
	@Override
	public boolean getGoal(Integer config) {
		if (config.compareTo(goal) == 0){
			return true;
		}//end if
		return false;
	}//end getGoal

	/* (non-Javadoc)
	 * @see Puzzle#getNeighbors(int)
	 */
	@Override
	public ArrayList<Integer> getNeighbors(Integer config) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>(2);
		if (config == 1){
			neighbors.add(hours);
		}//end if
		else {
			neighbors.add(config-1);
		}//end else
		if (config == hours){
			neighbors.add(1);
		}//end if
		else {
			neighbors.add(config+1);
		}//end else
		
		return neighbors;
	}//end getNeighbors
	
	public Clock (int h,int s,int g){
		hours = h;
		start = s;
		goal = g;
	}//end Clock

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 3){
			System.out.println("Usage: java Clock hours start goal");
			System.exit(0);
		}//end if
		int h = Integer.parseInt(args[0]);
		int s = Integer.parseInt(args[1]);
		int g = Integer.parseInt(args[2]);
		Solver <Integer> x = new Solver <Integer>();
		Puzzle<Integer> clock1 = new Clock (h,s,g);
		ArrayList<Integer> solution = x.solvePuzzle(clock1);
		if (solution == null){
			System.out.println("No solution.");
		}//end if
		else { 
			int i = 0;
			while (!(solution.isEmpty())){
				System.out.println("Step "+i+": " + solution.get(0));
				solution.remove(0);
				i++;
			}//end while
		}//end else
		

	}//end main

}//end Clock
