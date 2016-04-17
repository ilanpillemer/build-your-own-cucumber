package io.cucumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gherkin.pickles.PickleStep;

public class StepDefinition {

    Pattern regex;
    NoArgBody p1;
    
    public StepDefinition(Pattern regex, NoArgBody p1) {
	this.regex=regex;
	this.p1=p1;
    }

    @FunctionalInterface
    interface NoArgBody {
        void call();
    }

    public NoArgBody GetStep(PickleStep pickleStep) {
	Matcher m = regex.matcher(pickleStep.getText());
	if (m.matches()) {
	    return p1;
	}
	return null;
	
    }
}
