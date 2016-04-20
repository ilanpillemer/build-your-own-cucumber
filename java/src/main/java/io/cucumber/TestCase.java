package io.cucumber;

import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;

import io.cucumber.StepDefinition.NoArgBody;

import java.util.List;


public class TestCase {
    private List<StepDefinition> stepDefinitions;
    private Pickle pickle;

    public TestCase(Pickle pickle, List<StepDefinition> stepDefinitions) {
	this.pickle = pickle;
	this.stepDefinitions = stepDefinitions;
    }

    int matching_count = 0;
    boolean hasPassed() {

	// each step should map to one and only one lamda
	// if there is more than one lamda there are ambiguous step definitions
	// if there are no lamdas the step definition is undefined
	for (PickleStep ps : pickle.getSteps()) {
	    for (StepDefinition s : stepDefinitions) {
		if (s.matches(ps)) {
		    try {
			s.getLamda().call();
			matching_count++;
		    } catch (RuntimeException e) {
			return false;
		    }
		}
	    }
	}
	return (matching_count==1);
	    
    }
}
