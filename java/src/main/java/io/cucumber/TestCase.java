package io.cucumber;

import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class TestCase {
    private final Map<PickleStep,List<StepDefinition>> glueySteps;
    private final Pickle pickle;

    private static Predicate<PickleStep> not(Predicate<PickleStep> p) {
	return p.negate();
    }

    public TestCase(Pickle pickle, Map<PickleStep, List<StepDefinition>> glueySteps) {
	this.pickle = pickle;
	this.glueySteps = glueySteps;
    }

    public boolean hasPassed() {
	if (pickle.getSteps().stream().anyMatch(isAmbiguous())) return false;
	if (pickle.getSteps().stream().anyMatch(isUndefined())) return false;
	try {
	    pickle.getSteps().stream().
		filter(not(isAmbiguous())).
		filter(not(isUndefined())).
		forEach( p -> glueySteps.get(p).get(0).getLamda().call() );
	} catch (RuntimeException e) {
	    return false;
	}
	return true;
    }

    private Predicate<PickleStep> isAmbiguous() {
	return p -> glueySteps.get(p).size() > 1 ;
    }

    private Predicate<PickleStep> isUndefined() {
	return p -> glueySteps.get(p).size() == 0 ;
    }
}
