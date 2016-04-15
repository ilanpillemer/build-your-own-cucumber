package io.cucumber;

import java.util.List;
import java.util.ArrayList;

public class Report {
    public List<TestCase> testCases = new ArrayList<TestCase>();
    public List<TestCase> testCasesPassed = new ArrayList<TestCase>();

    public Report(TestCase t) {
	testCases.add(t);
	testCasesPassed.add(t);
    }

}
