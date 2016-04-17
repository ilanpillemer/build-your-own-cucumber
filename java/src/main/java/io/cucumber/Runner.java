package io.cucumber;

import java.util.List;

import gherkin.pickles.Pickle;

public class Runner {

    Glue glue;
    
    public Runner(Glue glue) {
	this.glue = glue;
    }

    public Report execute(List<Pickle> pickles) {
	TestCase t = glue.createTestCase(pickles);
        return new Report(t);
    }
}
