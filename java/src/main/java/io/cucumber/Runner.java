package io.cucumber;

import java.util.List;

import gherkin.pickles.Pickle;

public class Runner {

    private final Glue glue;
    
    public Runner(Glue glue) {
	this.glue = glue;
    }

    public Report execute(List<Pickle> pickles) {
	Report r = Report.newInstance();
	pickles.forEach(pickle -> r.addTestCase(glue.createTestCase(pickle)));
        return r;
    }
}
