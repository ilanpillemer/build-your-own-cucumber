package io.cucumber;

import gherkin.pickles.Pickle;
import java.util.List;

public class Glue {

    List<StepDefinition> stepDefinitions;
    
    public Glue(List<StepDefinition> stepDefinitions) {
	this.stepDefinitions = stepDefinitions;
    }

    TestCase createTestCase(Pickle pickle) {
	return new TestCase(pickle,stepDefinitions);
    }
}
