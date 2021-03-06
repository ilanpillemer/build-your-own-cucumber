package io.cucumber;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;


public class RunnerTest {

    List<StepDefinition> stepDefinitions = new ArrayList<StepDefinition>();
    
    @Before
    public void magic_step_definitions() {
        stepDefinitions.add( new StepDefinition(Pattern.compile("^.*pass.*$")  , () -> {}));
        stepDefinitions.add( new StepDefinition(Pattern.compile("^.*fail.*$")  , () -> { throw new RuntimeException(); }));
    }


    
    @Test
    public void cukes_sees_1_of_1_passing_test_cases() {
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        Compiler compiler = new Compiler();

        String feature = String.join("\n",
				     "Feature:",
				     "  Scenario:",
				     "    Given a passing step"
				     );
        GherkinDocument gherkinDocument = parser.parse(feature);
        List<Pickle> pickles = compiler.compile(gherkinDocument, "path/to/the.feature");

  
        Glue glue = new Glue(stepDefinitions);
        Runner runner = new Runner(glue);

        Report report = runner.execute(pickles);

        assertEquals(1, report.testCases.size());
        assertEquals(1, report.testCasesPassed.size());
    }

     @Test
    public void cukes_sees_5_of_5_passing_test_cases() {
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        Compiler compiler = new Compiler();

        String feature = String.join("\n",
				     "Feature:",
				     "  Scenario:",
				     "    Given a passing step",
				     "",
				     "  Scenario:",
				     "    Given a passing step",
				     "",
				     "  Scenario:",
				     "    Given a passing step",
				     "",
				     "  Scenario:",
				     "    Given a passing step",
				     "",
				     "  Scenario:",
				     "    Given a passing step"				     
				     );
        GherkinDocument gherkinDocument = parser.parse(feature);
        List<Pickle> pickles = compiler.compile(gherkinDocument, "path/to/the.feature");

  
        Glue glue = new Glue(stepDefinitions);
        Runner runner = new Runner(glue);

        Report report = runner.execute(pickles);

        assertEquals(5, report.testCases.size());
        assertEquals(5, report.testCasesPassed.size());
    }

      @Test
    public void cukes_sees_2_of_5_passing_test_cases() {
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        Compiler compiler = new Compiler();

        String feature = String.join("\n",
				     "Feature:",
				     "  Scenario:",
				     "    Given a passing step",
				     "",
				     "  Scenario:",
				     "    Given a failing step",
				     "",
				     "  Scenario:",
				     "    Given a undefined step",
				     "",
				     "  Scenario:",
				     "    Given a failing step",
				     "",
				     "  Scenario:",
				     "    Given a passing step"				     
				     );
        GherkinDocument gherkinDocument = parser.parse(feature);
        List<Pickle> pickles = compiler.compile(gherkinDocument, "path/to/the.feature");

  
        Glue glue = new Glue(stepDefinitions);
        Runner runner = new Runner(glue);

        Report report = runner.execute(pickles);

        assertEquals(5, report.testCases.size());
        assertEquals(2, report.testCasesPassed.size());
    }

    @Test
    public void cukes_sees_0_of_1_udefined_tests_cases() {
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        Compiler compiler = new Compiler();

        String feature = String.join("\n",
				     "Feature:",
				     "  Scenario:",
				     "    Given an undefined step"
				     );
        GherkinDocument gherkinDocument = parser.parse(feature);

        List<Pickle> pickles = compiler.compile(gherkinDocument, "path/to/the.feature");

        Glue glue = new Glue(stepDefinitions);
        Runner runner = new Runner(glue);

        Report report = runner.execute(pickles);

	assertEquals(1, report.testCases.size());
        assertEquals(0, report.testCasesPassed.size());
    }

    @Test
    public void cukes_sees_0_of_1_no_failing_tests_cases() {
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        Compiler compiler = new Compiler();

        String feature = String.join("\n",
				     "Feature:",
				     "  Scenario:",
				     "    Given a failing step"
				     );
        GherkinDocument gherkinDocument = parser.parse(feature);

        List<Pickle> pickles = compiler.compile(gherkinDocument, "path/to/the.feature");

        Glue glue = new Glue(stepDefinitions);
        Runner runner = new Runner(glue);

        Report report = runner.execute(pickles);

	assertEquals(1, report.testCases.size());
        assertEquals(0, report.testCasesPassed.size());
    }

     @Test
    public void cukes_sees_0_of_1_ambiguous_test_cases() {
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        Compiler compiler = new Compiler();

        String feature = String.join("\n",
				     "Feature:",
				     "  Scenario:",
				     "    Given a passing step"
				     );
        GherkinDocument gherkinDocument = parser.parse(feature);
        List<Pickle> pickles = compiler.compile(gherkinDocument, "path/to/the.feature");

	// add an second step definition that makes it ambiguous
	stepDefinitions.add( new StepDefinition(Pattern.compile("^.*pass.*$")  , () -> {}));
	
        Glue glue = new Glue(stepDefinitions);
        Runner runner = new Runner(glue);

        Report report = runner.execute(pickles);

        assertEquals(1, report.testCases.size());
        assertEquals(0, report.testCasesPassed.size());
    }

    

}
