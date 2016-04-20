package io.cucumber;

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

    public NoArgBody getLamda() {
	return lamda;
    }

    public boolean matches(PickleStep pickleStep) {
	return regex.matcher(pickleStep.getText()).matches();
    }

}
