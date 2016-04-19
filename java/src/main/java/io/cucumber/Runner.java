package io.cucumber;

import java.util.List;

import gherkin.pickles.Pickle;

public class Runner {

    Glue glue;
    
    public Runner(Glue glue) {
	this.glue = glue;
    }

    public Report execute(List<Pickle> pickles) {
	Report r = new Report();
	pickles
	    .forEach(pickle -> r.addTestCase(glue.createTestCase(pickle)));
        return r;
    }
}
