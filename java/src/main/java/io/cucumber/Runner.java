package io.cucumber;

import gherkin.pickles.Pickle;

import java.util.List;

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
