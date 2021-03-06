package io.cucumber;

import java.util.List;
import java.util.ArrayList;

public class Report {
    public List<TestCase> testCases = new ArrayList<TestCase>();
    public List<TestCase> testCasesPassed = new ArrayList<TestCase>();

    
    private Report() {

    }

    public static Report newInstance() {
	return new Report();
    }
    
    
    public void addTestCase(TestCase t) {
	testCases.add(t);
	if (t.hasPassed()) {
	    testCasesPassed.add(t);
	}
    }

}
