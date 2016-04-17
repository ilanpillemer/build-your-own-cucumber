package io.cucumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gherkin.pickles.PickleStep;

public class StepDefinition {

    Pattern regex;
    NoArgBody lamda;
    
    public StepDefinition(Pattern regex, NoArgBody lamda) {
	this.regex=regex;
	this.lamda=lamda;
    }

    @FunctionalInterface
    interface NoArgBody {
        void call();
    }

    public NoArgBody GetStep(PickleStep pickleStep) {
	Matcher m = regex.matcher(pickleStep.getText());
	if (m.matches()) {
	    return lamda;
	}
	return null;
	
    }
}
