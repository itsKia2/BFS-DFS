package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> solve() {
		Stack<T> nextStates = new Stack<T>();
		this.solution = new ArrayList<T>();
		List<T> states = new ArrayList<>();
		List<T> predecessorStates = new ArrayList<>();
		T currentState = this.searchProblem.getInitialState();

		//states.add(currentState);
		//predecessorStates.add(currentState);
		//this.visitedStates.add(currentState);
		nextStates.push(currentState);
		while (nextStates.isEmpty() == false) {
			currentState = nextStates.pop();

			if (this.searchProblem.isGoalState(currentState)) {
				break;
			}

			if (this.visitedStates.contains(currentState) == false) {
				this.visitedStates.add(currentState);
			}
			T currSucc;
			for (int i = 0; i < this.searchProblem.getSuccessors(currentState).size(); i++) {
				currSucc = this.searchProblem.getSuccessors(currentState).get(i);
				nextStates.push(currSucc);
				if (states.contains(currSucc) == false) {
					states.add(currSucc);
					predecessorStates.add(currentState);
				} 
			}
		}

		this.solution.add(currentState);
		while (currentState.equals(this.searchProblem.getInitialState()) == false) {
			currentState = predecessorStates.get(states.indexOf(currentState));
			this.solution.add(currentState);
		}
		Collections.reverse(this.solution);

		return this.solution;
	}
}
