package com.indiraactive.fulfillmentplatform.utility;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static com.indiraactive.fulfillmentplatform.utility.CommandLineRunner.ScriptCompletionCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandLineRunnerTests {

    @Autowired
    private CommandLineRunner commandLineRunner;

    @MockBean
    private RuntimeWrapper runtimeWrapper;

    public void configureMockProcess(int errorCodeProcessWillReturn) throws Exception {
        Process process = Mockito.mock(Process.class);
        given(process.waitFor()).willReturn(errorCodeProcessWillReturn);
        given(process.getInputStream()).willReturn(null);
        given(this.runtimeWrapper.executeCommand(anyString())).willReturn(process);
    }


    @Test
    public void testRunScriptReturnsSuccess() throws Exception {
        CommandLineRunner.ScriptCompletionCode expected = CommandLineRunner.ScriptCompletionCode.SUCCESS;
        String pathToScript = new ClassPathResource("test.py").getFile().getAbsolutePath();
        String args = "0";
        configureMockProcess(0);

        CommandLineRunner.ScriptCompletionCode actual = commandLineRunner.executeCommand("python " + " " + pathToScript + " " + args);

        assertEquals(expected, actual);
    }

    @Test
    public void testRunScriptReturnsFailure() throws Exception {
        CommandLineRunner.ScriptCompletionCode expected = CommandLineRunner.ScriptCompletionCode.FAILURE;
        String pathToScript = new ClassPathResource("test.py").getFile().getAbsolutePath();
        String args = "1";
        configureMockProcess(1);

        CommandLineRunner.ScriptCompletionCode actual = commandLineRunner.executeCommand("python " + " " + pathToScript + " " + args);

        assertEquals(expected, actual);
    }

    @Test
    public void testCheckCompletionCode() throws Exception{
        ScriptCompletionCode expectedCompletionCode = ScriptCompletionCode.SUCCESS;

        ScriptCompletionCode actualError = commandLineRunner.checkCompletionCode(0, "anyString");

        assertEquals(actualError, expectedCompletionCode);
    }


}
