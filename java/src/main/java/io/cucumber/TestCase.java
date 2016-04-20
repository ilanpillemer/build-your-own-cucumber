package io.cucumber;

import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;

import java.util.List;
import java.util.Map;


public class TestCase {
    private Map<PickleStep,List<StepDefinition>> glueySteps;
    private Pickle pickle;

    public TestCase(Pickle pickle, Map<PickleStep, List<StepDefinition>> glueySteps) {
	this.pickle = pickle;
	this.glueySteps = glueySteps;
    }

    int matching_count = 0;
    boolean hasPassed() {

	// each step should map to one and only one lamda
	// if there is more than one lamda there are ambiguous step definitions
	// if there are no lamdas the step definition is undefined

	for (PickleStep ps : pickle.getSteps()) {
	    if (glueySteps.get(ps).size() != 1) return false; 
	    try {
		glueySteps.get(ps).get(0).getLamda().call();
	    } catch (RuntimeException e) {
		return false;
	    }
	}
	return true;
	    
    }
}
