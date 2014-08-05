/**
 * Solver.java
 * 
 * $Id: Solver.java,v 1.5 2013/05/05 00:29:25 aab2210 Exp $
 * 
 * 
 * $Log: Solver.java,v $
 * Revision 1.5  2013/05/05 00:29:25  aab2210
 * Solver works with all 3 puzzles.  Chess GUI fully functional.
 *
 * Code Functionality: 100%
 *
 * Revision 1.4  2013/04/16 18:58:11  aab2210
 * Changed Spacing of Solver <E>
 *
 * Revision 1.3  2013/04/15 03:46:48  aab2210
 * Partner Project: Added Derek Brown, djb3718, to files.
 *
 * Revision:  Implement Water Puzzle, Solver memoization, Use of Generics
 *
 * Code Functionality: Success! Both Puzzles can be solved using Solver.
 *
 * Revision 1.2  2013/03/25 06:21:32  aab2210
 * Change: Error Supression
 *
 * Revision 1.1  2013/03/25 05:45:02  aab2210
 * Initial Revision
 *
 * Code Functionality:  Success.  Solves Clock Puzzle using naive BFS.
 *
 * 
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Solves Puzzle
 * 
 * @author Amar Bhatt aab2210
 * @author Derek Brown djb3718
 * 
 * @param <E> Type of Configurations
 *
 */
public class Solver <E> {

	/**
	 * solvePuzzle() Solves Given Puzzle Using BFS Algorithm
	 * 
	 * @param puzzle Puzzle Object
	 * @return Solution ArrayList of Integers
	 */
	
	public ArrayList<E> solvePuzzle (Puzzle <E> puzzle){
		HashMap<E, E> predecessors = new HashMap<E, E>();

		ArrayList <E> queue = new ArrayList<E>();
		E current;
		current = puzzle.getStart();
		queue.add(current);
		boolean found = false;
		if (puzzle.getGoal(queue.get(0))){
			found = true;
			queue.remove(0);
		}//end if
		predecessors.put(current, current);
		while (!(queue.isEmpty()) && (!(found))){
			current = queue.get(0);
			queue.remove(0);
			for (E s: puzzle.getNeighbors(current)) {
				E c = current;
				
				if (puzzle.getGoal(s)){
					predecessors.put(s, c);
					current = s;
					found = true;
					break;
				}//end if
				else {
					if (!(predecessors.containsKey(s))){
						predecessors.put(s, c);
						queue.add(s);
					}//end if
				}//end else				
			}//end for				
		}//end while
		if (found){
			ArrayList<E> solution = new ArrayList<E>();
			E p = current;
			solution.add(p);
			while (!(p.equals(puzzle.getStart()))){
				solution.add(0,predecessors.get(p));
				p = predecessors.get(p);
			}//end while		
			return solution;
		}//end if
		else {
			return null;
		}//end else
	}//end solvePuzzle
}//end Solver
