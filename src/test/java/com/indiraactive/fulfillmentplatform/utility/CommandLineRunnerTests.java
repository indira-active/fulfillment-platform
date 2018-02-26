package com.indiraactive.fulfillmentplatform.utility;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandLineRunnerTests {

    @Autowired
    private CommandLineRunner commandLineRunner;

    @Test
    public void testRunScriptReturnsSuccess() throws Exception {
        CommandLineRunner.CompletionErrorType expected = CommandLineRunner.CompletionErrorType.SUCCESS;
        String pathToScript = new ClassPathResource("test.py").getFile().getAbsolutePath();
        String args = "0";

        CommandLineRunner.CompletionErrorType actual = commandLineRunner.executeCommand("python " + " " + pathToScript + " " + args);

        assertEquals(expected, actual);
    }

    @Test
    public void testRunScriptReturnsFailure() throws Exception {
        CommandLineRunner.CompletionErrorType expected = CommandLineRunner.CompletionErrorType.FAILURE;
        String pathToScript = new ClassPathResource("test.py").getFile().getAbsolutePath();
        String args = "1";

        CommandLineRunner.CompletionErrorType actual = commandLineRunner.executeCommand("python " + " " + pathToScript + " " + args);

        assertEquals(expected, actual);
    }


}
