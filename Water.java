

/**
 * Water.java
 * 
 * $Id: Water.java,v 1.4 2013/04/16 19:14:18 aab2210 Exp $
 * 
 * 
 * $Log: Water.java,v $
 * Revision 1.4  2013/04/16 19:14:18  aab2210
 * "No Solution." --> "No solution."
 *
 * Revision 1.3  2013/04/16 19:10:34  aab2210
 * "No Solution" --> "No Solution."
 *
 * Revision 1.2  2013/04/16 18:58:11  aab2210
 * Changed Spacing of Solver <E>
 *
 * Revision 1.1  2013/04/15 03:46:49  aab2210
 * Partner Project: Added Derek Brown, djb3718, to files.
 *
 * Revision:  Implement Water Puzzle, Solver memoization, Use of Generics
 *
 * Code Functionality: Success! Both Puzzles can be solved using Solver.
 *
 * 
 * 
 */

import java.util.ArrayList;

/**
 * Water Puzzle from DIE HARD 3!
 * 
 * @author Amar Bhatt aab2210
 * @author Derek Brown djb3718
 *
 */
public class Water implements Puzzle<ArrayList<Integer>> {

	/**
	 * desired level of water
	 */
	private int capacity;
	/**
	 * number of jugs and their maximum capacity
	 */
	private ArrayList<Integer> jugs;
	/* (non-Javadoc)
	 * @see Puzzle#getStart()
	 */
	@Override
	public ArrayList<Integer> getStart() {
		ArrayList<Integer> start = new ArrayList<Integer>();
		for (int i = 0; i < jugs.size(); i ++){ start.add(0);}
		return start;
	}//end getStart

	/* (non-Javadoc)
	 * @see Puzzle#getGoal()
	 */
	@Override
	public boolean getGoal(ArrayList<Integer> config) {
		for (Integer i : config){
			if (i.compareTo(capacity) == 0){
				return true;
			}//end if
		}//end for
		return false;
	}//end getGoal
	/**
	 * Generates List of Configurations of Each Jug Pouring into Other Jugs
	 * 
	 * @param config initial configuration
	 * @return List of configurations
	 */
	public ArrayList<ArrayList<Integer>> pour (ArrayList<Integer> config){
		ArrayList<ArrayList<Integer>> pourConfigs = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < jugs.size(); i++){
			for (int j = 0; j < jugs.size(); j++){
				ArrayList<Integer> currentConfig = new ArrayList<Integer>();
				currentConfig.addAll(config);
				if (i != j){
					int maxCapacity = jugs.get(j);
					int currentCapacity = maxCapacity - currentConfig.get(j) ;
					if (currentConfig.get(i) <= currentCapacity){
						currentConfig.set(j, currentConfig.get(j) + currentConfig.get(i) );
						currentConfig.set(i, 0);
					}//end if
					else {
						currentConfig.set(j, maxCapacity);
						currentConfig.set(i, currentConfig.get(i) - currentCapacity);
					}//end else
					pourConfigs.add(currentConfig);
					
				}//end if
			}//end for j
		}//end for i
		return pourConfigs;
	}//end pour
		
	/**
	 * Fills Each Jug to Maximum Capacity
	 * 
	 * @param config initial configuration
	 * @return List of configurations
	 */
	public ArrayList<ArrayList<Integer>> fill (ArrayList<Integer> config){
		ArrayList<ArrayList<Integer>> fillConfigs = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < config.size(); i++){
			ArrayList<Integer> currentConfig = new ArrayList<Integer>();
			currentConfig.addAll(config);	
			currentConfig.set(i, jugs.get(i));			
			fillConfigs.add(currentConfig);
		}//end for
		return fillConfigs;
	}//end fill
	/**
	 * Dumps every jug to 0
	 * 
	 * @param config initial configuration
	 * @return List of configurations
	 */
	public ArrayList<ArrayList<Integer>> dump (ArrayList<Integer> config){
		ArrayList<ArrayList<Integer>> dumpConfigs = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < config.size(); i++){
			ArrayList<Integer> currentConfig = new ArrayList<Integer>();
			currentConfig.addAll(config);			
			currentConfig.set(i, 0);
			dumpConfigs.add(currentConfig);
		}//end for
		return dumpConfigs;
	}//end dump

	/* (non-Javadoc)
	 * @see Puzzle#getNeighbors(int)
	 */
	@Override
	public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> config) {
		ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
		neighbors.addAll(pour(config));
		neighbors.addAll(fill(config));
		neighbors.addAll(dump(config));
		return neighbors;
	}//end getNeighbors
	/**
	 * Constructor
	 * 
	 * @param capacity desired water level
	 * @param jugs number and maximum capacity of jugs
	 */
	public Water(int capacity, ArrayList<Integer> jugs){
		this.capacity = capacity;
		this.jugs = jugs;
	}//end Water

	/**
	 * Runs Water Puzzle through Solver, and Displays Results
	 * 
	 * @param args capacity, jug 1, jug 2, ...
	 */
	public static void main(String[] args) {
		if (args.length < 2){
			System.out.println("Usage: java Water amount jug1 jug2 ... ");
			System.exit(0);
		}//end if
		Integer c = Integer.parseInt(args[0]);
		
		ArrayList<Integer> j = new ArrayList<Integer>();
		for(int i = 1; i < args.length; i ++ ){
			j.add(Integer.parseInt(args[i]));
		}//end for
		
		Puzzle<ArrayList<Integer>> water1 = new Water(c , j);
		Solver <ArrayList<Integer>> x = new Solver <ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> solution = x.solvePuzzle(water1);
		if (solution == null){
			System.out.println("No solution.");
		}//end if
		else { 
			int i = 0;
			while (!(solution.isEmpty())){
				System.out.print("Step "+i+":");
				for (int k = 0; k < solution.get(0).size(); k++){
					System.out.print(" " +solution.get(0).get(k));
				}//end for
				System.out.println("");
				solution.remove(0);
				i++;
			}//end while
		}//end else		
	}//end main
}//end Water
