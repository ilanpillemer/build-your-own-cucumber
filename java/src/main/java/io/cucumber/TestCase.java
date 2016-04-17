package io.cucumber;

import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;

import java.util.List;


public class TestCase {
    private List<StepDefinition> stepDefinitions;
    private Pickle pickle;

    public TestCase(Pickle pickle, List<StepDefinition> stepDefinitions) {
	this.pickle = pickle;
	this.stepDefinitions = stepDefinitions;
    }

    int i = 0;
    boolean hasPassed() {
	for (PickleStep ps : pickle.getSteps()) {
	    for (StepDefinition s : stepDefinitions) {
		if (s.GetStep(ps)==null) {
		    return false;
		} else {
		    // la dee dah
		}

	    }
	    
	}
	return true;
    }

}
