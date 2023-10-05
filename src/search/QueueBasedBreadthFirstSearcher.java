package search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> solve() {
		this.solution = new ArrayList<T>();
		Queue<T> nextStates = new ArrayDeque<>();
		List<T> states = new ArrayList<>();
		List<T> predecessorStates = new ArrayList<>();
		T currentState = this.searchProblem.getInitialState();
		T currentSuccessor;

		// init vars
		//this.visitedStates.add(currentState);
		//states.add(currentState);
		//predecessorStates.add(currentState);
		nextStates.add(currentState);

		while (!nextStates.isEmpty()) {
			// pop a state from nextStates and use it for this iteration
			currentState = nextStates.remove();

			// check if this currentState is the goal state
			if (this.searchProblem.isGoalState(currentState)) {
				break;
			} // break out of loop so we can construct the solution var

			for (int i = 0; i < this.searchProblem.getSuccessors(currentState).size(); i++) {
				currentSuccessor = this.searchProblem.getSuccessors(currentState).get(i);

				// check if it is in visited states
				if (this.visitedStates.contains(currentSuccessor) == false) {
					// add to nextStates so we can iterate through possible solutions
					this.visitedStates.add(currentSuccessor);
					nextStates.add(currentSuccessor);
				}
				// check if it is in states so we can add it to states and add predecessor
				// this is so we know which state is the predecessor of which state
				if (states.contains(currentSuccessor) == false) {
					states.add(currentSuccessor);
					predecessorStates.add(currentState);
				} 
			}
		}

		// we assume that at this point we have a current state which is at the goal state
		// we need to go in reverse and find out the path
		List<T> reversePath = new ArrayList<>();
		reversePath.add(currentState); // final state
		while (!currentState.equals(this.searchProblem.getInitialState())) {
			currentState = predecessorStates.get(states.indexOf(currentState));
			reversePath.add(currentState);
		}

		// so now we have a path that is in reverse (starting with goal, ending with initial)
		// so just reverse that
		//for (int i = reversePath.size(); i <= 0; i--) {
		//	solution.add(reversePath.get(i));
		//}

		Collections.reverse(reversePath);
		this.solution = reversePath;
		return this.solution;
	}
}
