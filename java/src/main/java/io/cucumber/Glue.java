package io.cucumber;

import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;


public class Glue {
    List<StepDefinition> stepDefinitions;
    
    public Glue(List<StepDefinition> stepDefinitions) {
	this.stepDefinitions = stepDefinitions;
    }

    TestCase createTestCase(Pickle pickle) {
	return new TestCase(pickle, glueSteps(pickle));
    }

    private Map<PickleStep,List<StepDefinition>> glueSteps(Pickle p) {
	return p.getSteps().stream().collect(Collectors.toMap(Function.identity(), this::getSteps));
    }

    private List<StepDefinition> getSteps(PickleStep ps) {
	return stepDefinitions.stream().filter(s -> s.matches(ps)).collect(toList());
    }
}
