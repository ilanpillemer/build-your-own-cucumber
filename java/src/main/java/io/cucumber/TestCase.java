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
	for (PickleStep ps : pickle.getSteps()) {
	    for (StepDefinition s : stepDefinitions) {
		NoArgBody lamda = s.GetStep(ps);
		if (lamda!=null) {  
		    try {
			lamda.call();
			matching_count++;
		    } catch (RuntimeException e) {
			return false;
		    }
		} else {
		    // la dee dah
		}
	    }
	}
	return (matching_count==1);
    }

}
