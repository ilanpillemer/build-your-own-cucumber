package io.cucumber;

import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class TestCase {
    private final Map<PickleStep,List<StepDefinition>> glueySteps;
    private Pickle pickle;

    public TestCase(Pickle pickle, Map<PickleStep, List<StepDefinition>> glueySteps) {
	this.pickle = pickle;
	this.glueySteps = glueySteps;
    }

    // each step should map to one and only one lamda
    // if there is more than one lamda there are ambiguous step definitions
    // if there are no lamdas the step definition is undefined
    public boolean hasPassed() {

	if (pickle.getSteps().stream().anyMatch(p -> this.glueySteps.get(p).size() > 1)) return false;
	if (pickle.getSteps().stream().anyMatch(p -> this.glueySteps.get(p).size() == 0)) return false;
	try {
	pickle.getSteps().stream().
	    filter(p -> this.glueySteps.get(p).size() < 2).
	    filter(p -> this.glueySteps.get(p).size() > 0 ).
	    forEach( (PickleStep p) -> this.glueySteps.get(p).get(0).getLamda().call() );
	} catch (RuntimeException e) {
	    return false;
	}
	
	return true;
    }

    private Predicate<PickleStep> isAmbiguous() {
	return p -> this.glueySteps.get(p).size() > 1 ;
    }

    private Predicate<PickleStep> isUndefined() {
	return p -> glueySteps.get(p).size() == 0 ;
    }
    
}
