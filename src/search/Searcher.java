package search;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstraction over the idea of a search.
 *
 * @author liberato
 *
 * @param <T>
 */
public abstract class Searcher<T> {
	protected final SearchProblem<T> searchProblem;
	protected final List<T> visitedStates;
	protected List<T> solution;

	/**
	 * Instantiates a searcher.
	 * 
	 * @param searchProblem
	 *            the search problem for which this searcher will find and
	 *            validate solutions
	 */
	public Searcher(SearchProblem<T> searchProblem) {
		this.searchProblem = searchProblem;
		this.visitedStates = new ArrayList<T>();
	}

	/**
	 * Finds and return a solution to the problem, consisting of a list of
	 * states.
	 * 
	 * The list should start with the initial state of the underlying problem.
	 * Then, it should have one or more additional states. Each state should be
	 * a successor of its predecessor. The last state should be a goal state of
	 * the underlying problem.
	 * 
	 * If there is no solution, then this method should return an empty list.
	 * 
	 * @return a solution to the problem (or an empty list)
	 */
	public abstract List<T> solve();

	/**
	 * Checks that a solution is valid.
	 * 
	 * A valid solution consists of a list of states. The list should start with
	 * the initial state of the underlying problem. Then, it should have one or
	 * more additional states. Each state should be a successor of its
	 * predecessor. The last state should be a goal state of the underlying
	 * problem.
	 * 
	 * @param solution
	 * @return true iff this solution is a valid solution
	 * @throws NullPointerException
	 *             if solution is null
	 */
	public final boolean isValid(List<T> solution) {
		if (solution == null) {
			throw new NullPointerException();
		}
		if (solution.isEmpty()) {
			return false;
		}
		boolean isValidFlag = true;
		// check if the first state matches the initial state
		//if (!(this.solution.get(0).equals(this.searchProblem.getInitialState()))) {
			//return false;
		//} //-> apparently we dont need to check for this?
		int i = 0;
		//if (this.searchProblem.isGoalState(solution.get(solution.size() - 1))) {
			//return false;
		//}
		T curr = solution.get(i + 1);
		List<T> currSuccessors = this.searchProblem.getSuccessors(solution.get(i));
		while ((i < solution.size() - 2) && (isValidFlag)) {
			if (currSuccessors.contains(curr)) {
				i = i + 1;
				curr = solution.get(i + 1);
				currSuccessors = this.searchProblem.getSuccessors(solution.get(i));
			} else {
				isValidFlag = false;
			}
		}
        return isValidFlag;
	}
}
