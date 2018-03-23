package com.indiraactive.fulfillmentplatform.utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Runs a command on the host system's shell. This class needs to be enhanced.
 */
@Component
public class CommandLineRunner {
    /**
     * Retrieves the runtime
     */
    @Autowired
    private RuntimeWrapper runtimeWrapper;

    /**
     * Enums used to define how the shell finished running a given command
     */
    public enum ScriptCompletionCode {
        SUCCESS,
        FAILURE
    }

    /**
     * Runs a given command in the shell and captures whether or not it fails.
     * @param commandTobeExecuted Command to run in system's shell
     * @return ScriptCompletionCode of success or failure
     */
    public ScriptCompletionCode executeCommand(String commandTobeExecuted) {
        System.out.println("CommandLineRunner: Spinning up a new process");
        Process process;
        int errorCode = -1;
        try {
            System.out.println("CommandLineRunner: About to execute the following command: " + commandTobeExecuted);
            process = runtimeWrapper.executeCommand(commandTobeExecuted);
            if (process != null) {
                errorCode = process.waitFor();
                printOutInputStream(process.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkCompletionCode(errorCode, commandTobeExecuted);
    }

    public ScriptCompletionCode checkCompletionCode(int errorCode, String commandExecuted) {
        if (errorCode == 0) {
            System.out.println("CommandLineRunner: Successfully executed the following command: " + commandExecuted);
            return ScriptCompletionCode.SUCCESS;
        }

        return ScriptCompletionCode.FAILURE;
    }

    private void printOutInputStream(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            String line;
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = input.readLine()) != null) {
                System.out.println("***FROM CONSOLE: " + line);
            }
            input.close();
        }
    }
}
