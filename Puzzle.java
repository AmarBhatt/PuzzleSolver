/**
 * 
 * Puzzle.java
 * 
 * $Id: Puzzle.java,v 1.2 2013/04/15 03:46:48 aab2210 Exp $
 * 
 * $Log: Puzzle.java,v $
 * Revision 1.2  2013/04/15 03:46:48  aab2210
 * Partner Project: Added Derek Brown, djb3718, to files.
 *
 * Revision:  Implement Water Puzzle, Solver memoization, Use of Generics
 *
 * Code Functionality: Success! Both Puzzles can be solved using Solver.
 *
 * Revision 1.1  2013/03/25 05:44:24  aab2210
 * Initial Revision
 *
 * Code Functionality: Success.  Interface Methods for Various Classes.
 *
 * 
 * 
 */

/**
 * Interface for all methods of any puzzle
 * 
 * @author Amar Bhatt aab2210
 * @author Derek Brown djb 3718
 *
 *@param <E> Type of Configuration
 */
public interface Puzzle <E> {
	/**
	 * Get the starting config for this puzzle
	 * 
	 * @return starting config
	 */
	E getStart();
	/**
	 * Get goal config for this puzzle
	 * 
	 * @return goal config
	 */
	boolean getGoal(E config);
	/**
	 * For an incoming config, generate and return all direct
	 * neighbors to this config
	 * 
	 * @param config - the incoming config
	 * @return collection of neighbor configs
	 */
	java.util.ArrayList<E> getNeighbors(E config);

}//end Puzzle
