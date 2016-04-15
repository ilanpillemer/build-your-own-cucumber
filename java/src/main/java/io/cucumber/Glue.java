package io.cucumber;

import gherkin.pickles.Pickle;
import java.util.List;

public class Glue {
    public Glue(List<StepDefinition> stepDefinitions) {
    }

    TestCase createTestCase(List<Pickle> pickles) {
	return new TestCase(pickles);
    }
}
