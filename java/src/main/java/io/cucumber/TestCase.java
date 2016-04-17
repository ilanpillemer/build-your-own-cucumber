package io.cucumber;

import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;

import java.util.List;


public class TestCase {
    private List<StepDefinition> stepDefinitions;
    private List<Pickle> pickles;

    public TestCase(List<Pickle> pickles, List<StepDefinition> stepDefinitions) {
	this.pickles = pickles;
	this.stepDefinitions = stepDefinitions;
    }

    int i = 0;
    boolean hasPassed() {
	for (Pickle p : pickles) {
	    for (PickleStep ps : p.getSteps()) {
		for (StepDefinition s : stepDefinitions) {
		    if (s.GetStep(ps)==null) {
			return false;
		    } else {
			// la dee dah
		    }
		}
	    }
	    
	}
	return true;
    }

}
