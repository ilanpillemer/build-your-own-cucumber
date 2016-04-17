package io.cucumber;

import gherkin.pickles.Pickle;
import java.util.List;

public class Glue {

    List<StepDefinition> stepDefinitions;
    
    public Glue(List<StepDefinition> stepDefinitions) {
	this.stepDefinitions = stepDefinitions;
    }

    TestCase createTestCase(List<Pickle> pickles) {
	return new TestCase(pickles,stepDefinitions);
    }
}
